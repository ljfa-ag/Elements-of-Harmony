package de.ljfa.elofharmony.worldgen;

import java.util.Objects;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class FluttertreeGenerator extends WorldGenAbstractTree {
    private IBlockState log, leaves;
    
    public FluttertreeGenerator(boolean notify, IBlockState log, IBlockState leaves) {
        super(notify);
        this.log = Objects.requireNonNull(log, "The log must not be null");
        this.leaves = Objects.requireNonNull(leaves, "The leaves must not be null");
    }
    
    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int aboveGround = 1 + rand.nextInt(3);
        
        if(!canTreeGrow(world, pos, aboveGround + 3))
            return false;
        
        world.getBlockState(pos.offsetDown()).getBlock().onPlantGrow(world, pos.offsetDown(), pos);
        
        //Place logs
        for(int yo = 0; yo <= aboveGround+2; yo++)
            func_175903_a(world, pos.offsetUp(yo), log);
        
        //Place leaves
        for(int xo = -2; xo <= 2; xo++)
        for(int zo = -2; zo <= 2; zo++)
            if(!(xo == 0 && zo == 0) && !(Math.abs(xo) == 2 && Math.abs(zo) == 2)) {
                placeLeaf(world, pos.add(xo, aboveGround, zo));
                placeLeaf(world, pos.add(xo, aboveGround+1, zo));
            }
        
        for(int xo = -1; xo <= 1; xo++)
        for(int zo = -1; zo <= 1; zo++) {
            if(!(xo == 0 && zo == 0))
                placeLeaf(world, pos.add(xo, aboveGround+2, zo));
            if(xo == 0 || zo == 0)
                placeLeaf(world, pos.add(xo, aboveGround+3, zo));
        }
        
        return true;
    }

    public void placeLeaf(World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        if(!block.isWood(world, pos))
            func_175903_a(world, pos, leaves);
    }
    
    public boolean canTreeGrow(World world, BlockPos pos, int height) {
        int maxRadius = 2;
        
        //Check logs
        for(int yo = 1; yo <= height; yo++) {
            BlockPos offpos = pos.offsetUp(yo);
            Block block = world.getBlockState(offpos).getBlock();
            if(!(block.isLeaves(world, offpos) || block.isReplaceable(world, offpos)))
                return false;
        }
        
        //Check leaves
        for(int yo = 1; yo <= height; yo++)
        for(int xo = -maxRadius; xo <= +maxRadius; xo++)
        for(int zo = -maxRadius; zo <= +maxRadius; zo++) {
            BlockPos offpos = pos.add(xo, yo, zo);
            Block block = world.getBlockState(offpos).getBlock();
            if(!(block.isLeaves(world, offpos) || block.isWood(world, offpos)
                    || block.isReplaceable(world, offpos) || block.canBeReplacedByLeaves(world, offpos)))
                return false;
        }
        
        return true;
    }
}
