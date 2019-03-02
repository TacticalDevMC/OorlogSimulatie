package me.zakpatat.api;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.zakpatat.Main;
import me.zakpatat.data.Config;

public class PlayerVisibilityHandler {
	public static ArrayList<String> pv = new ArrayList<String>();
	public static Main plugin;
	public static Config cf = Config.getInstance();
	public static String prefix = ChatColor.translateAlternateColorCodes('&', cf.getData().getString("Messages.Prefix"));
	
	public static void PLAYERVISIBILITY_OFF(Player p){   		
    	p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " &7De spelers zijn &cuitgezet"));
		pv.add(p.getUniqueId().toString());
		for(Player pl : Bukkit.getOnlinePlayers()){
			p.hidePlayer(pl);
		}
		
	}
	
	public static void PLAYERVISIBILITY_ON(Player p){      

    	p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " &7De spelers zijn &aaangezet"));
		pv.remove(p.getUniqueId().toString());
		for(Player pl : Bukkit.getOnlinePlayers()){
			p.showPlayer(pl);
		}
		
	}
}
