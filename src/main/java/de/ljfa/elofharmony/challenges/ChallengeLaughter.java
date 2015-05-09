package de.ljfa.elofharmony.challenges;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
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
        startTimeMillis = System.currentTimeMillis();
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
        return getRuntime() < maxMillisecs && invRestr.check(player);
    }

    @Override
    public boolean checkCondition() {
        return stoneMined >= stoneNeeded;
    }

    @Override
    public void onStart() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.start0");
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.start1", stoneNeeded, maxMillisecs/1000f);
    }

    @Override
    public void onTick() {
        
    }
    
    @Override
    public void onPlayerBreakBlock(BreakEvent event) {
        if(event.block == Blocks.stone || event.block == Blocks.cobblestone)
            stoneMined++;
    }

    @Override
    public void onAbort() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.failed");
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.mined", stoneMined);
        super.onAbort();
    }

    @Override
    public void onComplete() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.success");
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.laughter.time", getRuntime()/1000f);
        super.onComplete();
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("Mined", stoneMined);
        tag.setLong("Millisecs", getRuntime());
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        stoneMined = tag.getInteger("Mined");
        startTimeMillis = System.currentTimeMillis() - tag.getLong("Millisecs");
    }
    
    @Override
    public String toString() {
        return super.toString()
            + "\nStone mined: " + stoneMined + " / " + stoneNeeded
            + "\nTime elapsed: " + getRuntime() + " / " + maxMillisecs;
    }
    
    private long getRuntime() {
        return System.currentTimeMillis() - startTimeMillis;
    }

    private int stoneMined = 0;
    private long startTimeMillis;
    
    public static final int stoneNeeded = 2*64;
    public static final int maxMillisecs = 65*1000;
    
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
