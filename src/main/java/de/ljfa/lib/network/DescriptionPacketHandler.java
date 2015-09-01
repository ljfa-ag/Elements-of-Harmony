package de.ljfa.lib.network;

import de.ljfa.lib.tile.DescriptionPacketSynced;
import de.ljfa.lib.util.ClientUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

/**
 * A packet handler for custom tile entity description packets.
 * 
 * This handler handles incoming FMLProxyPackets from tile entities, which can
 * be used in place of vanilla S35PacketUpdateTileEntity, allowing for slimmer
 * packets and more flexibility.
 */
@Sharable
public class DescriptionPacketHandler extends SimpleChannelInboundHandler<FMLProxyPacket> {
    public final String channel;
    
    /**  @param channel The channel name. Maximum 20 characters. */
    public DescriptionPacketHandler(String channel) {
        this.channel = channel;
    }
    
    /** Registers the handler to the NetworkRegistry */
    public void preInit() {
        NetworkRegistry.INSTANCE.newChannel(channel, this);
    }
    
    /**
     * Creates a new FMLProxyPacket from a tile entity that implements DescriptionPacketSynced
     * @return a packet which can be used as return value of getDescriptionPacket()
     */
    public <T extends TileEntity & DescriptionPacketSynced> FMLProxyPacket createDescPacket(T te) {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        BlockPos pos = te.getPos();
        buf.writeInt(pos.getX());
        buf.writeShort(pos.getY());
        buf.writeInt(pos.getZ());
        te.writeToPacket(buf);
        return new FMLProxyPacket(buf, channel);
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception {
        final PacketBuffer buf = (PacketBuffer)msg.payload();
        int x = buf.readInt();
        int y = buf.readShort();
        int z = buf.readInt();
        final BlockPos pos = new BlockPos(x, y, z);
        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
            @Override
            public void run() {
                TileEntity te = ClientUtils.getWorld().getTileEntity(pos);
                if(te instanceof DescriptionPacketSynced)
                    ((DescriptionPacketSynced)te).readFromPacket(buf);
            }
        });
    }

}
