package de.ljfa.elofharmony.blocks;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Config;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.handlers.PoisonJokeHandler;

public class BlockPoisonJoke extends BlockBush implements IGrowable {
    private final String name = "poisonjoke";
    
    private final int maxGrowth = 7;
    
    public BlockPoisonJoke() {
        float f = 0.5F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
        ModBlocks.register(this, name);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
        super.onEntityCollidedWithBlock(world, pos, entity);
        if(world.isRemote || Config.pjPlayersOnly && !(entity instanceof EntityPlayer))
            return;
        else if(world.getBlockMetadata(x, y, z) >= 3)
            PoisonJokeHandler.startIncubation(world, entity);
    }
    
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }
    
    /** Increments growth stage by a certain amount */
    public void growBy(World world, BlockPos pos, int increment) {
        int growthStage = world.getBlockMetadata(x, y, z) + increment;
        if(growthStage > maxGrowth)
            growthStage = maxGrowth;
        world.setBlockMetadataWithNotify(x, y, z, growthStage, 2);
    }
    
    /** Called when bone meal is used */
    public void incrementGrowthStage(World world, Random rand, BlockPos pos) {
        growBy(world, pos, MathHelper.getRandomIntegerInRange(rand, 1, 4));
    }
    
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);
        growBy(world, pos, 1);
    }
    
    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        if(meta == maxGrowth)
            return MathHelper.getRandomIntegerInRange(random, 1, 3 + fortune);
        else
            return 1;
    }
    
    /** Is the plant not fully grown yet? */
    @Override
    public boolean isStillGrowing(World world, BlockPos pos, IBlockState state, boolean par5) {
        return world.getBlockMetadata(x, y, z) != maxGrowth;
    }

    /** Can bone meal be used on this plant? */
    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    /** Increment growth stage */
    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        incrementGrowthStage(world, rand, pos);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return 1; //Crossed squares
    }

}
