package de.ljfa.lib.tile;

import net.minecraft.network.PacketBuffer;
import io.netty.buffer.ByteBuf;

/** To be implemented by tile entities that need synchronization, using a FMLProxyPacket */
public interface DescriptionPacketSynced {
    public void writeToPacket(PacketBuffer buf);
    public void readFromPacket(PacketBuffer buf);
}
