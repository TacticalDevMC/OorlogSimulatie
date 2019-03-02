package me.zakpatat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zakpatat.api.sendMsg;
import me.zakpatat.data.ArenaData;
import me.zakpatat.data.Config;
import me.zakpatat.data.KitData;
import me.zakpatat.data.PlayerData;
import net.md_5.bungee.api.ChatColor;

public class MainCMD implements CommandExecutor{
	
    public Config cf = Config.getInstance();
    public ArenaData ad = ArenaData.getInstance();
    public KitData kd = KitData.getInstance();
    public PlayerData pd = PlayerData.getInstance();
	
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("oorlogsimulatie") && args.length == 0) {
			if(p.hasPermission("os.admin")) {
				sendMsg((Player)p, "&7&m--- &c&lOorlogSimulatie - Admin &7&m---");
				sendMsg((Player)p, "&8/Arena &c- Alle commando's van de arena (Create, remove, etc)");
				return true;
			}
				if(!(p.hasPermission("os.admin"))) {
					sendMsg.noPerm((Player)p);
					return true;
				}
		}
				if(args[0].equals("reload") && args.length == 1) {
					sendMsg((Player)p, "&aDe data is succesvol herladen !");
					cf.reloadData();
					ad.reloadData();
					kd.reloadData();
					pd.reloadData();
					return true;
				}

			
			
		
		return false;
	}
	
	public void sendMsg(Player p, String m) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', m));

	}
}
