package ljfa.elofharmony.blocks;

import ljfa.elofharmony.Reference;
import ljfa.elofharmony.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EohBlock extends Block {
    
    protected final String name;

    public EohBlock(String name, Material material) {
        super(material);
        this.name = name;
        ModBlocks.register(this, name);
        setBlockTextureName(Reference.MODID + ":" + name);
    }
    
    public String getName() {
        return name;
    }
}
