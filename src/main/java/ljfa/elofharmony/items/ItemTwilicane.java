package ljfa.elofharmony.items;

import ljfa.elofharmony.handlers.ChallengeHandler;
import ljfa.elofharmony.tile.TileRitualTable;
import ljfa.elofharmony.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileRitualTable)
            return ((TileRitualTable)tile).startChallenge(player);
        else
            return false;
    }
}
