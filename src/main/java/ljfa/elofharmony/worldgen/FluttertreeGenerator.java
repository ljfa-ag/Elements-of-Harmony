package ljfa.elofharmony.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class FluttertreeGenerator extends WorldGenAbstractTree {
    protected Block log, leaves;
    
    public FluttertreeGenerator(boolean notify, Block log, Block leaves) {
        super(notify);
        if(log == null || leaves == null)
            throw new NullPointerException("Null passed as log or leaf block to FluttertreeGenerator");
        this.log = log;
        this.leaves = leaves;
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int aboveGround = 1 + rand.nextInt(3);
        
        if(!canTreeGrow(world, x, y, z, aboveGround + 3))
            return false;
        
        world.getBlock(x, y-1, z).onPlantGrow(world, x, y-1, z, x, y, z);
        
        //Place logs
        for(int yo = 0; yo <= aboveGround+2; yo++)
            setBlockAndNotifyAdequately(world, x, y+yo, z, log, 0);
        
        //Place leaves
        for(int xo = -2; xo <= 2; xo++)
        for(int zo = -2; zo <= 2; zo++)
            if(!(xo == 0 && zo == 0) && !(Math.abs(xo) == 2 && Math.abs(zo) == 2)) {
                placeLeaf(world, x+xo, y+aboveGround, z+zo);
                placeLeaf(world, x+xo, y+aboveGround+1, z+zo);
            }
        
        for(int xo = -1; xo <= 1; xo++)
        for(int zo = -1; zo <= 1; zo++) {
            if(!(xo == 0 && zo == 0))
                placeLeaf(world, x+xo, y+aboveGround+2, z+zo);
            if(xo == 0 || zo == 0)
                placeLeaf(world, x+xo, y+aboveGround+3, z+zo);
        }
        
        return true;
    }

    public void placeLeaf(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if(!block.isWood(world, x, y, z))
            setBlockAndNotifyAdequately(world, x, y, z, leaves, 0);
    }
    
    public boolean canTreeGrow(IBlockAccess world, int x, int y, int z, int height) {
        int maxRadius = 2;
        
        //Check logs
        for(int yo = 1; yo <= height; yo++) {
            Block block = world.getBlock(x, y+yo, z);
            if(!(block.isLeaves(world, x, y+yo, z) || block.isReplaceable(world, x, y+yo, z)))
                return false;
        }
        
        //Check leaves
        for(int yo = 1; yo <= height; yo++)
        for(int xo = -maxRadius; xo <= +maxRadius; xo++)
        for(int zo = -maxRadius; zo <= +maxRadius; zo++) {
            Block block = world.getBlock(x+xo, y+yo, z+zo);
            if(!(block.isLeaves(world, x+xo, y+yo, z+zo) || block.isWood(world, x+xo, y+yo, z+zo)
                    || block.isReplaceable(world, x+xo, y+yo, z+zo) || block.canBeReplacedByLeaves(world, x+xo, y+yo, z+zo)))
                return false;
        }
        
        return true;
    }
}
