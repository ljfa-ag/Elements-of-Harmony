package de.ljfa.elofharmony.challenges;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import de.ljfa.elofharmony.items.ItemElement.ElementType;
import de.ljfa.elofharmony.items.ItemResource.ResourceType;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.chat.ChatHelper;
import de.ljfa.lib.inventory.FullInvRestriction;
import de.ljfa.lib.inventory.SlotRestriction;
import de.ljfa.lib.inventory.SlotType;
import de.ljfa.lib.math.Metric;
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
            World world = tablePos.getWorld();
            //Check if ground around table is flat
            for(int xo = -radius; xo <= radius; xo++)
            for(int zo = -radius; zo <= radius; zo++) {
                if(xo == 0 && zo == 0)
                    continue;
                if(!world.isAirBlock(tablePos.x+xo, tablePos.y, tablePos.z+zo)) {
                    ChatHelper.toPlayerLoc(player, "elofharmony.challenge.kindness.flat_ground");
                    return false;
                }
            }
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
        final double vOffset = 0.2, //maximum vertical distance to the table and the animals
        minHDist = 0.4, //minimum horizontal distance between animals
        threshold = 0.2; //maximum distance to an animal
        
        if(event.source == DamageSource.fall && player.fallDistance >= 39.5f //Player fell 40 blocks
                && Math.abs(player.posY-tablePos.y) <= vOffset //Player is at the same y-level as the table
                && MetricHelper.d(Metric.lInf, player, tablePos) <= radius+0.5) { //Player is not too far away
            List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player,
                    AxisAlignedBB.getBoundingBox(player.posX-minHDist, player.posY-vOffset, player.posZ-minHDist, player.posX+minHDist, player.posY+vOffset, player.posZ+minHDist));
            
            if(list.size() == 1) {
                Entity ent = list.get(0);
                if(ent instanceof EntityAnimal && MetricHelper.d(Metric.l2sq, player, ent) <= threshold*threshold) {
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
        return invRestr.mayPickUp(player, stack);
    }

    private boolean complete = false;
    
    private static final int radius = 3;
    
    private static final FullInvRestriction invRestr = new FullInvRestriction(new SlotRestriction() {
        @Override
        public boolean check(SlotType type, int slot, ItemStack stack) {
            return stack == null || stack.getItem() == ModItems.twilicane
                    || (stack.getItem() == ModItems.resource && stack.getItemDamage() == ResourceType.YELLOW_FEATHER.ordinal());
        }
    });
}
