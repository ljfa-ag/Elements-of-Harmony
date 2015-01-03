package ljfa.elofharmony.blocks;

import ljfa.elofharmony.CreativeTabEoh;
import ljfa.elofharmony.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class EohBlock extends Block {
    private String name;

    public EohBlock(String name, Material material) {
        super(material);
        this.name = name;
        setBlockName(Reference.MODID + ":" + name);
        setBlockTextureName(Reference.MODID + ":" + name);
        setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerBlock(this, name);
    }
    
    public String getName() {
        return name;
    }
}
