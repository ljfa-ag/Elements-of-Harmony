package de.ljfa.elofharmony.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Reference;

public class ItemElement extends Item {
    public enum ElementType {
        HONESTY("honesty"),
        KINDNESS("kindness"),
        LAUGHTER("laughter"),
        LOYALTY("loyalty"),
        GENEROSITY("generosity"),
        MAGIC("magic");
        
        private final String subname;
        private final String unlocalizedName;
        private final String textureName;
        
        private ElementType(String subname) {
            this.subname = subname;
            this.unlocalizedName = Reference.MODID + ":element_" + subname; 
            this.textureName = Reference.MODID + ":element_" + subname; 
        }
    }
    
    public final int elementCount = ElementType.values().length;
    
    public ItemElement() {
        ModItems.register(this, "element_of_harmony", "element_honesty");
        setHasSubtypes(true);
        setMaxStackSize(1);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack) {
        if(stack.getItemDamage() == ElementType.MAGIC.ordinal())
            return EnumRarity.EPIC;
        else
            return EnumRarity.RARE;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        if(meta >= elementCount)
            meta = 0;
        return "item." + ElementType.values()[meta].unlocalizedName;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for(int i = 0; i < elementCount; i++)
            list.add(new ItemStack(item, 1, i));
    }

}
