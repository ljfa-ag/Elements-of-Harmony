package ljfa.elofharmony.items;

import cpw.mods.fml.common.registry.GameRegistry;
import ljfa.elofharmony.CreativeTabEoh;
import ljfa.elofharmony.Reference;
import net.minecraft.item.Item;

public class ModItems {

    public static void init() {
        
    }
    
    public static Item register(Item item, String name, String imageName) {
        item.setCreativeTab(CreativeTabEoh.EOH_TAB)
        .setUnlocalizedName(Reference.MODID + ":" + name)
        .setTextureName(Reference.MODID + ":" + imageName);
        GameRegistry.registerItem(item, name);
        return item;
    }
}
