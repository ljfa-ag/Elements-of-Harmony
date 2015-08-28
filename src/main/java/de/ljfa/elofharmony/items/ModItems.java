package de.ljfa.elofharmony.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.ljfa.elofharmony.CreativeTabEoh;
import de.ljfa.elofharmony.Reference;
import de.ljfa.lib.items.ModeledItem;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    public static ItemElement elementOfHarmony;
    public static ItemTwilicane twilicane;
    public static ItemResource resource;
    
    public static void preInit() {
        elementOfHarmony = new ItemElement();
        twilicane = new ItemTwilicane();
        resource = new ItemResource();
    }
    
    public static List<Item> getList() {
        return Collections.unmodifiableList(itemList);
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerItemModels(ItemModelMesher mesher) {
        for(Item item: itemList) {
            if(item instanceof ModeledItem)
                ((ModeledItem)item).registerItemModels(mesher);
        }
    }

    /** Sets the item's name and texture and registers it */
    public static <T extends Item> T register(T item, String name) {
        item.setUnlocalizedName(Reference.MODID + ":" + name)
        .setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerItem(item, name);
        itemList.add(item);
        return item;
    }
    
    /** Default implementation of registerItemModels */
    @SideOnly(Side.CLIENT)
    public static void defaultRegisterModel(ItemModelMesher mesher, Item item, String name) {
        mesher.register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + name, "inventory"));
    }
    
    private static final List<Item> itemList = new ArrayList<>();
}
