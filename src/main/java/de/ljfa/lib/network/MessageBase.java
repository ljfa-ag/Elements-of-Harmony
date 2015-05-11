package de.ljfa.lib.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
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
