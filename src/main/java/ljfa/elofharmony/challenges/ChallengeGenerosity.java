package ljfa.elofharmony.challenges;

import ljfa.elofharmony.items.ItemElement.ElementType;
import ljfa.elofharmony.items.ModItems;
import ljfa.elofharmony.tile.TileRitualTable;
import ljfa.elofharmony.util.LjfaMathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ChallengeGenerosity extends Challenge {
    public static final ChallengeGenerosity instance = new ChallengeGenerosity();
    
    private ChallengeGenerosity() {
        super(4);
    }
    
    @Override
    public boolean checkStartingCondition(EntityPlayer player, TileRitualTable tile) {
        return true;
    }
    
    @Override
    public void start(EntityPlayer player, NBTTagCompound data, TileRitualTable tile) {
        World world = player.getEntityWorld();
        //double mean = 300.0, sigma = 20.0;
        double mean = 30.0, sigma = 2.0;
        
        double dist = mean + sigma * LjfaMathHelper.stdTriangular(world.rand);
        double angle = 2 * Math.PI * world.rand.nextDouble();
        
        int tpx = (int)(player.posX + dist * Math.cos(angle));
        int tpz = (int)(player.posZ + dist * Math.sin(angle));
        int tpy = world.getTopSolidOrLiquidBlock(tpx, tpz);
        
        player.setPositionAndUpdate(tpx + 0.5, tpy, tpz + 0.5);
    }

    @Override
    public boolean checkRestriction(EntityPlayer player, NBTTagCompound data) {
        return true;
    }
    
    @Override
    public boolean checkCondition(EntityPlayer player, NBTTagCompound data) {
        return LjfaMathHelper.dist2sq(player.posX, player.posY, player.posZ,
                data.getInteger("tableX"), data.getInteger("tableY"), data.getInteger("tableZ")) <= 25.0;
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
    
    @Override
    public ItemStack getResult() {
        return new ItemStack(ModItems.elementOfHarmony, 1, ElementType.GENEROSITY.ordinal());
    }
}
