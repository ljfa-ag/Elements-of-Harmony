package de.ljfa.elofharmony.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import de.ljfa.elofharmony.CreativeTabEoh;
import de.ljfa.elofharmony.Reference;


public class ModItems {

    public static ItemElement elementOfHarmony;
    public static ItemTwilicane twilicane;
    public static ItemResource resource;
    
    public static void init() {
        elementOfHarmony = new ItemElement();
        twilicane = new ItemTwilicane();
        resource = new ItemResource();
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
