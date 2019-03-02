package me.zakpatat.lobby;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.zakpatat.Main;
import me.zakpatat.WaitLobby.WaitingBoard;
import me.zakpatat.api.ArenaManager;
import me.zakpatat.api.PlayerVisibilityHandler;
import me.zakpatat.api.StatusM;
import me.zakpatat.data.ArenaData;
import me.zakpatat.data.Config;



public class Signs implements Listener{
	
	ArenaData ad = ArenaData.getInstance();
	Config cf = Config.getInstance();
	

	private Main plugin = Main.getPlugin(Main.class);



	@EventHandler
    public void onSignChange(SignChangeEvent e) {
		String prefix = ChatColor.translateAlternateColorCodes('&', cf.getData().getString("Messages.Prefix"));

            if (e.getLine(0).equalsIgnoreCase("[OS]")) {
            if(ad.getData().get("Arenas." + e.getLine(1)) == null) {
            	e.getPlayer().sendMessage("§cDeze game bestaat niet!");
            	return;
            }
            
            	

            String game = e.getLine(1);
			if(ad.getData().get("Arenas." + game + ".MaxPlayers") == null) {
				e.getPlayer().sendMessage(prefix + " §cEr zijn geen Minimale en Maximale spelers voor de game ingesteld!.");
				return;
			}
			if(ad.getData().get("Arenas." + game + ".Spawn") == null) {
				e.getPlayer().sendMessage(prefix +" §cEr is geen spawn gezet voor deze game! Contacteer staff.");
				return;
			}
            
                    e.setLine(0, "§c§lOorlog simulatie");
                    e.setLine(1,  "§6Wachten...");
                    e.setLine(2, "§a" + game);
                    e.setLine(3, "§c" +  ArenaManager.getManager().playercount.get(game) + " §7/ §c" + ad.getData().getInt("Arenas." + game + ".MaxPlayers"));
                  
                    
               
            }
	}




	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
        Block b =  e.getClickedBlock();
	
    	String prefix = ChatColor.translateAlternateColorCodes('&', cf.getData().getString("Messages.Prefix"));
    
        
        
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
        if(b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST) {
            Sign s = (Sign) b.getState();
            String[] lines = s.getLines();
			String game = ChatColor.stripColor(lines[2]); 
			int ingame =  ArenaManager.getManager().playercount.size();
            
            
			if(lines[0].equalsIgnoreCase("§c§lOorlog simulatie") && lines[1].equalsIgnoreCase("§6Wachten...")) {

				if(ingame == ad.getData().getInt("Arenas." + game + ".MaxPlayers") ||ingame >= ad.getData().getInt("Arenas." + game + ".MaxPlayers") ) {
					p.sendMessage(prefix + " §cDeze game zit vol!");
					s.setLine(1, "§6Vol" );
					s.update();
					StatusM.getManager().setWaiting(game);
					return;
				}
				if(ArenaManager.getManager().players.containsKey(game) && ArenaManager.getManager().players.containsValue(p)) {
					p.sendMessage(prefix + " §cJe zit al in deze game!");
					return;
				}
				if(ArenaManager.getManager().playercount.get(game) == null) {
					ArenaManager.getManager().playercount.put(game, 0);
				}


			

				p.sendMessage(prefix + " §aJe bent de game succesvol gejoined.");
				double x = ad.getData().getInt("Arenas." + game + ".Spawn.X");
				double y = ad.getData().getInt("Arenas." + game + ".Spawn.Y");
				double z = ad.getData().getInt("Arenas." + game + ".Spawn.Z");

				Location loc = e.getPlayer().getLocation();
				loc.setX(x);
				loc.setY(y);
				loc.setZ(z);
				Vector dr = ad.getData().getVector("Arenas." + game + ".Spawn.Direction");
				loc.setDirection(dr);
				p.teleport(loc);
				ArenaManager.getManager().addPlayer(game, p, 1);

				
				double x1 = e.getClickedBlock().getLocation().getX();
				double y1 = e.getClickedBlock().getLocation().getY();
				double z1 = e.getClickedBlock().getLocation().getZ();

				ArenaManager.getManager().setSign(game, x1, y1, z1);
				s.setLine(3, "§c" +  ArenaManager.getManager().playercount.get(game) + " §7/ §c" + ad.getData().getInt("Arenas." + game + ".MaxPlayers"));
				p.getInventory().clear();
				
				
				ArenaManager.getManager().playerstatus.put(p, "waitinglobby");
				Board.removeScoreboard(p);
    			if(PlayerVisibilityHandler.pv.contains(p.getUniqueId().toString())){
    				PlayerVisibilityHandler.PLAYERVISIBILITY_ON(p);
    			}
			    new BukkitRunnable() {
			    	public void run(){
			  		  if(ArenaManager.getManager().playercount.get(game) == null || p == null) {
						  return;
					  }
			    		   if(ArenaManager.getManager().playerstatus.containsValue("waitinglobby") && ArenaManager.getManager().playerstatus.containsKey(p)) {
			    			   WaitingBoard.showScoreboard(p);
			    			   return;
			    		   }
			    		   
							

			    	    		
			    	   		}
			    		}.runTaskTimer(plugin, 0L, 10L);


				s.update();
				for(Player p1 : ArenaManager.getManager().players.values()) {
					
					p1.sendMessage(prefix + " §6" + p.getDisplayName() + " §ais de game gejoined! §c§l" + ArenaManager.getManager().playercount.get(game) + " §7§l/ §c§l" + ad.getData().getInt("Arenas." + game + ".MaxPlayers"));
				}
				return;
	}
			if(lines[0].equalsIgnoreCase("§c§lOorlog simulatie") && lines[1].equalsIgnoreCase("§aBegonnen")) {
				p.sendMessage(prefix + " §cDeze game is al begonnen.");
				return;
	}
			if(lines[0].equalsIgnoreCase("§c§lOorlog simulatie") && lines[1].equalsIgnoreCase("§4Uitgezet")) {
				p.sendMessage(prefix +" §cDeze game is uitgezet!");
				return;
	}
			if(lines[0].equalsIgnoreCase("§c§lOorlog simulatie") && lines[1].equalsIgnoreCase("§cGame Restart...")) {
				p.sendMessage(prefix + " §cDeze game is op dit moment niet berijkbaar.");
				return;
	}
        
        

        }
        }
	}


        
    
}
        
        
        
	

	
	

	


	




























