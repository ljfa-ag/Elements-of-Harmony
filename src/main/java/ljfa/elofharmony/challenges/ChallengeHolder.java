package ljfa.elofharmony.challenges;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.Level;

import ljfa.elofharmony.util.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

public class ChallengeHolder implements IExtendedEntityProperties {
    
    @Override
    public void saveNBTData(NBTTagCompound tag) {
        if(challenge != null) {
            NBTTagCompound chTag = new NBTTagCompound();
            
            challenge.writeToNBT(chTag);
            chTag.setString("ClassName", challenge.getClass().getName());
            
            tag.setTag("eoh:Challenge", chTag);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound tag) {
        if(tag.hasKey("eoh:Challenge", Constants.NBT.TAG_COMPOUND)) {
            NBTTagCompound chTag = tag.getCompoundTag("eoh:Challenge");
            
            String className = chTag.getString("ClassName");
            try {
                Constructor cstr = Class.forName(className).getConstructor(EntityPlayerMP.class, NBTTagCompound.class);
                challenge = (Challenge)cstr.newInstance(null, chTag);
                LogHelper.info("Successfully created instance of %s", className);
            }
            catch(ReflectiveOperationException ex) {
                challenge = null;
                LogHelper.log(Level.ERROR, ex, "Failed to create instance of %s", className);
            }
        }
    }

    @Override
    public void init(Entity entity, World world) {
        if(challenge != null)
            challenge.setPlayer((EntityPlayerMP)entity);
    }
    
    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
    
    public void clearChallenge() {
        this.challenge = null;
    }

    private Challenge challenge;
}
