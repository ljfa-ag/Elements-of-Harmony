package ljfa.elofharmony.challenges;

import ljfa.elofharmony.util.ChatHelper;
import ljfa.elofharmony.util.LjfaMathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ChallengeGenerosity extends Challenge {
    public static final ChallengeGenerosity instance = new ChallengeGenerosity();
    
    private ChallengeGenerosity() {
        super(4);
    }
    
    public boolean start(EntityPlayer player, NBTTagCompound data) {
        World world = player.getEntityWorld();
        double mean = 300.0, sigma = 20.0;
        
        double dist = mean + sigma * LjfaMathHelper.stdTriangular(world.rand);
        double angle = 2 * Math.PI * world.rand.nextDouble();
        
        int tpx = (int)(player.posX + dist * Math.cos(angle));
        int tpz = (int)(player.posZ + dist * Math.sin(angle));
        int tpy = world.getTopSolidOrLiquidBlock(tpx, tpz);
        
        player.setPositionAndUpdate(tpx + 0.5, tpy, tpz + 0.5);
        ChatHelper.toPlayer(player, "Generostiy Challenge started!");
        
        return true;
    }

    @Override
    public boolean checkRestriction(EntityPlayer player, NBTTagCompound data) {
        return true;
    }
    
    @Override
    public boolean checkCondition(EntityPlayer player, NBTTagCompound data) {
        return false;
    }

    @Override
    public void tick(EntityPlayer player, NBTTagCompound data) {
        
    }

    @Override
    public void abort(EntityPlayer player, NBTTagCompound data) {
        
    }
    
    @Override
    public void complete(EntityPlayer player, NBTTagCompound data) {
        
    }
}
