package ljfa.elofharmony.challenges;

import net.minecraft.entity.player.EntityPlayer;

public abstract class Challenge {
    public final int id;
    
    protected Challenge(int id) {
        challenges[id] = this;
        this.id = id;
    }
    
    /** Called when a challenge is about to start for the player.
     * @return true if it successfully started
     */
    public abstract boolean start(EntityPlayer player);
    
    /** @return true if the challenge's restriction is met */
    public abstract boolean checkRestriction(EntityPlayer player);
    
    /** @return true if the challenge's completion condition is met */
    public abstract boolean checkCondition(EntityPlayer player);
    
    /** Called each tick during the challenge */
    public abstract void tick(EntityPlayer player);
    
    /** Called when the challenge should be aborted */
    public abstract void abort(EntityPlayer player);
    
    /** Called when the challenge is completed */
    public abstract void complete(EntityPlayer player);
    
    private static Challenge[] challenges = new Challenge[6];
    
    public static Challenge fromId(int id) {
        return challenges[id];
    }
}
