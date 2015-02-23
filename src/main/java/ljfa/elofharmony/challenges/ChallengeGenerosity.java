package ljfa.elofharmony.challenges;

import ljfa.elofharmony.items.ItemElement.ElementType;
import ljfa.elofharmony.items.ModItems;
import ljfa.elofharmony.tile.TileRitualTable;
import ljfa.elofharmony.util.LjfaMathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ChallengeGenerosity extends Challenge {
    public ChallengeGenerosity(EntityPlayerMP player, TileRitualTable tile) {
        super(player);
        this.table = tile;
    }
    
    @Override
    public boolean checkStartingCondition() {
        return true;
    }

    @Override
    public boolean checkRestriction() {
        return !player.isDead;
    }
    
    @Override
    public boolean checkCondition() {
        return player.worldObj == table.getWorldObj()
            && LjfaMathHelper.dist2sq(player, table.xCoord+0.5, table.yCoord+0.5, table.zCoord+0.5) <= 25.0;
    }
    
    @Override
    public void onStart() {
        World world = player.getEntityWorld();
        //double mean = 300.0, sigma = 20.0;
        double mean = 30.0, sigma = 2.0;
        
        double dist = mean + sigma * LjfaMathHelper.stdTriangular(world.rand);
        double angle = 2 * Math.PI * world.rand.nextDouble();
        
        int tpx = (int)(player.posX + dist * Math.cos(angle));
        int tpz = (int)(player.posZ + dist * Math.sin(angle));
        int tpy = world.getTopSolidOrLiquidBlock(tpx, tpz);
        
        float yaw = 360.0f * world.rand.nextFloat();

        player.playerNetServerHandler.setPlayerLocation(tpx + 0.5, tpy, tpz + 0.5, yaw, 0.0f);
        world.playSoundEffect(tpx + 0.5, tpy + 0.5, tpz + 0.5, "mob.endermen.portal", 1.0f, 1.0f);
        //player.worldObj.setWorldTime(18000);
    }
    
    @Override
    public void onTick() { }
    
    @Override
    public void onAbort() {
        table.endChallenge();
    }
    
    @Override
    public void onComplete() {
        table.setInventorySlotContents(0, new ItemStack(ModItems.elementOfHarmony, 1, ElementType.GENEROSITY.ordinal()));
        table.endChallenge();
    }
    
    private TileRitualTable table;
}
