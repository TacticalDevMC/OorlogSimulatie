package me.zakpatat.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.zakpatat.data.Config;
import net.md_5.bungee.api.ChatColor;

public class sendMsg implements Listener{
	
	private static Config cf = Config.getInstance();
	
	public static void noPerm(Player p) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', cf.getData().getString("Messages.noPerm")));
		
		
		
	}

}
