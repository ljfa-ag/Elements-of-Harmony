package de.ljfa.lib.tile;

import io.netty.buffer.ByteBuf;

/** To be implemented by tile entities that need synchronization, using a FMLProxyPacket */
public interface DescriptionPacketSynced {
    public void writeToPacket(ByteBuf buf);
    public void readFromPacket(ByteBuf buf);
}
