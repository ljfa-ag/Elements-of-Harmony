package de.ljfa.elofharmony.blocks;

import de.ljfa.lib.items.ModeledItem;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStairsFlutter extends BlockStairs implements ModeledItem {
    private final String name = "stairs_flutter";
    
    public BlockStairsFlutter(IBlockState modelState) {
        super(modelState);
        ModBlocks.register(this, name);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemModels(ItemModelMesher mesher) {
        ModBlocks.defaultRegisterModel(mesher, this, name);
    }
}
