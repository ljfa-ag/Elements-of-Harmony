package de.ljfa.elofharmony.blocks;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Config;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.handlers.PoisonJokeHandler;

public class BlockPoisonJoke extends BlockBush implements IGrowable {
    private final String name = "poisonjoke";
    
    private final int maxGrowth = 7;

    @SideOnly(Side.CLIENT)
    private IIcon[] textures;
    
    public BlockPoisonJoke() {
        float f = 0.5F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
        ModBlocks.register(this, name);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        super.onEntityCollidedWithBlock(world, x, y, z, entity);
        if(world.isRemote || Config.pjPlayersOnly && !(entity instanceof EntityPlayer))
            return;
        else if(world.getBlockMetadata(x, y, z) >= 3)
            PoisonJokeHandler.startIncubation(world, entity);
    }
    
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }
    
    /** Increments growth stage by a certain amount */
    public void growBy(World world, int x, int y, int z, int increment) {
        int growthStage = world.getBlockMetadata(x, y, z) + increment;
        if(growthStage > maxGrowth)
            growthStage = maxGrowth;
        world.setBlockMetadataWithNotify(x, y, z, growthStage, 2);
    }
    
    /** Called when bone meal is used */
    public void incrementGrowthStage(World world, Random rand, int x, int y, int z) {
        growBy(world, x, y, z, MathHelper.getRandomIntegerInRange(rand, 1, 4));
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        growBy(world, x, y, z, 1);
    }
    
    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        if(meta == maxGrowth)
            return MathHelper.getRandomIntegerInRange(random, 1, 3 + fortune);
        else
            return 1;
    }
    
    /** Is the plant not fully grown yet? */
    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean par5) {
        return world.getBlockMetadata(x, y, z) != maxGrowth;
    }

    /** Can bone meal be used on this plant? */
    @Override
    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return true;
    }

    /** Increment growth stage */
    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        incrementGrowthStage(world, rand, x, y, z);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return 1; //Crossed squares
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        textures = new IIcon[3];
        for(int i = 0; i < textures.length; i++)
            textures[i] = iconRegister.registerIcon(Reference.MODID + ":poisonjoke_stage_" + i);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if(meta < 3)
            return textures[0];
        else if(meta < 7)
            return textures[1];
        else
            return textures[2];
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public String getItemIconName() {
        return Reference.MODID + ":poisonjoke";
    }
}
