package de.ljfa.elofharmony.blocks;

import de.ljfa.lib.items.ModeledItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block implements ModeledItem {
    
    protected final String name;

    public BlockBase(String name, Material material) {
        super(material);
        this.name = name;
        ModBlocks.register(this, name);
    }
    
    public BlockBase(String name, Class<? extends ItemBlock> itemClass, Material material) {
        super(material);
        this.name = name;
        ModBlocks.register(this, itemClass, name);
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemModels(ItemModelMesher mesher) {
        ModBlocks.defaultRegisterModel(mesher, this, name);
    }
}
