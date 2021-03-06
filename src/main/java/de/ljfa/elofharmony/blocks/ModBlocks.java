package de.ljfa.elofharmony.blocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.ljfa.elofharmony.CreativeTabEoh;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.lib.items.ModeledItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        slab_flutter = new BlockSlabFlutter(false);
        stairs_flutter = new BlockStairsFlutter(planks_flutter.getDefaultState());
        ritual_table = new BlockRitualTable();
        locker = new BlockLocker();
        
        setFireInfo();
    }
    
    public static List<Block> getList() {
        return Collections.unmodifiableList(blockList);
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerItemModels(ItemModelMesher mesher) {
        for(Block block: blockList) {
            if(block instanceof ModeledItem)
                ((ModeledItem)block).registerItemModels(mesher);
        }
    }
    
    private static void setFireInfo() {
        Blocks.fire.setFireInfo(log_flutter, 5, 5);
        Blocks.fire.setFireInfo(leaves_flutter, 30, 60);
        Blocks.fire.setFireInfo(planks_flutter, 5, 20);
        Blocks.fire.setFireInfo(slab_flutter, 5, 20);
        Blocks.fire.setFireInfo(stairs_flutter, 5, 20);
    }

    /** Sets the block's name and registers it with the given ItemBlock class */
    public static <T extends Block> T register(T block, Class<? extends ItemBlock> itemClass, String name) {
        block.setUnlocalizedName(Reference.MODID + ":" + name)
        .setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerBlock(block, itemClass, name);
        blockList.add(block);
        return block;
    }

    /** Sets the block's name and registers it */
    public static <T extends Block> T register(T block, String name) {
        return register(block, ItemBlock.class, name);
    }
    
    /** Default implementation of registerItemModels */
    @SideOnly(Side.CLIENT)
    public static void defaultRegisterModel(ItemModelMesher mesher, Block block, String name) {
        ModItems.defaultRegisterModel(mesher, Item.getItemFromBlock(block), name);
    }
    
    private static final List<Block> blockList = new ArrayList<>();
}
