package ljfa.elofharmony.challenges;

import ljfa.elofharmony.tile.TileRitualTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class Challenge {
    protected EntityPlayerMP player;
    protected TileRitualTable table;
    
    public Challenge(EntityPlayerMP player, TileRitualTable tile) {
        this.player = player;
        this.table = tile;
    }
    
    /** @return if the challenge is ready to be started on the player */
    public abstract boolean checkStartingCondition(EntityPlayerMP player, TileRitualTable tile);
    
    /** @return true if the challenge's restriction is met */
    public abstract boolean checkRestriction();
    
    /** @return true if the challenge's completion condition is met */
    public abstract boolean checkCondition();
    
    /** Called when a challenge is about to start for the player. */
    public void onStart() {}
    
    /** Called each tick during the challenge */
    public void onTick() {}
    
    ///** Called when the player dies during the challenge */
    //public void onPlayerDeath(EntityPlayer player, NBTTagCompound data) {}
    
    /** Called when the challenge should be aborted */
    public void onAbort() {}
    
    /** Called when the challenge is completed */
    public void onComplete() {}
}
