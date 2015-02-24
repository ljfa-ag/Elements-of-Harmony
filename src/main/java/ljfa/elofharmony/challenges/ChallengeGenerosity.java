package ljfa.elofharmony.challenges;

import ljfa.elofharmony.inventory.PlayerInvRestriction;
import ljfa.elofharmony.inventory.SlotRestriction;
import ljfa.elofharmony.inventory.SlotType;
import ljfa.elofharmony.items.ItemElement.ElementType;
import ljfa.elofharmony.items.ModItems;
import ljfa.elofharmony.tile.TileRitualTable;
import ljfa.elofharmony.util.ChatHelper;
import ljfa.elofharmony.util.LjfaMathHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ChallengeGenerosity extends Challenge {
    public ChallengeGenerosity(EntityPlayerMP player, TileRitualTable tile) {
        super(player);
        this.table = tile;
        
        this.invRestr = new PlayerInvRestriction(new SlotRestriction() {
            @Override
            public boolean check(SlotType type, int slot, ItemStack stack) {
                return stack == null || stack.getItem() == ModItems.twilicane;
            }
        });
    }
    
    @Override
    public boolean checkStartingCondition() {
        return invRestr.check(player.inventory);
    }

    @Override
    public boolean checkRestriction() {
        return !player.isDead && invRestr.check(player.inventory);
    }
    
    @Override
    public boolean checkCondition() {
        return player.worldObj == table.getWorldObj()
            && LjfaMathHelper.dist2sq(player, table.xCoord+0.5, table.yCoord+0.5, table.zCoord+0.5) <= 25.0;
    }
    
    @Override
    public void onStart() {
        World world = player.getEntityWorld();
        double mean = DEBUG_MODE ? 30.0 : 300.0;
        double sigma = DEBUG_MODE ? 2.0 : 20.0;
        
        double dist = mean + sigma * LjfaMathHelper.stdTriangular(world.rand);
        double angle = 2 * Math.PI * world.rand.nextDouble();
        
        int tpx = (int)(player.posX + dist * Math.cos(angle));
        int tpz = (int)(player.posZ + dist * Math.sin(angle));
        int tpy = world.getTopSolidOrLiquidBlock(tpx, tpz);
        
        float yaw = 360.0f * world.rand.nextFloat();

        if(!DEBUG_MODE) {
            player.worldObj.setWorldTime(14000);
            player.addPotionEffect(new PotionEffect(Potion.blindness.id, 200));
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 2));
        }
        player.playerNetServerHandler.setPlayerLocation(tpx + 0.5, tpy, tpz + 0.5, yaw, 0.0f);
        world.playSoundEffect(tpx + 0.5, tpy + 0.5, tpz + 0.5, "mob.endermen.portal", 1.0f, 1.0f);
        ChatHelper.toPlayer(player, "You have to go " + getCompassDirection(2*Math.PI - angle) + " in order to find back.");
        ChatHelper.toPlayer(player, "The moon rises in the East. Use it to ortientate yourself!");
    }
    
    @Override
    public void onTick() { }
    
    @Override
    public boolean mayPickUp(ItemStack stack) {
        return invRestr.mayPickUp(stack);
    }
    
    @Override
    public void onAbort() {
        table.endChallenge();
    }
    
    @Override
    public void onComplete() {
        table.setInventorySlotContents(0, new ItemStack(ModItems.elementOfHarmony, 1, ElementType.GENEROSITY.ordinal()));
        table.endChallenge();
    }
    
    private static String getCompassDirection(double angle) {
        if(angle < 0.125 * Math.PI)
            return "East";
        else if(angle < 0.375 * Math.PI)
            return "Northeast";
        else if(angle < 0.625 * Math.PI)
            return "North";
        else if(angle < 0.875 * Math.PI)
            return "Northwest";
        else if(angle < 1.125 * Math.PI)
            return "West";
        else if(angle < 1.375 * Math.PI)
            return "Southwest";
        else if(angle < 1.625 * Math.PI)
            return "South";
        else if(angle < 1.875 * Math.PI)
            return "Southeast";
        else
            return "East";
    }
    
    private static final boolean DEBUG_MODE = true;
    
    private final TileRitualTable table;
    private final PlayerInvRestriction invRestr;
}
