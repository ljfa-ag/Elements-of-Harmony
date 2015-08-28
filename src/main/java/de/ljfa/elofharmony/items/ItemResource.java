package de.ljfa.elofharmony.items;

import java.util.List;

import de.ljfa.elofharmony.Reference;
import de.ljfa.lib.items.ModeledItem;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemResource extends Item implements ModeledItem {
    public enum ResourceType {
        YELLOW_FEATHER("yellow_feather");
        
        private final String name;
        private final String unlocalizedName;
        
        private ResourceType(String name) {
            this.name = name;
            this.unlocalizedName = Reference.MODID + ":" + name;
        }
    }
    
    public final int typeCount = ResourceType.values().length;
    
    public ItemResource() {
        ModItems.register(this, "resource");
        setHasSubtypes(true);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        if(meta >= typeCount)
            meta = 0;
        return "item." + ResourceType.values()[meta].unlocalizedName;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for(int i = 0; i < typeCount; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    public void registerItemModels(ItemModelMesher mesher) {
        for(ResourceType type: ResourceType.values()) {
            ModelBakery.addVariantName(this, type.unlocalizedName);
            mesher.register(this, type.ordinal(), new ModelResourceLocation(type.unlocalizedName, "inventory"));
        }
    }

}
