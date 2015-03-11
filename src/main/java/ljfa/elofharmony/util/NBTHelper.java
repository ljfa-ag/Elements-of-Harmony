package ljfa.elofharmony.util;

import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {
    /** Writes the UUID into the compound, split into most and least significant bits.
     * For this purpose, "Most" and "Least" are appended to the given key. */
    public static void setUUID(NBTTagCompound tag, String key, UUID uuid) {
        tag.setLong(key + "Most", uuid.getMostSignificantBits());
        tag.setLong(key + "Least", uuid.getLeastSignificantBits());
    }
    
    /** Reds the UUID from the compound, split into most and least significant bits.
     * For this purpose, "Most" and "Least" are appended to the given key. */
    public static UUID getUUID(NBTTagCompound tag, String key) {
        return new UUID(tag.getLong(key + "Most"), tag.getLong(key + "Least"));
    }
}
