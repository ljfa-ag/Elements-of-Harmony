package de.ljfa.elofharmony.challenges;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

import org.apache.logging.log4j.Level;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.ljfa.elofharmony.util.LogHelper;

/** This is a wrapper class for Challenge objects. Instances of this class get saved to the
 * ExtendedEntityProperties of each player.
 * Challenges have to be registered with the register method.
 */
public class ChallengeHolder implements IExtendedEntityProperties {

    /** Registers a Challenge class. The challenge must have a no-arguments constructor. */
    public static void register(Class<? extends Challenge> clazz, String name) {
        if(registry.containsKey(name))
            throw new RuntimeException("Duplicate entry for challenge ID " + name);
        registry.put(name, clazz);
    }
    
    /** @return the ChallengeHolder for the player. */
    public static ChallengeHolder get(EntityPlayerMP player) {
        return (ChallengeHolder)player.getExtendedProperties("eoh:Challenge");
    }
    
    /** Initializes the player with a ChallengeHolder. Gets called when the player entity gets constructed. */
    public static void initPlayer(EntityPlayerMP player) {
        player.registerExtendedProperties("eoh:Challenge", new ChallengeHolder(player));
    }
    
    /** @return the Challenge inside this holder */
    public Challenge getChallenge() {
        return challenge;
    }

    /** Sets the challenge for this holder. */
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
    
    /** Removes the challenge from this holder. */
    public void clearChallenge() {
        this.challenge = null;
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
    
    private ChallengeHolder(EntityPlayerMP player) {
        this.player = player;
    }
    
    private static BiMap<String, Class<? extends Challenge>> registry = HashBiMap.create();
    
    private EntityPlayerMP player; //This field is only used for loading challenges
    private Challenge challenge;
}
