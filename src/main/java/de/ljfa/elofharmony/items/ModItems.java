package de.ljfa.elofharmony.items;

import de.ljfa.elofharmony.CreativeTabEoh;
import de.ljfa.elofharmony.Reference;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ModItems {

    public static ItemElement elementOfHarmony;
    public static ItemTwilicane twilicane;
    public static ItemResource resource;
    
    public static void init() {
        elementOfHarmony = new ItemElement();
        twilicane = new ItemTwilicane();
        resource = new ItemResource();
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerItemModels(ItemModelMesher mesher) {
        
    }

    /** Sets the item's name and texture and registers it */
    public static <T extends Item> T register(T item, String name, String imageName) {
        item.setUnlocalizedName(Reference.MODID + ":" + name)
        .setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerItem(item, name);
        return item;
    }

    /** Sets the item's name and texture and registers it */
    public static <T extends Item> T register(T item, String name) {
        return register(item, name, name);
    }
}
