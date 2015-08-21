package de.ljfa.elofharmony.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.worldgen.FluttertreeGenerator;

public class BlockSaplingFlutter extends BlockSapling {
    private final String name = "sapling_flutter";
    
    private final FluttertreeGenerator treeGen;
    
    public BlockSaplingFlutter() {
        setStepSound(soundTypeGrass);
        ModBlocks.register(this, name);
        
        treeGen = new FluttertreeGenerator(true, ModBlocks.log_flutter, ModBlocks.leaves_flutter);
    }
    
    /** Grows the tree */
    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        if(world.isRemote)
            return;
        treeGen.generate(world, rand, x, y, z);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item));
    }

}
