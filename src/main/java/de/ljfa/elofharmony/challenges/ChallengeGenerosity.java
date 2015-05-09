package de.ljfa.elofharmony.challenges;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import de.ljfa.elofharmony.items.ItemElement.ElementType;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.chat.ChatHelper;
import de.ljfa.lib.inventory.FullInvRestriction;
import de.ljfa.lib.inventory.PlayerSlotRestriction;
import de.ljfa.lib.inventory.PlayerSlotType;
import de.ljfa.lib.math.MathUtils;
import de.ljfa.lib.math.Metric;
import de.ljfa.lib.math.MetricHelper;

public class ChallengeGenerosity extends TableChallenge {

    public ChallengeGenerosity(EntityPlayerMP player, TileRitualTable tile) {
        super(player, tile);
    }
    
    public ChallengeGenerosity() { }
    
    @Override
    public boolean checkStartingCondition() {
        if(invRestr.check(player)) {
            return true;
        } else {
            ChatHelper.toPlayerLoc(player, "elofharmony.challenge.generosity.no_items_allowed");
            return false;
        }
    }

    @Override
    public boolean checkRestriction() {
        return running && invRestr.check(player);
    }
    
    @Override
    public boolean checkCondition() {
        if(player.ticksExisted % 16 == 0)
            return MetricHelper.d(Metric.l2sq, player, tablePos) <= 25.0;
        else
            return false;
    }
    
    @Override
    public void onStart() {
        World world = player.getEntityWorld();
        double mean = 300.0, sigma = 20.0;
        //double mean = 30.0, sigma = 2.0;
        
        double dist = mean + sigma * MathUtils.stdTriangular(world.rand);
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
        
        for(int i = 0; i < 3; i++)
            ChatHelper.toPlayerLoc(player, "elofharmony.challenge.generosity.start" + i);
    }
    
    @Override
    public void onTick() { }
    
    @Override
    public boolean mayPickUp(ItemStack stack) {
        return invRestr.mayPickUp(player, stack);
    }
    
    @Override
    public void onAbort() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.failed");
        super.onAbort();
    }
    
    @Override
    public void onComplete() {
        ChatHelper.toPlayerLoc(player, "elofharmony.challenge.success");
        super.onComplete();
    }
    
    @Override
    public void onPlayerDeath(LivingDeathEvent event) {
        running = false;
    }
    
    @Override
    protected ItemStack getResult() {
        return new ItemStack(ModItems.elementOfHarmony, 1, ElementType.GENEROSITY.ordinal());
    }
    
    private boolean running = true;
    
    private static final FullInvRestriction invRestr = new FullInvRestriction(new PlayerSlotRestriction() {
        @Override
        public boolean check(PlayerSlotType type, int slot, ItemStack stack) {
            return stack == null || stack.getItem() == ModItems.twilicane;
        }
    });
}
