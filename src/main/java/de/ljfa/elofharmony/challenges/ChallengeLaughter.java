package de.ljfa.elofharmony.challenges;

import java.util.Locale;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import de.ljfa.elofharmony.items.ItemElement.ElementType;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.inventory.FullInvRestriction;
import de.ljfa.lib.inventory.PlayerSlotRestriction;
import de.ljfa.lib.inventory.PlayerSlotType;
import de.ljfa.lib.items.ItemHelper;
import de.ljfa.lib.util.ChatHelper;

//TODO: This will probably not work well at all on a laggy server. I need to come up with something different.
public final class ChallengeLaughter extends TableChallenge {

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
        return ticks < maxTicks && invRestr.check(player);
    }

    @Override
    public boolean checkCondition() {
        return stoneMined >= stoneNeeded;
    }

    @Override
    public void onStart() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.start0");
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.start1", stoneNeeded, String.format(Locale.ENGLISH, "%.0f", maxTicks/20.0f));
    }

    @Override
    public void onTick() {
        ticks++;
    }
    
    @Override
    public void onPlayerBreakBlock(BreakEvent event) {
        if(event.state.getBlock() == Blocks.stone || event.state.getBlock() == Blocks.cobblestone)
            stoneMined++;
    }

    @Override
    public void onAbort() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.aborted");
        super.onAbort();
    }
    
    @Override
    public void onFail() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.failed");
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.mined", stoneMined);
        super.onFail();
    }

    @Override
    public void onComplete() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.success");
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.time", String.format(Locale.ENGLISH, "%.2f", ticks/20.0f));
        super.onComplete();
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("Mined", stoneMined);
        tag.setInteger("Ticks", ticks);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        stoneMined = tag.getInteger("Mined");
        ticks = tag.getInteger("Ticks");
    }
    
    @Override
    public String toString() {
        return super.toString()
            + "\nStone mined: " + stoneMined + " / " + stoneNeeded
            + "\nTime elapsed: " + ticks + " / " + maxTicks;
    }

    private int stoneMined = 0;
    private int ticks = 0;
    
    public static final int stoneNeeded = 2*64;
    public static final int maxTicks = 65*20;
    
    private static final FullInvRestriction invRestr = new FullInvRestriction(new PlayerSlotRestriction() {
        @Override
        public boolean check(PlayerSlotType type, int slot, ItemStack stack) {
            if(stack == null)
                return true;
            Item item = stack.getItem();
            if(item == ModItems.twilicane || item == Item.getItemFromBlock(Blocks.cobblestone) || item == Item.getItemFromBlock(Blocks.torch))
                return true;
            else if(ItemHelper.vanillaTools.contains(item))
                return !stack.isItemEnchanted();
            else
                return false;
        }
    });
}
