package de.ljfa.elofharmony.challenges;

import static de.ljfa.elofharmony.ElementsOfHarmony.logger;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.items.ItemHelper;
import de.ljfa.lib.math.DimPos;

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
    public void onFail() {
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
            ItemHelper.dropItem(tablePos, result);
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
    
    @Override
    public String toString() {
        return super.toString() + "\nTable position: " + tablePos;
    }

    protected DimPos tablePos;
    
    protected abstract ItemStack getResult();
    
    protected TileRitualTable getTable() {
        TileEntity tile = tablePos.getTile();
        if(tile instanceof TileRitualTable)
            return (TileRitualTable)tile;
        else {
            logger.warn("Missing or wrong tile entity at " + tablePos);
            return null;
        }
    }
}
