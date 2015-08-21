package de.ljfa.elofharmony.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import de.ljfa.elofharmony.CreativeTabEoh;
import de.ljfa.elofharmony.Reference;

public final class ModBlocks {
    public static BlockPoisonJoke poisonjoke;
    public static BlockLogFlutter log_flutter;
    public static BlockLeavesFlutter leaves_flutter;
    public static BlockSaplingFlutter sapling_flutter;
    public static BlockBase planks_flutter;
    public static BlockSlabFlutter slab_flutter;
    public static BlockStairsFlutter stairs_flutter;
    public static BlockRitualTable ritual_table;
    public static BlockLocker locker;
    
    public static void preInit() {
        poisonjoke = new BlockPoisonJoke();
        log_flutter = new BlockLogFlutter();
        leaves_flutter = new BlockLeavesFlutter();
        sapling_flutter = new BlockSaplingFlutter();
        planks_flutter = (BlockBase)new BlockBase("planks_flutter", Material.wood).setHardness(2.0f).setResistance(5.0f).setStepSound(Block.soundTypeWood);
        slab_flutter = new BlockSlabFlutter();
        stairs_flutter = new BlockStairsFlutter(planks_flutter, 0);
        ritual_table = new BlockRitualTable();
        locker = new BlockLocker();
        
        setFireInfo();
    }
    
    private static void setFireInfo() {
        Blocks.fire.func_180686_a(log_flutter, 5, 5);
        Blocks.fire.func_180686_a(leaves_flutter, 30, 60);
        Blocks.fire.func_180686_a(planks_flutter, 5, 20);
        Blocks.fire.func_180686_a(slab_flutter, 5, 20);
        Blocks.fire.func_180686_a(stairs_flutter, 5, 20);
    }

    /** Sets the block's name and registers it with the given ItemBlock class */
    public static <T extends Block> T register(T block, Class<? extends ItemBlock> itemClass, String name) {
        block.setUnlocalizedName(Reference.MODID + ":" + name)
        .setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerBlock(block, itemClass, name);
        return block;
    }

    /** Sets the block's name and registers it */
    public static <T extends Block> T register(T block, String name) {
        block.setUnlocalizedName(Reference.MODID + ":" + name)
        .setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerBlock(block, name);
        return block;
    }
}
