package de.ljfa.elofharmony.challenges;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import de.ljfa.elofharmony.items.ItemElement;
import de.ljfa.elofharmony.items.ItemElement.ElementType;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.chat.ChatHelper;
import de.ljfa.lib.inventory.FullInvRestriction;
import de.ljfa.lib.inventory.PlayerSlotRestriction;
import de.ljfa.lib.inventory.PlayerSlotType;
import de.ljfa.lib.items.ItemHelper;

public class ChallengeLaughter extends TableChallenge {

    public ChallengeLaughter(EntityPlayerMP player, TileRitualTable tile) {
        super(player, tile);
    }

    public ChallengeLaughter() { }

    @Override
    protected ItemStack getResult() {
        return new ItemStack(ModItems.elementOfHarmony, 1, ElementType.LAUGHTER.ordinal());
    }

    @Override
    public boolean checkStartingCondition() {
        if(invRestr.check(player))
            return true;
        else {
            ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.no_items_allowed");
            return false;
        }
    }

    @Override
    public boolean checkRestriction() {
        return invRestr.check(player);
    }

    @Override
    public boolean checkCondition() {
        return false;
    }

    @Override
    public void onStart() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.start");
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

    public static final FullInvRestriction invRestr = new FullInvRestriction(new PlayerSlotRestriction() {
        @Override
        public boolean check(PlayerSlotType type, int slot, ItemStack stack) {
            Item item = stack.getItem();
            if(item == Item.getItemFromBlock(Blocks.cobblestone))
                return true;
            else if(ItemHelper.vanillaTools.contains(item))
                return !stack.isItemEnchanted();
            else
                return false;
        }
    });
}
