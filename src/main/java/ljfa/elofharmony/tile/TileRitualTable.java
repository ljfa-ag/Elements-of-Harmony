package ljfa.elofharmony.tile;

import java.util.List;

import ljfa.elofharmony.challenges.Challenge;
import ljfa.elofharmony.challenges.ChallengeGenerosity;
import ljfa.elofharmony.handlers.ChallengeHandler;
import ljfa.elofharmony.items.ItemTwilicane;
import ljfa.elofharmony.items.ModItems;
import ljfa.elofharmony.util.ChatHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class TileRitualTable extends TileInventoryBase {
    private boolean hasChallenge = false;
    private Challenge challenge = null;
    
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
        return item == ModItems.elementOfHarmony
            || item == Items.apple
            || item == Items.feather
            || item == Items.cake
            || item == Items.diamond
            || item == Items.nether_star
            || isPotionOfType(item, stack.getItemDamage(), Potion.moveSpeed.id);
    }
    
    public boolean onPlayerInteract(EntityPlayer player) {
        InventoryPlayer playerInv = player.inventory;
        int playerSlot = playerInv.currentItem;
        ItemStack playerStack = playerInv.getCurrentItem();
        if(playerStack != null && playerStack.getItem() instanceof ItemTwilicane)
            return false;
        
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
    
    public Challenge getChallenge() {
        return challenge;
    }
    
    public boolean startChallenge(EntityPlayerMP player) {
        ChallengeHandler handler = ChallengeHandler.getInstance();
        if(!handler.hasChallengeRunning(player)) {
            if(inv[0] == null)
                return false;
            Item item = inv[0].getItem();
            Challenge challenge;
            if(item == Items.diamond)
                challenge = new ChallengeGenerosity(player, this);
            else
                return false;
            
            if(handler.tryStartChallenge(challenge)) {
                setInventorySlotContents(0, null);
                this.hasChallenge = true;
                this.challenge = challenge;
                return true;
            } else
                return false;
        }
        return false;
    }
    
    public void endChallenge() {
        hasChallenge = false;
        challenge = null;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
    @Override
    public void spillItems() {
        if(challenge != null) {
            ChatHelper.toPlayer(challenge.getPlayer(), "The challenge has been aborted as the table was broken!");
            ChallengeHandler.getInstance().abortChallenge(challenge);
        }
        super.spillItems();
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
    
    private boolean isPotionOfType(Item item, int damage, int potionID) {
        if(!(item instanceof ItemPotion))
            return false;
        ItemPotion potion = (ItemPotion)item;
        List<PotionEffect> effectList = potion.getEffects(damage);
        if(effectList == null)
            return false;
        for(PotionEffect eff: effectList) {
            if(eff != null && eff.getPotionID() == potionID)
                return true;
        }
        return false;
    }
}
