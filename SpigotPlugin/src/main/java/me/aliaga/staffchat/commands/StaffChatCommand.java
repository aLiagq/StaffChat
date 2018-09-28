package me.aliaga.staffchat.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.aliaga.staffchat.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "The console cannot execute this command!");
        }

        if (commandSender.hasPermission("command.staffchat")) {
            if (strings.length > 0) {
                String text = "";
                for (int i = 0; i < strings.length; i++) {
                    text = text + strings[i];
                }
                sendToAnotherServers((Player) commandSender, Main.getInstance().getConfig().getString("servername") + ";" + commandSender.getName() + ";" + text);
            } else {
                commandSender.sendMessage(ChatColor.RED + "Usage: /staffchat <message>");
            }
        }
        return false;
    }

    private void sendToAnotherServers(Player player, String message){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("staffMessage");
        out.writeUTF(message);
        player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
    }
}
