package de.ljfa.lib.blocks;

import de.ljfa.elofharmony.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
    
    protected final String name;

    public BlockBase(String name, Material material) {
        super(material);
        this.name = name;
        BlockHelper.register(this, name);
        setBlockTextureName(Reference.MODID + ":" + name);
    }
    
    public String getName() {
        return name;
    }
}
