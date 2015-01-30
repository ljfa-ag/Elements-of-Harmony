package ljfa.elofharmony.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class ChatHelper {
    public static void toPlayer(EntityPlayer player, String msg) {
        player.addChatMessage(new ChatComponentText(msg));
    }
}
