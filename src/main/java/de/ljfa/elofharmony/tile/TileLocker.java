package de.ljfa.elofharmony.tile;

import de.ljfa.lib.tile.TileInventoryBase;

public class TileLocker extends TileInventoryBase {
    /* Slot indexes:
     *  0-35: inventory
     * 36-39: armor
     */
    
    public TileLocker() {
        super(36 + 4);
    }

    @Override
    public String getInventoryName() {
        return "TileLocker";
    }

}
