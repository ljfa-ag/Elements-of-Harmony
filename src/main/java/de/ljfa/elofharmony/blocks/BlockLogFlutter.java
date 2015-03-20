package de.ljfa.elofharmony.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Reference;
import de.ljfa.lib.blocks.BlockHelper;

public class BlockLogFlutter extends BlockLog {
    private final String name = "log_flutter";
    
    @SideOnly(Side.CLIENT)
    private IIcon[] textures;
    
    public BlockLogFlutter() {
        BlockHelper.register(this, name);
    }
    
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int meta) {
        return textures[0];
    }
    
    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int meta) {
        return textures[1];
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        textures = new IIcon[] {
            iconRegister.registerIcon(Reference.MODID + ":log_flutter"),
            iconRegister.registerIcon(Reference.MODID + ":log_flutter_top")
        };
    }
}
