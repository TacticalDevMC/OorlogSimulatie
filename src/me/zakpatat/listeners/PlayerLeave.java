package me.zakpatat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.zakpatat.WaitLobby.WaitingBoard;
import me.zakpatat.api.ArenaManager;
import me.zakpatat.data.Config;
import net.md_5.bungee.api.ChatColor;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerLeave implements Listener{
	
	Config cf = Config.getInstance();
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', cf.getData().getString("Messages.Leave").replaceAll("%name", e.getPlayer().getDisplayName()).replaceAll("%prefix", PermissionsEx.getUser(e.getPlayer()).getPrefix())));
		
		if(ArenaManager.getManager().playersarena.get(e.getPlayer()) == null) {
			return;
		}
		ArenaManager.getManager().removePlayer(ArenaManager.getManager().playersarena.get(e.getPlayer()), e.getPlayer(), 1);
		WaitingBoard.removeScoreboard(e.getPlayer());
	
	}

}
