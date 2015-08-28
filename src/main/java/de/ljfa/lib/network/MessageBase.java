package de.ljfa.lib.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/** Base class for simple packets */
public abstract class MessageBase<T extends MessageBase<T>> implements IMessage, IMessageHandler<T, IMessage> {

    @Override
    public IMessage onMessage(T message, MessageContext ctx) {
        if(ctx.side == Side.SERVER)
            message.handleServer(ctx.getServerHandler().playerEntity);
        else
            message.handleClient();
        return null;
    }

    public void handleClient() {}
    public void handleServer(EntityPlayerMP player) {}
}
