package de.ljfa.lib.items;

import java.util.Arrays;
import java.util.Set;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Sets;

import de.ljfa.lib.math.DimPos;

public class ItemHelper {

    /** Heuristic function that doesn't detect armor that is not extending ItemArmor */
    public static boolean isItemArmor(Item item, int armorType) {
        if(item instanceof ItemArmor)
            return ((ItemArmor)item).armorType == armorType;
        else if(armorType == 0)
            return item == Items.skull || item == Item.getItemFromBlock(Blocks.pumpkin);
        else
            return false;
    }
    
    /** Spawns an EntityItem at the specified position */
    public static void dropItem(World world, double x, double y, double z, ItemStack stack) {
        world.spawnEntityInWorld(new EntityItem(world, x, y, z, stack));
    }
    
    /** Spawns an EntityItem at the specified DimPos */
    public static void dropItem(DimPos pos, ItemStack stack) {
        dropItem(pos.getWorld(), pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, stack);
    }
    
    /** Set of all vanilla pickaxes, shovels and axes */
    public static final Set<Item> vanillaTools;
    
    static {
        vanillaTools = Sets.newIdentityHashSet();
        vanillaTools.addAll(Arrays.asList(
            Items.wooden_pickaxe, Items.stone_pickaxe, Items.iron_pickaxe, Items.golden_pickaxe, Items.diamond_pickaxe,
            Items.wooden_shovel, Items.stone_shovel, Items.iron_shovel, Items.golden_shovel, Items.diamond_shovel,
            Items.wooden_axe, Items.stone_axe, Items.iron_axe, Items.golden_axe, Items.diamond_axe));
    }
}
