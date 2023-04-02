package dev.omochi.javaprefixblock;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.geysermc.floodgate.api.FloodgateApi;

import javax.security.auth.login.Configuration;


public final class JavaPrefixBlock extends JavaPlugin implements Listener{
    public static FloodgateApi floodgateApi;
    public static FileConfiguration config;
    public static String BlockPrefix;
    @Override
    public void onEnable() {
        // Plugin startup logic
        config = getConfig();
        //
        this.getConfig().options().copyDefaults(true);
        saveConfig();

        BlockPrefix=config.getString("BlockPrefix");

        floodgateApi=FloodgateApi.getInstance();
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        if(e.getPlayer().getName().startsWith(BlockPrefix)){
            if(!floodgateApi.isFloodgatePlayer(e.getPlayer().getUniqueId())){
                e.getPlayer().kickPlayer("Java版で"+BlockPrefix+"から始まるプレイヤーは重複防止のためこのサーバーに参加できません");
            }
        }

    }
}
