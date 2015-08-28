package de.ljfa.lib.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Like MessageBase, but the processing of the messages is scheduled as task
 * on either the Server or Client thread, as opposed to a Networking thread,
 * making the processing thread safe.
 */
public abstract class SynchronizedMessageBase<T extends SynchronizedMessageBase<T>> extends MessageBase<T> {

    @Override
    public IMessage onMessage(T message, MessageContext ctx) {
        if(ctx.side == Side.SERVER)
            enqueueServer(ctx.getServerHandler().playerEntity);
        else
            enqueueClient();
        return null;
    }

    private void enqueueServer(final EntityPlayerMP player) {
        ((WorldServer)player.worldObj).addScheduledTask(new Runnable() {
            @Override
            public void run() {
                handleServer(player);
            }
        });
    }
    
    private void enqueueClient() {
        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
            @Override
            public void run() {
                handleClient();
            }
        });
    }
}
