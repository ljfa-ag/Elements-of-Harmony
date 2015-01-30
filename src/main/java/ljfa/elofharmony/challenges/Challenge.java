package ljfa.elofharmony.challenges;

import net.minecraft.entity.player.EntityPlayer;

public abstract class Challenge {
    public final int id;
    
    protected Challenge(int id) {
        challenges[id] = this;
        this.id = id;
    }
    
    public abstract boolean start(EntityPlayer player);
    public abstract boolean checkRestriction(EntityPlayer player);
    public abstract boolean checkCondition(EntityPlayer player);
    public abstract void tick(EntityPlayer player);
    public abstract void abort(EntityPlayer player);
    public abstract void complete(EntityPlayer player);
    
    private static Challenge[] challenges = new Challenge[6];
    
    public static Challenge fromId(int id) {
        return challenges[id];
    }
}
