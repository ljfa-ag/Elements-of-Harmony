package de.ljfa.elofharmony.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.challenges.Challenge;
import de.ljfa.elofharmony.challenges.ChallengeGenerosity;
import de.ljfa.elofharmony.handlers.ChallengeHandler;
import de.ljfa.elofharmony.items.ItemResource.ResourceType;
import de.ljfa.elofharmony.items.ItemTwilicane;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.lib.tile.TileInventoryBase;
import de.ljfa.lib.util.GameUtils;
import de.ljfa.lib.util.PotionHelper;

public class TileRitualTable extends TileInventoryBase {
    private boolean hasChallenge = false;
    
    public TileRitualTable() {
        super(1);
    }
    
    @Override
    public String getInventoryName() {
        return "TileRitualTable";
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }
    
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if(stack == null)
            return true;
        else if(hasChallenge)
            return false;
        Item item = stack.getItem();
        int meta = stack.getItemDamage();
        return item == ModItems.elementOfHarmony
            || item == Items.apple
            || item == ModItems.resource && meta == ResourceType.YELLOW_FEATHER.ordinal()
            || item == Items.cake
            || item == Items.diamond
            || item == Items.nether_star
            || PotionHelper.isPotionOfType(item, meta, Potion.moveSpeed.id);
    }
    
    public boolean onPlayerInteract(EntityPlayer player) {
        InventoryPlayer playerInv = player.inventory;
        int playerSlot = playerInv.currentItem;
        ItemStack playerStack = playerInv.getCurrentItem();
        
        if(playerStack != null && playerStack.getItem() instanceof ItemTwilicane) {
            if(player.worldObj.isRemote)
                return true;
            else
                return tryStartChallenge((EntityPlayerMP)player);
        }
        
        ItemStack tableStack = getStackInSlot(0);
        if(tableStack != null) {
            //Remove item from table
            if(playerInv.addItemStackToInventory(tableStack)) {
                setInventorySlotContents(0, null);
                return true;
            } else
                return false;
        } else {
            //Put item in table
            if(isItemValidForSlot(0, playerStack)) {
                ItemStack playerSplitStack = playerInv.decrStackSize(playerSlot, 1);
                setInventorySlotContents(0, playerSplitStack);
                return playerSplitStack != null;
            } else
                return false;
        }
    }
    
    public boolean hasChallenge() {
        return hasChallenge;
    }
    
    private boolean tryStartChallenge(EntityPlayerMP player) {
        if(!ChallengeHandler.hasChallenge(player)) {
            if(inv[0] == null)
                return false;
            Item item = inv[0].getItem();
            Challenge challenge;
            if(item == Items.diamond)
                challenge = new ChallengeGenerosity(player, this);
            else
                return false;
            
            if(ChallengeHandler.tryStartChallenge(challenge)) {
                setInventorySlotContents(0, null);
                this.hasChallenge = true;
                return true;
            } else
                return false;
        }
        return false;
    }
    
    public void onChallengeEnded() {
        hasChallenge = false;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        super.writeCustomNBT(tag);
        tag.setBoolean("hasChallenge", hasChallenge);
    }
    
    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        super.readCustomNBT(tag);
        hasChallenge = tag.getBoolean("hasChallenge");
    }
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeCustomNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readCustomNBT(packet.func_148857_g());
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public double getMaxRenderDistanceSquared() {
        return GameUtils.getRenderDistanceSq();
    }
}
