package de.ljfa.lib.items;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Can be implemented by items or blocks and allows them to register
 * their item models on initalization
 */
public interface ModeledItem {
    /** Gets called during the Initialization phase */
    @SideOnly(Side.CLIENT)
    void registerItemModels(ItemModelMesher mesher);
}
