package de.ljfa.elofharmony.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.challenges.impl.ChallengeHandler;
import de.ljfa.lib.chat.ChatHelper;

public class ItemTwilicane extends Item {
    public ItemTwilicane() {
        ModItems.register(this, "twilicane");
        setMaxStackSize(1);
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player,
            World world, int x, int y, int z, int side, float fx, float fy, float fz) {
        if(world.isRemote)
            return true;
        if(player.isSneaking()) {
            //Abort current challenge
            if(ChallengeHandler.tryAbortChallenge(player)) {
                return true;
            }
            else {
                ChatHelper.toPlayerLoc(player, "elofharmony.challenge.no_challenge");
                return false;
            }
        }
        else
            return false;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if(!world.isRemote && player.isSneaking()) {
            //Abort current challenge
            if(!ChallengeHandler.tryAbortChallenge(player))
                ChatHelper.toPlayerLoc(player, "elofharmony.challenge.no_challenge");
        }
        return super.onItemRightClick(stack, world, player);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {
        info.add(StatCollector.translateToLocal("elofharmony.challenge.twilicane_tooltip"));
    }
}
