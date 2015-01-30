package ljfa.elofharmony.challenges;

import ljfa.elofharmony.util.ChatHelper;
import ljfa.elofharmony.util.LjfaMathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ChallengeGenerosity extends ChallengeBase {
    public static final ChallengeGenerosity instance = new ChallengeGenerosity();
    
    private ChallengeGenerosity() {
        
    }
    
    public void start(EntityPlayer player) {
        World world = player.getEntityWorld();
        double mean = 300.0, sigma = 20.0;
        
        double dist = mean + sigma * LjfaMathHelper.stdTriangular(world.rand);
        double angle = 2 * Math.PI * world.rand.nextDouble();
        
        int tpx = (int)(player.posX + dist * Math.cos(angle));
        int tpz = (int)(player.posZ + dist * Math.sin(angle));
        int tpy = world.getTopSolidOrLiquidBlock(tpx, tpz);
        
        player.setPositionAndUpdate(tpx + 0.5, tpy, tpz + 0.5);
        ChatHelper.toPlayer(player, "Generostiy Challenge started!");
    }
}
