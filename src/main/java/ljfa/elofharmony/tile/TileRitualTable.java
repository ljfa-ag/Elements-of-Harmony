package ljfa.elofharmony.tile;

import java.util.List;
import java.util.UUID;

import ljfa.elofharmony.challenges.Challenge;
import ljfa.elofharmony.challenges.ChallengeRegistry;
import ljfa.elofharmony.handlers.ChallengeHandler;
import ljfa.elofharmony.items.ItemTwilicane;
import ljfa.elofharmony.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class TileRitualTable extends TileInventoryBase {
    public UUID challengerUUID = null;
    
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
        Item item = stack.getItem();
        return item == ModItems.elementOfHarmony
            || item == Items.apple
            || item == Items.feather
            || item == Items.cake
            || item == Items.diamond
            || item == Items.nether_star
            || isPotionOfType(item, stack.getItemDamage(), Potion.moveSpeed.id);
    }
    
    public void onPlayerInteract(EntityPlayer player) {
        InventoryPlayer playerInv = player.inventory;
        int playerSlot = playerInv.currentItem;
        ItemStack playerStack = playerInv.getCurrentItem();
        if(playerStack != null && playerStack.getItem() instanceof ItemTwilicane)
            return;
        
        ItemStack tableStack = this.getStackInSlot(0);
        if(tableStack != null) {
            if(playerInv.addItemStackToInventory(tableStack)) {
                this.setInventorySlotContents(0, null);
            }
        } else {
            if(this.isItemValidForSlot(0, playerStack)) {
                ItemStack playerSplitStack = playerInv.decrStackSize(playerSlot, 1);
                this.setInventorySlotContents(0, playerSplitStack);
            }
        }
    }
    
    public boolean startChallenge(EntityPlayer player) {
        if(!ChallengeHandler.hasChallengeRunning(player)) {
            if(inv[0] == null)
                return false;
            Item item = inv[0].getItem();
            Challenge challenge;
            if(item == Items.diamond)
                challenge = ChallengeRegistry.generosity;
            else
                return false;
            
            if(ChallengeHandler.startChallenge(player, challenge, this)) {
                setInventorySlotContents(0, null);
                challengerUUID = player.getUniqueID();
                return true;
            } else
                return false;
        }
        return false;
    }
    
    public void endChallenge() {
        challengerUUID = null;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if(challengerUUID != null) {
            tag.setLong("challengerMost", challengerUUID.getMostSignificantBits());
            tag.setLong("challengerLeast", challengerUUID.getLeastSignificantBits());
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if(tag.hasKey("challengerMost")) {
            challengerUUID = new UUID(tag.getLong("challengerMost"), tag.getLong("challengerLeast"));
        }
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
