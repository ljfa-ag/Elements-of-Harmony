package ljfa.elofharmony.challenges;

import net.minecraft.entity.player.EntityPlayer;

public abstract class Challenge {
    public abstract boolean start(EntityPlayer player);
    public abstract boolean checkRestriction(EntityPlayer player);
    public abstract boolean checkCondition(EntityPlayer player);
    public abstract void tick(EntityPlayer player);
    public abstract void abort(EntityPlayer player);
}
