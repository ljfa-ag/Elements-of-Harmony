package de.ljfa.elofharmony.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class ModBlocks {
    public static BlockPoisonJoke poisonjoke;
    public static BlockLogFlutter log_flutter;
    public static BlockLeavesFlutter leaves_flutter;
    public static BlockSaplingFlutter sapling_flutter;
    public static BlockBase planks_flutter;
    public static BlockSlabFlutter slab_flutter;
    public static BlockStairsFlutter stairs_flutter;
    public static BlockRitualTable ritual_table;
    
    public static void init() {
        poisonjoke = new BlockPoisonJoke();
        log_flutter = new BlockLogFlutter();
        leaves_flutter = new BlockLeavesFlutter();
        sapling_flutter = new BlockSaplingFlutter();
        planks_flutter = (BlockBase)new BlockBase("planks_flutter", Material.wood).setHardness(2.0f).setResistance(5.0f).setStepSound(Block.soundTypeWood);
        slab_flutter = new BlockSlabFlutter();
        stairs_flutter = new BlockStairsFlutter(planks_flutter, 0);
        ritual_table = new BlockRitualTable();
    }
}
