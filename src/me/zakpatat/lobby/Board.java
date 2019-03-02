package me.zakpatat.lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import me.zakpatat.Main;
import me.zakpatat.data.Config;
import me.zakpatat.data.PlayerData;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Board implements Listener{ 

	public static Config cf = Config.getInstance();
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
	      if (p.getScoreboard().equals(p.getServer().getScoreboardManager().getMainScoreboard())) {
	        p.setScoreboard(p.getServer().getScoreboardManager().getNewScoreboard());
	      }
	      Scoreboard score = p.getScoreboard();
	      
	      Objective objective = score.getObjective(p.getName()) == null ? score.registerNewObjective(p.getName(), "dummy") : 
	        score.getObjective(p.getName());
	      objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&l  OorlogSimulatie  "));

 
	      int ping = getPing(p);

		  replaceScore(objective, 13, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 13)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		   		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		   		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		   		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		   		  .replaceAll("%ping","" + ping));
		  replaceScore(objective, 12, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 12)
		   		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
		     	  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
		    	  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
		    	  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
		    	  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
		    	  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
		    	  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 11, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 11)
	    		  .replaceAll("%name", pd.getData().getString(p.getUniqueId() + ".Naam")))
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses"))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 10, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 10)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 9, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 9)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 8, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 8)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 7, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 7)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 6, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 6)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 5, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 5)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 4, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 4)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 3, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 3)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 2, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 2)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      replaceScore(objective, 1, ChatColor.translateAlternateColorCodes('&', cf.getData().getString("ScoreBoard.Lobby." + 1)
	    		  .replaceAll("%name", p.getDisplayName())
	    		  .replaceAll("%rank", PermissionsEx.getUser(p).getPrefix())
	    		  .replaceAll("%kills","" + pd.getData().getInt(p.getUniqueId() + ".Kills"))
	    		  .replaceAll("%deaths", "" + pd.getData().getInt(p.getUniqueId() + ".Deaths"))
	    		  .replaceAll("%wins", "" + pd.getData().getInt(p.getUniqueId() + ".Wins"))
	    		  .replaceAll("%loses", "" + pd.getData().getInt(p.getUniqueId() + ".Loses")))
	    		  .replaceAll("%ping", "" + ping));
	      if (objective.getDisplaySlot() != DisplaySlot.SIDEBAR) {
	        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	      }
	      p.setScoreboard(score);
	    }
	      public static int getPing(Player player) {
	    	    return ((CraftPlayer) player).getHandle().ping;
	    	}

			
		}
	  

