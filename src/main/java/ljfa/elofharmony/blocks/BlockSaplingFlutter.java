package ljfa.elofharmony.blocks;

import java.util.List;
import java.util.Random;

import ljfa.elofharmony.CreativeTabEoh;
import ljfa.elofharmony.Reference;
import ljfa.elofharmony.worldgen.FluttertreeGenerator;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSaplingFlutter extends BlockSapling {
    private final String name = "sapling_flutter";
    
    private FluttertreeGenerator treeGen;
    
    @SideOnly(Side.CLIENT)
    private IIcon texture;
    
    public BlockSaplingFlutter() {
        setStepSound(soundTypeGrass);
        ModBlocks.register(this, name);
        
        treeGen = new FluttertreeGenerator(true, ModBlocks.log_flutter, ModBlocks.leaves_flutter);
    }
    
    /** Grows the tree */
    @Override
    public void func_149878_d(World world, int x, int y, int z, Random rand) {
        if(world.isRemote)
            return;
        treeGen.generate(world, rand, x, y, z);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item));
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return texture;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        texture = iconRegister.registerIcon(Reference.MODID + ":sapling_flutter");
    }
}
