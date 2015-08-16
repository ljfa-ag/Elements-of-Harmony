package de.ljfa.elofharmony.challenges;

import java.util.Objects;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

public abstract class Challenge {

    protected EntityPlayerMP player;
    
    public Challenge(EntityPlayerMP player) {
        this.player = player;
    }
    
    public Challenge() { }
    
    /** @return if the challenge is ready to be started on the player */
    public abstract boolean checkStartingCondition();
    
    /** @return true if the challenge's restriction is met */
    public abstract boolean checkRestriction();
    
    /** @return true if the challenge's completion condition is met */
    public abstract boolean checkCondition();
    
    /** Called when a challenge is about to start for the player. */
    public abstract void onStart();
    
    /** Called each tick during the challenge */
    public abstract void onTick();
    
    /** @return true if the player is allowed to pick up this stack into the inventory */
    public boolean mayPickUp(ItemStack stack) {
        return true;
    }
    
    /** Called when the challenge is canceled by the player */
    public abstract void onAbort();
    
    /** Called when the challenge's restriction has been violated */
    public abstract void onFail();
    
    /** Called when the challenge is completed */
    public abstract void onComplete();
    
    /** Called when the player takes damage */
    public void onPlayerHurt(LivingHurtEvent event) { }
    
    /** Called when the player dies */
    public void onPlayerDeath(LivingDeathEvent event) { }
    
    /** Called when the player breaks a block */
    public void onPlayerBreakBlock(BlockEvent.BreakEvent event) { }
    
    /** Writes the challenge data to NBT */
    public abstract void writeToNBT(NBTTagCompound tag);
    
    /** Reads the challenge data from NBT */
    public abstract void readFromNBT(NBTTagCompound tag);
    
    public EntityPlayerMP getPlayer() {
        return player;
    }
    
    public void setPlayer(EntityPlayerMP player) {
        this.player = Objects.requireNonNull(player);
    }
}
