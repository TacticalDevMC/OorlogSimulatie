package me.zakpatat.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.zakpatat.api.ArenaManager;
import me.zakpatat.data.Config;
import me.zakpatat.data.PlayerData;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerJoin implements Listener{
	 PlayerData pd = PlayerData.getInstance();
	 Config cf = Config.getInstance();
	 @EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		p.getInventory().clear();
		e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',cf.getData().getString("Messages.Join").replace("%name",  p.getDisplayName()).replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())));

		ArenaManager.getManager().playerstatus.put(p, "lobby");

		pd.getData().addDefault(p.getUniqueId() + ".Naam", p.getDisplayName());
		pd.getData().addDefault(p.getUniqueId() + ".Kills", 0);
		pd.getData().addDefault(p.getUniqueId() + ".Deaths", 0);
		pd.getData().addDefault(p.getUniqueId() + ".Wins", 0);
		pd.getData().addDefault(p.getUniqueId() + ".Loses", 0);
		pd.getData().addDefault(p.getUniqueId() + ".Arena", "");
		pd.getData().addDefault(p.getUniqueId() + ".Status", "");
		pd.saveData();
		

	    

		

	
	}

}
