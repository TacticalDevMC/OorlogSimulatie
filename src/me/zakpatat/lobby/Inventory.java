package me.zakpatat.lobby;



import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;

import me.zakpatat.api.ArenaManager;
import me.zakpatat.api.Cooldown;
import me.zakpatat.api.ISGen;
import me.zakpatat.data.Config;
import me.zakpatat.data.PlayerData;

public class Inventory implements Listener{
	
	public PlayerData pd = PlayerData.getInstance();
	public Config cf = Config.getInstance();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
	
		   if(!(ArenaManager.getManager().playerstatus.containsValue("waitinglobby") 
				   && ArenaManager.getManager().playerstatus.containsKey(p))) {
			   if(p.getGameMode() == GameMode.CREATIVE) {
				   return;
			   }
	    event.setCancelled(true);
	}
	}
	public void PlayerDrop(PlayerDropItemEvent event) {
		Player p = (Player) event.getPlayer();
		
		   if(!(ArenaManager.getManager().playerstatus.containsValue("waitinglobby") 
				   && ArenaManager.getManager().playerstatus.containsKey(p))) {
			   if(p.getGameMode() == GameMode.CREATIVE) {
				   return;
			   }
		
	    event.setCancelled(true);
	}
	}

    @EventHandler
    public void onClickItem(PlayerInteractEvent e){       
        Player p = e.getPlayer();     
		String prefix = ChatColor.translateAlternateColorCodes('&', cf.getData().getString("Messages.Prefix"));
        if(e.getAction() == Action.RIGHT_CLICK_AIR){
        	if(e.getItem().getType() == Material.SKULL_ITEM && e.getItem().getItemMeta().getDisplayName().equals("§cStats")){
        		
        		if(Cooldown.isInCooldown(p.getUniqueId(), "stats")) {
        			p.sendMessage(prefix + " §cJe moet nog §f" + Cooldown.getTimeLeft(p.getUniqueId(), "stats")+ "§c seconden wachten!");
        			return;
        		}else {
    				p.sendMessage("§7§m---§c§l OorlogSimulatie §7§m-§c§l Stats §7§m---");
    				p.sendMessage("§f● §cNaam§f: " + p.getDisplayName());
    				p.sendMessage("§f● §cKills§f: " + pd.getData().getInt(p.getUniqueId() + ".Kills"));
    				p.sendMessage("§f● §cDeaths§f: " + pd.getData().getInt(p.getUniqueId() + ".Deaths"));
    				p.sendMessage("§f● §cWins§f: " + pd.getData().getInt(p.getUniqueId() + ".Wins"));
    				p.sendMessage("§f● §cLoses§f: " + pd.getData().getInt(p.getUniqueId() + ".Loses"));
    				
    				Cooldown c = new Cooldown(p.getUniqueId(), "stats", 3);
    				c.start();

        			
        		}
        	}
        	}
    }
        		

	@EventHandler
	public void onJoin(PlayerJoinEvent e){		

		Player p = (Player) e.getPlayer();

		String n = p.getDisplayName();
		PlayerInventory inv = p.getInventory();		
		 inv.setItem(4, new ISGen(Material.SKULL_ITEM, 1, (short)3).setOwner(n).setDisplayName("§cStats").getItem());		
		
				}
			
			
		}

