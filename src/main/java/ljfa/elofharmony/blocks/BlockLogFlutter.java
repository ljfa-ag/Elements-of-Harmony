package ljfa.elofharmony.blocks;

import ljfa.elofharmony.CreativeTabEoh;
import ljfa.elofharmony.Reference;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLogFlutter extends BlockLog {
    private String name = "log_flutter";
    
    @SideOnly(Side.CLIENT)
    private IIcon[] textures;
    
    public BlockLogFlutter() {
        setBlockName(Reference.MODID + ":" + name);
        setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerBlock(this, name);
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
