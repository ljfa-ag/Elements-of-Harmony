package ljfa.elofharmony.tile;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;

public class TileRitualTable extends TileInventoryBase {
    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public String getInventoryName() {
        return "TileRitualTable";
    }
    
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        Item item = stack.getItem();
        return item == Items.apple
                || item == Items.feather
                || item == Items.cake
                || isPotionOfType(item, stack.getItemDamage(), Potion.moveSpeed.id)
                || item == Items.diamond
                || item == Items.nether_star;
    }
    
    private boolean isPotionOfType(Item item, int damage, int potionID) {
        if(!(item instanceof ItemPotion))
            return false;
        ItemPotion potion = (ItemPotion)item;
        List<PotionEffect> effects = potion.getEffects(damage);
        if(effects == null)
            return false;
        for(PotionEffect eff: effects) {
            if(eff != null && eff.getPotionID() == potionID)
                return true;
        }
        return false;
    }
}
