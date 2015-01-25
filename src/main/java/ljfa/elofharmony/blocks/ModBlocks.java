package ljfa.elofharmony.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import ljfa.elofharmony.CreativeTabEoh;
import ljfa.elofharmony.Reference;
import ljfa.elofharmony.blocks.itemblock.ItemBlockLeavesFlutter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public final class ModBlocks {
    public static BlockPoisonJoke poisonjoke;
    public static BlockLogFlutter log_flutter;
    public static BlockLeavesFlutter leaves_flutter;
    public static BlockSaplingFlutter sapling_flutter;
    public static EohBlock planks_flutter;
    public static BlockSlabFlutter slab_flutter;
    public static BlockStairsFlutter stairs_flutter;
    public static BlockRitualTable ritual_table;
    
    public static void init() {
        poisonjoke = new BlockPoisonJoke();
        log_flutter = new BlockLogFlutter();
        leaves_flutter = new BlockLeavesFlutter();
        sapling_flutter = new BlockSaplingFlutter();
        planks_flutter = (EohBlock)new EohBlock("planks_flutter", Material.wood).setHardness(2.0f).setResistance(5.0f).setStepSound(Block.soundTypeWood);
        slab_flutter = new BlockSlabFlutter();
        stairs_flutter = new BlockStairsFlutter(planks_flutter, 0);
        ritual_table = new BlockRitualTable();
    }
    
    /** Sets the block's name and registers it with the given ItemBlock class */
    public static <T extends Block> T register(T block, Class<? extends ItemBlock> itemclass, String name) {
        block.setBlockName(Reference.MODID + ":" + name)
        .setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerBlock(block, itemclass, name);
        return block;
    }
    
    /** Sets the block's name and registers it */
    public static <T extends Block> T register(T block, String name) {
        block.setBlockName(Reference.MODID + ":" + name)
        .setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerBlock(block, name);
        return block;
    }
}
