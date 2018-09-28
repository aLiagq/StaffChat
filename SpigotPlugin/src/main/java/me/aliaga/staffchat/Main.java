package me.aliaga.staffchat;

import me.aliaga.staffchat.commands.StaffChatCommand;
import me.aliaga.staffchat.listeners.BungeeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable(){
        instance = this;
        saveDefaultConfig();
        register();
    }

    private void register(){
        Bukkit.getPluginCommand("staffchat").setExecutor(new StaffChatCommand());
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "Return", new BungeeListener());
    }
}
