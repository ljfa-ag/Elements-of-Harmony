package de.ljfa.elofharmony.network;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import de.ljfa.elofharmony.Reference;
import de.ljfa.lib.tile.DescriptionPacketSynced;
import de.ljfa.lib.util.ClientUtils;

@Sharable
public class DescriptionPacketHandler extends SimpleChannelInboundHandler<FMLProxyPacket> {
    //Huh, apparently the channel name can't be longer than 20 characters
    public static final String CHANNEL = Reference.MODID + "Desc";
    
    public static void init() {
        NetworkRegistry.INSTANCE.newChannel(CHANNEL, new DescriptionPacketHandler());
    }
    
    public static <T extends TileEntity & DescriptionPacketSynced> FMLProxyPacket createDescPacket(T te) {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        BlockPos pos = te.getPos();
        buf.writeInt(pos.getX());
        buf.writeShort(pos.getY());
        buf.writeInt(pos.getZ());
        te.writeToPacket(buf);
        return new FMLProxyPacket(buf, CHANNEL);
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception {
        PacketBuffer buf = (PacketBuffer)msg.payload();
        int x = buf.readInt();
        int y = buf.readShort();
        int z = buf.readInt();
        TileEntity te = ClientUtils.getWorld().getTileEntity(new BlockPos(x, y, z));
        if(te instanceof DescriptionPacketSynced)
            ((DescriptionPacketSynced)te).readFromPacket(buf);
    }

}