package org.bonilla.moneysteal;

import org.bonilla.moneysteal.events.PlayerDeath;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MoneySteal extends JavaPlugin {

    public void onEnable() {
        registerEvent();
        registerConfig();

        getLogger().info("[MoneySteal] Successfully enabled.");
    }

    public void onDisable() {
        getLogger().info("[MoneySteal] Successfully disabled.");
    }




    private void registerConfig() {
        getConfig().options().copyDefaults();
        saveConfig();
    }

    private void registerEvent() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerDeath(this), this);
        saveConfig();
    }
}
