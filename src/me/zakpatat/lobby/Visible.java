package me.zakpatat.lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.zakpatat.api.Cooldown;
import me.zakpatat.api.ISGen;
import me.zakpatat.api.PlayerVisibilityHandler;
import me.zakpatat.data.Config;

public class Visible implements Listener {
	public Config cf = Config.getInstance();
	
	    public ItemStack on() {
	 	    return new ISGen(Material.INK_SACK, 1, (short)10).setDisplayName("&7Spelerzichtbaarheid: &aaan").getItem();
	    }
	    public ItemStack off() {
	 	    return new ISGen(Material.INK_SACK, 1, (short)1).setDisplayName("&7Spelerzichtbaarheid: &cuit").getItem();
	    }
	
    @EventHandler
    public void onClickItem(PlayerInteractEvent e){       
        Player p = e.getPlayer();     
		String prefix = ChatColor.translateAlternateColorCodes('&', cf.getData().getString("Messages.Prefix"));
        if(e.getAction() == Action.RIGHT_CLICK_AIR){
        	if(e.getItem().getType() == Material.INK_SACK ){
        		if(Cooldown.isInCooldown(p.getUniqueId(), "visible")) {
        			p.sendMessage(prefix + " §cJe moet nog §f" + Cooldown.getTimeLeft(p.getUniqueId(), "visible")+ "§c seconden wachten!");
        			return;
        		}else {
    				Cooldown c = new Cooldown(p.getUniqueId(), "visible", 3);
    				c.start();
        		
        			if(!PlayerVisibilityHandler.pv.contains(p.getUniqueId().toString())){
        				PlayerVisibilityHandler.PLAYERVISIBILITY_OFF(p);
        				p.setItemInHand(off());
        				return;       	       			
        		}
        		if(e.getItem().getType() == Material.INK_SACK){
        			if(PlayerVisibilityHandler.pv.contains(p.getUniqueId().toString())){
        				PlayerVisibilityHandler.PLAYERVISIBILITY_ON(p);
        				p.setItemInHand(on());
        				return;
        			}
        			
        		}
        	}

        }
        }

    }

            
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){		
        
		Player p = (Player) e.getPlayer();
		int slot = 8;
		Inventory inv = p.getInventory();		
		inv.setItem(slot, on());		
		
			for(Player pl : Bukkit.getOnlinePlayers()){
				if(PlayerVisibilityHandler.pv.contains(pl.getUniqueId().toString())){
					pl.hidePlayer(p);
				}
			}
			
		}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(PlayerVisibilityHandler.pv.contains(p.getUniqueId().toString())){
			PlayerVisibilityHandler.pv.remove(p.getUniqueId().toString());
		}
		for(Player pl : Bukkit.getOnlinePlayers()){
			pl.showPlayer(p);
			p.showPlayer(pl);
		}
	}


	}

