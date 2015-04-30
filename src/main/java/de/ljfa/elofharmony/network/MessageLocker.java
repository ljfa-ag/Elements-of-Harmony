package de.ljfa.elofharmony.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import cpw.mods.fml.relauncher.Side;
import de.ljfa.elofharmony.inventory.ContainerLocker;
import de.ljfa.lib.network.MessageBase;

public class MessageLocker extends MessageBase<MessageLocker> {
    
    public MessageLocker() { }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
    }

    @Override
    public void toBytes(ByteBuf buf) {
        
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        Container cont = player.openContainer;
        if(cont instanceof ContainerLocker)
            ((ContainerLocker)cont).swapWithPlayer();
    }
    
}
