package de.ljfa.elofharmony.tile;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.potion.Potion;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.challenges.Challenge;
import de.ljfa.elofharmony.challenges.ChallengeGenerosity;
import de.ljfa.elofharmony.challenges.ChallengeKindness;
import de.ljfa.elofharmony.challenges.ChallengeLaughter;
import de.ljfa.elofharmony.challenges.impl.ChallengeHandler;
import de.ljfa.elofharmony.items.ItemResource.ResourceType;
import de.ljfa.elofharmony.items.ItemTwilicane;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.network.DescriptionPacketHandler;
import de.ljfa.lib.inventory.InvUtils;
import de.ljfa.lib.tile.DescriptionPacketSynced;
import de.ljfa.lib.tile.TileInventoryBase;
import de.ljfa.lib.util.ClientUtils;
import de.ljfa.lib.util.PotionHelper;

public class TileRitualTable extends TileInventoryBase implements DescriptionPacketSynced {
    private boolean hasChallenge = false;
    
    public TileRitualTable() {
        super(1);
    }
    
    @Override
    public String getInventoryName() {
        return "elofharmony.container.ritual_table";
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
        return item == ModItems.elementOfHarmony //TODO: Not hardcode that
            || item == Items.apple
            || item == ModItems.resource && meta == ResourceType.YELLOW_FEATHER.ordinal()
            || item == Items.cake
            || item == Items.diamond
            || item == Items.nether_star
            || PotionHelper.isPotionOfType(item, meta, Potion.moveSpeed.id);
    }
    
    public boolean onPlayerInteract(EntityPlayer player) {
        InventoryPlayer playerInv = player.inventory;
        ItemStack playerStack = playerInv.getCurrentItem();
        
        //TODO: Have an interface here rather than a hardcoded class
        if(playerStack != null && playerStack.getItem() instanceof ItemTwilicane) {
            if(player.worldObj.isRemote)
                return true;
            else
                return tryStartChallenge((EntityPlayerMP)player);
        }
        
        return InvUtils.transferFromToHand(playerInv, this, 0);
    }
    
    public boolean hasChallenge() {
        return hasChallenge;
    }
    
    private boolean tryStartChallenge(EntityPlayerMP player) {
        if(!ChallengeHandler.hasChallenge(player)) {
            if(inv[0] == null)
                return false;
            Item item = inv[0].getItem();
            int meta = inv[0].getItemDamage();
            Challenge challenge; //TODO: Not hardcode that
            if(item == ModItems.resource && meta == ResourceType.YELLOW_FEATHER.ordinal())
                challenge = new ChallengeKindness(player, this);
            else if(item == Items.cake)
                challenge = new ChallengeLaughter(player, this);
            else if(item == Items.diamond)
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
        return DescriptionPacketHandler.createDescPacket(this);
    }
    
    @Override
    public void writeToPacket(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, inv[0]);
        buf.writeBoolean(hasChallenge);
    }
    
    public void readFromPacket(ByteBuf buf) {
        inv[0] = ByteBufUtils.readItemStack(buf);
        if(hasChallenge != buf.readBoolean()) { //Force render update if that has changed
            hasChallenge = !hasChallenge;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public double getMaxRenderDistanceSquared() {
        return ClientUtils.getRenderDistanceSq();
    }
}
