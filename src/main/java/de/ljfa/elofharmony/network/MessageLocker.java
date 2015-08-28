package de.ljfa.elofharmony.network;

import de.ljfa.elofharmony.gui.ContainerLocker;
import de.ljfa.lib.network.SynchronizedMessageBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public class MessageLocker extends SynchronizedMessageBase<MessageLocker> {
    
    public MessageLocker() { }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
    }

    @Override
    public void toBytes(ByteBuf buf) {
        
    }

    @Override
    public void handleServer(EntityPlayerMP player) {
        Container cont = player.openContainer;
        if(cont instanceof ContainerLocker)
            ((ContainerLocker)cont).swapWithPlayer();
    }
}
