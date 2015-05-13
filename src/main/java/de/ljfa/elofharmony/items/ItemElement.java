package de.ljfa.elofharmony.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Reference;

public class ItemElement extends Item {
    @SideOnly(Side.CLIENT)
    private IIcon[] textures;
    
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
    public boolean hasEffect(ItemStack stack, int pass) {
        return true;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack) {
        if(stack.getItemDamage() == ElementType.MAGIC.ordinal())
            return EnumRarity.epic;
        else
            return EnumRarity.rare;
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
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        textures = new IIcon[elementCount];
        for(int i = 0; i < elementCount; i++)
            textures[i] = iconRegister.registerIcon(ElementType.values()[i].textureName);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        if(meta >= elementCount)
            meta = 0;
        return textures[meta];
    }
}
