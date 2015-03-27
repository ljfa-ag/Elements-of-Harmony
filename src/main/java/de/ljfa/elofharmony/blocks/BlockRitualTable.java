package de.ljfa.elofharmony.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.elofharmony.util.LogHelper;

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
        TileEntity tile = world.getTileEntity(x, y, z);
        if(!world.isRemote) {
            if(tile instanceof TileRitualTable) {
                TileRitualTable te = (TileRitualTable)tile;
                te.spillItems();
            }
        }
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
            int side, float par7, float par8, float par9) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileRitualTable) {
            return ((TileRitualTable)tile).onPlayerInteract(player);
        } else {
            LogHelper.error("Missing or wrong tile entity at (%d,%d,%d)", x, y, z);
            return false;
        }
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return -1;
    }
}
