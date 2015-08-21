package de.ljfa.elofharmony.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Reference;

public class ItemResource extends Item {
    public enum ResourceType {
        YELLOW_FEATHER("yellow_feather");
        
        private final String name;
        private final String unlocalizedName;
        private final String textureName;
        
        private ResourceType(String name) {
            this.name = name;
            this.unlocalizedName = Reference.MODID + ":" + name; 
            this.textureName = Reference.MODID + ":" + name; 
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

}
