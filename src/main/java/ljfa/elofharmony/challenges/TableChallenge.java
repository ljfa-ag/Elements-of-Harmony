package ljfa.elofharmony.challenges;

import de.ljfa.lib.math.DimPos;
import ljfa.elofharmony.tile.TileRitualTable;
import ljfa.elofharmony.util.LogHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/** Base class for challenges that use the Ritual Table */
public abstract class TableChallenge extends Challenge {

    public TableChallenge(EntityPlayerMP player, TileRitualTable tile) {
        super(player);
        this.tablePos = DimPos.fromTile(tile);
    }
    
    public TableChallenge() { }
    @Override
    public void onAbort() {
        TileRitualTable table = getTable();
        if(table != null)
            table.onChallengeEnded();
    }
    
    @Override
    public void onComplete() {
        TileRitualTable table = getTable();
        ItemStack result = getResult();
        if(table != null) {
            //Put item into table
            table.setInventorySlotContents(0, result);
            table.onChallengeEnded();
        }
        else {
            //Drop item on ground if table is not present
            EntityItem entity = new EntityItem(tablePos.getWorld(), tablePos.x+0.5, tablePos.y+0.5, tablePos.z+0.5, result);
            tablePos.getWorld().spawnEntityInWorld(entity);
        }
        player.worldObj.playSoundAtEntity(player, "random.levelup", 1.0f, 1.0f);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setIntArray("TablePos", tablePos.toArray());
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        this.tablePos = DimPos.fromArray(tag.getIntArray("TablePos"));
    }

    protected DimPos tablePos;
    
    protected abstract ItemStack getResult();
    
    protected TileRitualTable getTable() {
        TileEntity tile = tablePos.getTile();
        if(tile instanceof TileRitualTable)
            return (TileRitualTable)tile;
        else {
            LogHelper.warn("Missing or wrong tile entity at " + tablePos);
            return null;
        }
    }
}
