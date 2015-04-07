package de.ljfa.elofharmony.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.ljfa.elofharmony.tile.TileLocker;

public class BlockLocker extends BlockBase implements ITileEntityProvider {

    public BlockLocker() {
        super("locker", Material.wood);
        setHardness(2.0f);
        setResistance(20.0f);
        setStepSound(soundTypeWood);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileLocker();
    }

}
