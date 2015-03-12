package ljfa.elofharmony.items;

import java.util.List;

import ljfa.elofharmony.handlers.ChallengeHandler;
import ljfa.elofharmony.util.ChatHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
            if(ChallengeHandler.getInstance().tryAbortChallenge(player)) {
                return true;
            }
            else {
                ChatHelper.toPlayer(player, "You have no challenge running at the moment.");
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
            if(!ChallengeHandler.getInstance().tryAbortChallenge(player))
                ChatHelper.toPlayer(player, "You have no challenge running at the moment.");
        }
        return super.onItemRightClick(stack, world, player);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {
        info.add("Sneak+Right click to abort the current challenge");
    }
}
