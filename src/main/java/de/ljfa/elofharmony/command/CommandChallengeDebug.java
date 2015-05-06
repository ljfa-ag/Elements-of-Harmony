package de.ljfa.elofharmony.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import de.ljfa.elofharmony.challenges.Challenge;
import de.ljfa.elofharmony.challenges.impl.ChallengeHandler;
import de.ljfa.lib.chat.ChatHelper;

public class CommandChallengeDebug extends CommandBase {

    @Override
    public String getCommandName() {
        return "challenge_debug";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/challenge_debug [player]";
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayerMP player;
        if(args.length < 1)
            player = getCommandSenderAsPlayer(sender);
        else {
            player = getPlayer(sender, args[0]);
            if(player == null)
                throw new PlayerNotFoundException();
        }
        Challenge ch = ChallengeHandler.getChallenge(player);
        ChatHelper.toPlayerLines(sender, "Current challenge for " + player.getCommandSenderName() + ":\n" + String.valueOf(ch));
    }
    
    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length != 1 ? null : getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
    }
    
    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

}
