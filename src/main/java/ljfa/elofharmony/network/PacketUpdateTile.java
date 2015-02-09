package ljfa.elofharmony.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateTile implements IMessage {
    private int x, y, z;
    private NBTTagCompound tag;
    
    public PacketUpdateTile() { }
    
    public PacketUpdateTile(TileEntity tile) {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
        tag = new NBTTagCompound();
        tile.writeToNBT(tag);
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x).writeInt(y).writeInt(z);
        ByteBufUtils.writeTag(buf, tag);
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        tag = ByteBufUtils.readTag(buf);
    }
    
    public static class Handler implements IMessageHandler<PacketUpdateTile, IMessage> {
        @Override
        public IMessage onMessage(PacketUpdateTile message, MessageContext ctx) {
            
            return null;
        }
    }
}
