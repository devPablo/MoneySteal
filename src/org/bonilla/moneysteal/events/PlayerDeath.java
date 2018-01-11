package org.bonilla.moneysteal.events;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.Economy;
import org.bonilla.moneysteal.MoneySteal;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.math.BigDecimal;

public class PlayerDeath implements Listener{
    private MoneySteal plugin;
    Economy eco;
    double moneyStolen;

    public PlayerDeath(MoneySteal plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws UserDoesNotExistException, NoLoanPermittedException {
        Player p = event.getEntity().getPlayer();
        FileConfiguration config = plugin.getConfig();
        if(p.getKiller().hasPermission("moneysteal.allow") || p.isOp()) {
            if(eco.getMoneyExact(p.getName()).doubleValue() >= config.getDouble("data.start-from")) {
                moneyStolen = (Double.valueOf(plugin.getConfig().getDouble("data.percentage")) * eco.getMoneyExact(p.getName()).doubleValue()) / 100;
                eco.substract(p.getName(), new BigDecimal(moneyStolen));
                eco.add(p.getKiller().getName(), new BigDecimal(moneyStolen));
                if (config.getBoolean("data.use-messages")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.substract-money").replace("{MONEY}", String.valueOf(moneyStolen))));
                    p.getKiller().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.add-money").replace("{MONEY}", String.valueOf(moneyStolen))));
                }
            }
        }
    }
}
