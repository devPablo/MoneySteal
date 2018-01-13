package org.bonilla.moneysteal;

import org.bonilla.moneysteal.commands.Commandmoneysteal;
import org.bonilla.moneysteal.events.PlayerDeath;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MoneySteal extends JavaPlugin {

    public void onEnable() {
        registerEvent();
        registerConfig();
        registerCommand();

        getLogger().info("Successfully enabled. (v0.2)");
    }

    public void onDisable() {
        getLogger().info("Successfully disabled. (v0.2)");
    }




    private void registerConfig() {
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
        reloadConfig();
    }

    private void registerEvent() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerDeath(this), this);
    }

    private void registerCommand() {
        getCommand("moneysteal").setExecutor(new Commandmoneysteal(this));
    }
}
