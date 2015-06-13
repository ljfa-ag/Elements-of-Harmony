package de.ljfa.lib.chat;

import java.util.regex.Pattern;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class ChatHelper {
    /** Sends an unlocalized chat message to a player. */
    public static void toPlayer(ICommandSender player, String msg) {
        player.addChatMessage(new ChatComponentText(msg));
    }
    
    /** Sends an unlocalized chat message which can contain multiple lines to a player. */
    public static void toPlayerLines(ICommandSender player, String msg) {
        for(String line: patternNewline.split(msg))
            toPlayer(player, line);
    }
    
    /** Sends a localized chat message to a player. */
    public static void toPlayerLoc(ICommandSender player, String key, Object... args) {
        player.addChatMessage(new ChatComponentTranslation(key, args));
    }
    
    /** Broadcasts an unlocalized chat message. */
    public static void broadcast(String msg) {
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(msg));
    }
    
    /** Broadcasts an unlocalized chat message which can contain multiple lines. */
    public static void broadcastLines(String msg) {
        for(String line: patternNewline.split(msg))
            broadcast(line);
    }
    
    /** Broadcasts a localized chat message. */
    public static void broadcastLoc(String key, Object... args) {
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentTranslation(key, args));
    }
    
    /** Regex pattern that detects newlines (LF or CRLF) */
    public static final Pattern patternNewline = Pattern.compile("\\r?\\n");
}
