package me.aliaga.staffchat.listeners;

import me.aliaga.staffchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class BungeeListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] message) {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
        try {
            String sub = in.readUTF();
            if (sub.equals("staffMessage")) {
                String tosend = in.readUTF();
                for(Player target : Bukkit.getServer().getOnlinePlayers()){
                    if(target.hasPermission("command.staffchat")){
                        String[] strings = tosend.split(";");
                        String format = Main.getInstance().getConfig().getString("format");
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&',format.replace("%player%", strings[1])).replace("%message%", strings[2]).replace("%server%", strings[0]));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
