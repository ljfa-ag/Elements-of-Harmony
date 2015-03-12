package ljfa.elofharmony.challenges;

import ljfa.elofharmony.util.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

import org.apache.logging.log4j.Level;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ChallengeHolder implements IExtendedEntityProperties {
    
    private static BiMap<String, Class<? extends Challenge>> registry = HashBiMap.create();
    
    public static void register(Class<? extends Challenge> clazz, String name) {
        if(registry.containsKey(name))
            throw new RuntimeException("Duplicate entry for challenge ID " + name);
        registry.put(name, clazz);
    }
    
    public ChallengeHolder(EntityPlayerMP player) {
        this.player = player;
    }
    
    @Override
    public void saveNBTData(NBTTagCompound tag) {
        if(challenge != null) {
            String chName = registry.inverse().get(challenge.getClass());
            if(chName == null)
                throw new RuntimeException("The class " + challenge.getClass() + " has not been registered as challenge!");
            NBTTagCompound chTag = new NBTTagCompound();
            chTag.setString("ID", chName);
            challenge.writeToNBT(chTag);
            tag.setTag("eoh:Challenge", chTag);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound tag) {
        if(tag.hasKey("eoh:Challenge", Constants.NBT.TAG_COMPOUND)) {
            NBTTagCompound chTag = tag.getCompoundTag("eoh:Challenge");
            String chName = chTag.getString("ID");
            Class<? extends Challenge> clazz = registry.get(chName);
            if(clazz != null) {
                try {
                    challenge = clazz.newInstance();
                    challenge.setPlayer(player);
                    challenge.readFromNBT(chTag);
                }
                catch(Exception e) {
                    LogHelper.log(Level.ERROR, e, "Failed to create challenge instance for " + chName);
                    challenge = null;
                }
                player = null;
            }
            else
                LogHelper.warn("Unknown challenge ID: %s", chName);
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

    private EntityPlayerMP player; //This field is only used for loading challenges
    private Challenge challenge;
}
