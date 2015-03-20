package de.ljfa.elofharmony.challenges;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import de.ljfa.elofharmony.items.ItemElement.ElementType;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.chat.ChatHelper;

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
        return true;
    }

    @Override
    public boolean checkRestriction() {
        return !player.isDead;
    }

    @Override
    public boolean checkCondition() {
        return complete;
    }

    @Override
    public void onStart() {
        ChatHelper.toPlayer(player, "The Kindness challenge is on!");
    }

    @Override
    public void onTick() {
        
    }
    
    @Override
    public void onPlayerHurt(LivingHurtEvent event) {
        if(event.source == DamageSource.fall) {
            if(getPlayer().fallDistance > 40f) {
                event.setCanceled(true);
                complete = true;
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
}
