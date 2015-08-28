package de.ljfa.elofharmony.blocks;

import java.util.List;
import java.util.Random;

import de.ljfa.elofharmony.worldgen.FluttertreeGenerator;
import de.ljfa.lib.items.ModeledItem;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSaplingFlutter extends BlockSapling implements ModeledItem {
    private final String name = "sapling_flutter";
    
    private final FluttertreeGenerator treeGen;
    
    public BlockSaplingFlutter() {
        setStepSound(soundTypeGrass);
        setDefaultState(blockState.getBaseState().withProperty(STAGE, 0));
        ModBlocks.register(this, name);
        treeGen = new FluttertreeGenerator(true, ModBlocks.log_flutter.getDefaultState(), ModBlocks.leaves_flutter.getDefaultState());
    
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
            setStateMapper();
    }
    
    /** Grows the tree */
    @Override
    public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
        treeGen.generate(world, rand, pos);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item));
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(STAGE, meta);
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue(STAGE);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemModels(ItemModelMesher mesher) {
        ModBlocks.defaultRegisterModel(mesher, this, name);
    }
    
    @SideOnly(Side.CLIENT)
    private void setStateMapper() {
        //FIXME: Hacky solution, I'd rather not have the TYPE property at all in the state
        //but that messes with BlockSapling's constructor...
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().addPropertiesToIgnore(TYPE, STAGE).build());
    }
}
