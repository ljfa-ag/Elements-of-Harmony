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
        ModBlocks.register(this, name);
        setBlockTextureName(Reference.MODID + ":" + name);
    }
    
    public String getName() {
        return name;
    }
}
