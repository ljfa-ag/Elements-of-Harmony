package de.ljfa.elofharmony.challenges;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import de.ljfa.elofharmony.items.ItemElement.ElementType;
import de.ljfa.elofharmony.items.ItemResource.ResourceType;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.chat.ChatHelper;
import de.ljfa.lib.inventory.FullInvRestriction;
import de.ljfa.lib.inventory.SlotRestriction;
import de.ljfa.lib.inventory.SlotType;
import de.ljfa.lib.math.MetricHelper;

public class ChallengeKindness extends TableChallenge {

    public ChallengeKindness() { }

    public ChallengeKindness(EntityPlayerMP player, TileRitualTable tile) {
        super(player, tile);
    }

    @Override
    protected ItemStack getResult() {
        return new ItemStack(ModItems.elementOfHarmony, 1, ElementType.KINDNESS.ordinal());
    }

    @Override
    public boolean checkStartingCondition() {
        if(invRestr.check(player)) {
            return true;
        } else {
            ChatHelper.toPlayerLoc(player, "elofharmony.challenge.kindness.no_items_allowed");
            return false;
        }
    }

    @Override
    public boolean checkRestriction() {
        return !player.isDead && invRestr.check(player);
    }

    @Override
    public boolean checkCondition() {
        return complete;
    }

    @Override
    public void onStart() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.kindness.start");
    }

    @Override
    public void onTick() {
        
    }
    
    @Override
    public void onPlayerHurt(LivingHurtEvent event) {
        if(event.source == DamageSource.fall && player.fallDistance >= 39.5f && MetricHelper.distInf(player, tablePos) <= 3.5) {
            double minDistH = 0.25, offsetV = 0.2;
            List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player,
                    AxisAlignedBB.getBoundingBox(player.posX-minDistH, player.posY-offsetV, player.posZ-minDistH, player.posX+minDistH, player.posY+offsetV, player.posZ+minDistH));
            
            if(list.size() == 1) {
                Entity ent = list.get(0);
                double threshold = 0.2;
                if(ent instanceof EntityAnimal && MetricHelper.dist2sq(player, ent) <= threshold*threshold) {
                    event.setCanceled(true);
                    complete = true;
                }
            }
        }
    }
    
    @Override
    public void onAbort() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.failed");
        super.onAbort();
    }

    @Override
    public void onComplete() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.success");
        super.onComplete();
    }

    @Override
    public boolean mayPickUp(ItemStack stack) {
        return false;
    }

    private boolean complete = false;
    
    private static final FullInvRestriction invRestr = new FullInvRestriction(new SlotRestriction() {
        @Override
        public boolean check(SlotType type, int slot, ItemStack stack) {
            return stack == null || stack.getItem() == ModItems.twilicane
                    || (stack.getItem() == ModItems.resource && stack.getItemDamage() == ResourceType.YELLOW_FEATHER.ordinal());
        }
    });
}
