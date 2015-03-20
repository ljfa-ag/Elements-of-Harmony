package de.ljfa.elofharmony.challenges;

import de.ljfa.elofharmony.items.ItemElement.ElementType;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.chat.ChatHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

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
        return true;
    }

    @Override
    public void onStart() {
        ChatHelper.toPlayer(player, "The Kindness challenge is on!");
    }

    @Override
    public void onTick() {

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

}
