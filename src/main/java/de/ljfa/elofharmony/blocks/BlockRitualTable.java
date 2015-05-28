package de.ljfa.elofharmony.blocks;

import static de.ljfa.elofharmony.ElementsOfHarmony.logger;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.ljfa.elofharmony.ElementsOfHarmony;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.inventory.InvUtils;

public class BlockRitualTable extends BlockBase implements ITileEntityProvider {
    public BlockRitualTable() {
        super("ritual_table", Material.wood);
        setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.75f, 1.0f);
        setHardness(2.0f);
        setResistance(20.0f);
        setStepSound(soundTypeWood);
        setBlockTextureName(Reference.MODID + ":planks_flutter");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileRitualTable();
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if(!world.isRemote)
            InvUtils.spillInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
            int side, float hitX, float hitY, float hitZ) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileRitualTable) {
            return ((TileRitualTable)tile).onPlayerInteract(player);
        } else {
            logger.error("Missing or wrong tile entity at (%d,%d,%d)", x, y, z);
            return false;
        }
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @Override
    public int getRenderType() {
        return -1;
    }
}
