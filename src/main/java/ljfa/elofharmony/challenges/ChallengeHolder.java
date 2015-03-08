package ljfa.elofharmony.challenges;

import ljfa.elofharmony.util.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ChallengeHolder implements IExtendedEntityProperties {

    public Challenge challenge;
    
    @Override
    public void saveNBTData(NBTTagCompound tag) {
        LogHelper.info("Saved challenge data");
    }

    @Override
    public void loadNBTData(NBTTagCompound tag) {
        LogHelper.info("Loaded challenge data");
    }

    @Override
    public void init(Entity entity, World world) {
        LogHelper.info("Initialized challenge data");
    }
}
