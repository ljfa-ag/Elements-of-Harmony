package de.ljfa.elofharmony.network;

import de.ljfa.elofharmony.gui.ContainerLocker;
import de.ljfa.lib.network.MessageBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;

public class MessageLocker extends MessageBase<MessageLocker> {
    
    public MessageLocker() { }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        
    }

    @Override
    public void toBytes(ByteBuf buf) {
        
    }

    @Override
    public void process(final EntityPlayer player, Side side) {
        ((WorldServer)player.worldObj).addScheduledTask(new Runnable() {
            @Override
            public void run() {
                Container cont = player.openContainer;
                if(cont instanceof ContainerLocker)
                    ((ContainerLocker)cont).swapWithPlayer();
            }
        });
    }
    
}
