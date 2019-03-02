package me.zakpatat.WaitLobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import me.zakpatat.Main;
import me.zakpatat.api.ArenaManager;
import me.zakpatat.data.ArenaData;
import me.zakpatat.data.Config;
import me.zakpatat.data.PlayerData;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class WaitingBoard implements Listener{ 

	public static Config cf = Config.getInstance();
	public static ArenaData ad = ArenaData.getInstance();
	    Main plugin;
	    private static PlayerData pd = PlayerData.getInstance();
	    public static String getEntryFromScore(Objective o, int score) {
	      if (o == null) {
	        return null;
	      }
	      if (!hasScoreTaken(o, score)) {
	        return null;
	      }
	      for (String s : o.getScoreboard().getEntries()) {
	        if (o.getScore(s).getScore() == score) {
	          return o.getScore(s).getEntry();
	        }
	      }
	      return null;
	    }
	    
	    public static boolean hasScoreTaken(Objective o, int score)
	    {
	      for (String s : o.getScoreboard().getEntries()) {
	        if (o.getScore(s).getScore() == score) {
	          return true;
	        }
	      }
	      return false;
	    }
	    
	    public static void replaceScore(Objective o, int score, String name) {
	      if (hasScoreTaken(o, score)) {
	        if (getEntryFromScore(o, score).equalsIgnoreCase(name)) {
	          return;
	        }
	        if (!getEntryFromScore(o, score).equalsIgnoreCase(name)) {
	          o.getScoreboard().resetScores(getEntryFromScore(o, score));
	        }
	      }
	      o.getScore(name).setScore(score);
	    }
	    public static void removeScoreboard(Player p) {
	    	p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	    }
	    

		public static void showScoreboard(Player p) {
			if(p == null) {
				return;
			}
	      if (p.getScoreboard().equals(p.getServer().getScoreboardManager().getMainScoreboard())) {
	        p.setScoreboard(p.getServer().getScoreboardManager().getNewScoreboard());
	      }
	      Scoreboard score = p.getScoreboard();
	      
	      Objective objective = score.getObjective(p.getName()) == null ? score.registerNewObjective(p.getName(), "dummy") : 
	        score.getObjective(p.getName());
	      objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&l  OorlogSimulatie  "));

 
	      int ping = getPing(p);

	      String Arena =  ChatColor.stripColor(ArenaManager.getManager().playersarena.get(p));
	      if(Arena == null) {
	    	  return;
	      }

	      Integer ingame = ArenaManager.getManager().playercount.get(ArenaManager.getManager().playersarena.get(p));

	      String maxplayers =  ad.getData().getString("Arenas." + Arena + ".MaxPlayers");


		  replaceScore(objective, 12, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 12)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame", "" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 11, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 11)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 10, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 10)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 9, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 9)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 8, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 8)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 7, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 7)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 6, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 6)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 5, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 5)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", "" + Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 4, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 4)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 3, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 3)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 2, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 2)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      replaceScore(objective, 1, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.WaitingLobby." + 1)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix()))
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping)
		    	  .replaceAll("%game", Arena)
		    	  .replaceAll("%playersingame","" + ingame)
		    	  .replaceAll("%maxplayers", maxplayers)
		    	  .replaceAll("%timeforbegin", ""));
	      if (objective.getDisplaySlot() != DisplaySlot.SIDEBAR) {
	        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	      }
	      p.setScoreboard(score);
	    }
	      public static int getPing(Player player) {
	    	    return ((CraftPlayer) player).getHandle().ping;
	    	}

			
		}
	  

