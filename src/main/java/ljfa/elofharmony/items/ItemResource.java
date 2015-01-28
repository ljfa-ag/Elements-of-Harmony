package ljfa.elofharmony.items;

import java.util.List;

import ljfa.elofharmony.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemResource extends Item {
    @SideOnly(Side.CLIENT)
    private IIcon[] textures;
    
    public enum ResourceType {
        YELLOW_FEATHER("yellow_feather");
        
        private String name;
        private String unlocalizedName;
        private String textureName;
        
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
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        textures = new IIcon[typeCount];
        for(int i = 0; i < typeCount; i++)
            textures[i] = iconRegister.registerIcon(ResourceType.values()[i].textureName);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        if(meta >= typeCount)
            meta = 0;
        return textures[meta];
    }
}
