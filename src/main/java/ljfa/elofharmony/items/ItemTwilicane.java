package ljfa.elofharmony.items;

import ljfa.elofharmony.Reference;
import ljfa.elofharmony.items.ItemElement.ElementType;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTwilicane extends Item {
    public ItemTwilicane() {
        ModItems.register(this, "twilicane");
        setMaxStackSize(1);
    }
}
