package ljfa.elofharmony.util;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class ChatHelper {
    public static void toPlayer(ICommandSender player, String msg) {
        player.addChatMessage(new ChatComponentText(msg));
    }
    
    public static void toPlayerLocalized(ICommandSender player, String key, Object... args) {
        player.addChatMessage(new ChatComponentTranslation(key, args));
    }
    
    public static void broadcast(String msg) {
        MinecraftServer.getServer().addChatMessage(new ChatComponentText(msg));
    }
    
    public static void broadcastLocalized(String key, Object... args) {
        MinecraftServer.getServer().addChatMessage(new ChatComponentTranslation(key, args));
    }
}
