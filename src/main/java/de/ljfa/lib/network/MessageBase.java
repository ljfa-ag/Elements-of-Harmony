package de.ljfa.lib.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import de.ljfa.lib.util.ClientUtils;

public abstract class MessageBase<T extends MessageBase<T>> implements IMessage, IMessageHandler<T, IMessage> {

    @Override
    public IMessage onMessage(T message, MessageContext ctx) {
        EntityPlayer player = ctx.side == Side.SERVER
                ? ctx.getServerHandler().playerEntity
                : ClientUtils.getPlayer();
        message.process(player, ctx.side);
        return null;
    }

    public abstract void process(EntityPlayer player, Side side);
}
