package de.ljfa.lib.util;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionEffect;

public class PotionHelper {
    /** Adds a potion effect to the entity */
    public static void addEffect(EntityLivingBase entity, int id, int duration, int strength, boolean ambient) {
        entity.addPotionEffect(new PotionEffect(id, duration, strength, false));
    }
    
    /** Adds a potion effect to the entity */
    public static void addEffect(EntityLivingBase entity, int id, int duration, int strength) {
        addEffect(entity, id, duration, strength, false);
    }
    
    /** @return true if this item is a potion which has the specified potion effect ID */
    public static boolean isPotionOfType(Item item, int damage, int potionID) {
        if(!(item instanceof ItemPotion))
            return false;
        ItemPotion potion = (ItemPotion)item;
        List<PotionEffect> effectList = potion.getEffects(damage);
        if(effectList == null)
            return false;
        for(PotionEffect eff: effectList) {
            if(eff != null && eff.getPotionID() == potionID)
                return true;
        }
        return false;
    }

}
