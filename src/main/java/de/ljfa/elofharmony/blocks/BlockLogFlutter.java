package de.ljfa.elofharmony.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Reference;

public class BlockLogFlutter extends BlockLog {
    private final String name = "log_flutter";
    
    @SideOnly(Side.CLIENT)
    private IIcon texture_side;
    @SideOnly(Side.CLIENT)
    private IIcon texture_top;
    
    public BlockLogFlutter() {
        BlockHelper.register(this, name);
    }
    
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int meta) {
        return texture_side;
    }
    
    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int meta) {
        return texture_top;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        texture_side = iconRegister.registerIcon(Reference.MODID + ":log_flutter");
        texture_top = iconRegister.registerIcon(Reference.MODID + ":log_flutter_top");
    }
}
