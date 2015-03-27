package de.ljfa.elofharmony.blocks;

import java.util.ArrayList;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.blocks.itemblock.ItemBlockLeavesFlutter;
import de.ljfa.elofharmony.items.ItemResource.ResourceType;
import de.ljfa.elofharmony.items.ModItems;

public class BlockLeavesFlutter extends BlockLeaves {
    private final String name = "leaves_flutter";
    
    @SideOnly(Side.CLIENT)
    private IIcon texture_transparent;
    @SideOnly(Side.CLIENT)
    private IIcon texture_opaque;
    
    public BlockLeavesFlutter() {
        BlockHelper.register(this, ItemBlockLeavesFlutter.class, name);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if((meta & 4) != 0)
            return ret;
        
        // 5.5% to 10.2% chance
        // Expected: 2.9 to 5.4 per tree
        if(world.rand.nextInt(128) < 7 + 2*fortune)
            ret.add(new ItemStack(ModBlocks.sapling_flutter));
        
        // 2.3% to 4.7% chance
        // Expected: 1.2 to 2.5 per tree
        if(world.rand.nextInt(128) < 3 + fortune)
            ret.add(new ItemStack(ModItems.resource, 1, ResourceType.YELLOW_FEATHER.ordinal()));
        
        return ret;
    }
    
    @Override
    protected boolean canSilkHarvest() {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return Blocks.leaves.isOpaqueCube();
    }
    
    @Override
    public String[] func_150125_e() {
        return null;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        if(isOpaqueCube()) {
            if(world.getBlock(x, y, z) == this)
                return false;
            else
                return super.shouldSideBeRendered(world, x, y, z, side);
        } else
            return true;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return isOpaqueCube() ? texture_opaque : texture_transparent;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int par1) {
        return 0xFF75AC; //Pink
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess blockAcc, int x, int y, int z) {
        return 0xFF75AC; //Pink
    };
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        texture_transparent = iconRegister.registerIcon(Reference.MODID + ":leaves_flutter");
        texture_opaque = iconRegister.registerIcon(Reference.MODID + ":leaves_flutter_opaque");
    }
}
