package de.ljfa.elofharmony.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import de.ljfa.elofharmony.Reference;

public class BlockBase extends Block {
    
    protected final String name;

    public BlockBase(String name, Material material) {
        super(material);
        this.name = name;
        ModBlocks.register(this, name);
        setBlockTextureName(Reference.MODID + ":" + name);
    }
    
    public BlockBase(String name, Class<? extends ItemBlock> itemClass, Material material) {
        super(material);
        this.name = name;
        ModBlocks.register(this, itemClass, name);
        setBlockTextureName(Reference.MODID + ":" + name);
    }
    
    public String getName() {
        return name;
    }
}
