package me.zakpatat.listeners;


import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.zakpatat.data.PlayerData;

public class KD implements Listener{
	
	PlayerData pd = PlayerData.getInstance(); 
	
	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();
		Player p = e.getEntity().getPlayer();
		

		p.sendMessage(killer.getDisplayName());
			addKill(killer.getUniqueId(), 1);
			addDeath(p.getUniqueId(), 1);
		}
		
	
    public void addKill(UUID p, int count) {
   	 
    	int i = pd.getData().getInt(p + ".Kills");
    	int a = count;
    	pd.getData().set(p + ".Kills", a+i);
    	pd.saveData();
    }
    public void addDeath(UUID p, int count) {
      	 
    	int i = pd.getData().getInt(p + ".Deaths");
    	int a = count;
    	pd.getData().set(p + ".Deaths", a+i);
    	pd.saveData();
    }
}