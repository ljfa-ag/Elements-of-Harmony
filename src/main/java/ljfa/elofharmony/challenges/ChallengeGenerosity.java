package ljfa.elofharmony.challenges;

import ljfa.elofharmony.inventory.FullInvRestriction;
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
    }
    
    @Override
    public boolean checkStartingCondition() {
        if(invRestr.check(player)) {
            return true;
        } else {
            ChatHelper.toPlayer(player, "In order to start the Generosity challenge you're not allowed "
                + "to have any items on you besides the Twilicane");
            return false;
        }
    }

    @Override
    public boolean checkRestriction() {
        return !player.isDead && invRestr.check(player);
    }
    
    @Override
    public boolean checkCondition() {
        return player.worldObj == table.getWorldObj()
            && LjfaMathHelper.dist2sq(player, table.xCoord+0.5, table.yCoord+0.5, table.zCoord+0.5) <= 25.0;
    }
    
    @Override
    public void onStart() {
        World world = player.getEntityWorld();
        double mean = 300.0, sigma = 20.0;
        //double mean = 30.0, sigma = 2.0;
        
        double dist = mean + sigma * LjfaMathHelper.stdTriangular(world.rand);
        double angle = 2 * Math.PI * world.rand.nextDouble();
        
        int tpx = (int)(player.posX + dist * Math.cos(angle));
        int tpz = (int)(player.posZ + dist * Math.sin(angle));
        int tpy = world.getTopSolidOrLiquidBlock(tpx, tpz);
        
        float yaw = 360.0f * world.rand.nextFloat();

        player.worldObj.setWorldTime(14000);
        player.addPotionEffect(new PotionEffect(Potion.blindness.id, 200));
        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 2));
        player.playerNetServerHandler.setPlayerLocation(tpx + 0.5, tpy, tpz + 0.5, yaw, 0.0f);
        world.playSoundEffect(tpx + 0.5, tpy + 0.5, tpz + 0.5, "mob.endermen.portal", 1.0f, 1.0f);
        
        ChatHelper.toPlayer(player, "The Generosity challenge is on!");
        ChatHelper.toPlayer(player, "You have to return to the Harmony Table without dying.");
        ChatHelper.toPlayer(player, "You cannot pick up any items during the challenge.");
    }
    
    @Override
    public void onTick() { }
    
    @Override
    public boolean mayPickUp(ItemStack stack) {
        return invRestr.mayPickUp(player, stack);
    }
    
    @Override
    public void onAbort() {
        ChatHelper.toPlayer(player, "You failed the challenge!");
        table.endChallenge();
    }
    
    @Override
    public void onComplete() {
        ChatHelper.toPlayer(player, "Congratulations, you completed the challenge!");
        table.setInventorySlotContents(0, new ItemStack(ModItems.elementOfHarmony, 1, ElementType.GENEROSITY.ordinal()));
        table.endChallenge();
    }
    
    private final TileRitualTable table;
    
    private static final FullInvRestriction invRestr = new FullInvRestriction(new SlotRestriction() {
        @Override
        public boolean check(SlotType type, int slot, ItemStack stack) {
            return stack == null || stack.getItem() == ModItems.twilicane;
        }
    });
}
