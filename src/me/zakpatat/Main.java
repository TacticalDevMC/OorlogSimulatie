package me.zakpatat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.zakpatat.api.ArenaManager;
import me.zakpatat.commands.ArenaCMD;
import me.zakpatat.commands.MainCMD;
import me.zakpatat.data.ArenaData;
import me.zakpatat.data.Config;
import me.zakpatat.data.KitData;
import me.zakpatat.data.PlayerData;
import me.zakpatat.listeners.KD;
import me.zakpatat.listeners.PlayerJoin;
import me.zakpatat.listeners.PlayerLeave;
import me.zakpatat.lobby.Board;
import me.zakpatat.lobby.Inventory;
import me.zakpatat.lobby.Signs;
import me.zakpatat.lobby.Visible;

public class Main extends JavaPlugin{
    public Config cf = Config.getInstance();
    public ArenaData ad = ArenaData.getInstance();
    public KitData kd = KitData.getInstance();
    public PlayerData pd = PlayerData.getInstance();
    private static Main instance;
	
	
	public void onEnable() {
		loadConfigManager();
		regListener();
		startTitle();
		creatConf();
		regCommand();
		SignUpdater();
	    for (Player all : Bukkit.getServer().getOnlinePlayers() ) {
			ArenaManager.getManager().playerstatus.put(all, "lobby");
	    }
	}
	
	public void regListener() {
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
		Bukkit.getPluginManager().registerEvents(new KD(), this);
		Bukkit.getPluginManager().registerEvents(new Signs(), this);
		Bukkit.getPluginManager().registerEvents(new Visible(), this);
		Bukkit.getPluginManager().registerEvents(new Inventory(), this);

	}
	public void regCommand() {
		getCommand("arena").setExecutor(new ArenaCMD());
		getCommand("oorlogsimulatie").setExecutor(new MainCMD());
	}

	public void loadConfigManager(){
	      pd.setup(this);
	      pd.getData().options().copyDefaults(true);
	      pd.saveData();
	      
	      cf.setup(this);
	      cf.getData().options().copyDefaults(true);
	      cf.saveData();
	      
	      kd.setup(this);
	      kd.getData().options().copyDefaults(true);
	      kd.saveData();
	      
	      ad.setup(this);
	      ad.getData().options().copyDefaults(true);
	      ad.saveData();
	      
	      
	      

	}
	public void creatConf() {
		
		cf.getData().addDefault("Messages.Join", "&a&l+ %rank &7%name ");
		cf.getData().addDefault("Messages.Leave", "&c&l- %rank &7%name ");
		cf.getData().addDefault("Messages.noPerm", "&cJe hebt geen toegang tot dit commando !");
		cf.getData().addDefault("Messages.Prefix", "&c&lOorlogSimulatie &8&l>");
		
		cf.getData().addDefault("ScoreBoard.Lobby." + 13, "&7&m-----------------");
		cf.getData().addDefault("ScoreBoard.Lobby." + 12, "&c&lSPELER");
		cf.getData().addDefault("ScoreBoard.Lobby." + 11, "&cNaam: &f%name");
		cf.getData().addDefault("ScoreBoard.Lobby." + 10, "&cRank: &f%rank");
		cf.getData().addDefault("ScoreBoard.Lobby." + 9, "&cPing: &f%ping");
		cf.getData().addDefault("ScoreBoard.Lobby." + 8, "&f");
		cf.getData().addDefault("ScoreBoard.Lobby." + 7, "&c&lSTATS");
		cf.getData().addDefault("ScoreBoard.Lobby." + 6, "&cKills: &f%kills");
		cf.getData().addDefault("ScoreBoard.Lobby." + 5, "&cDeaths: &f%deaths");
		cf.getData().addDefault("ScoreBoard.Lobby." + 4, "&cWins: &f%wins");
		cf.getData().addDefault("ScoreBoard.Lobby." + 3, "&cLoses: &f%loses");
		cf.getData().addDefault("ScoreBoard.Lobby." + 2, "&7&m -----------------");
		cf.getData().addDefault("ScoreBoard.Lobby." + 1, "&cPlay.YounixMC.com");
		
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 12, "&7&m-----------------");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 11, "&c&lSPELER");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 10, "&cNaam: &f%name");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 9, "&cRank: %rank");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 8, "&cPing: &f%ping");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 7, "&d");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 6, "&c&lINFO");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 5, "&cGame: &f%game");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 4, "&cSpelers: &f%playersingame &7/ &f%maxplayers");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 3, "&cTijd: %timeforbegin");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 2, "&7&m -----------------");
		cf.getData().addDefault("ScoreBoard.WaitingLobby." + 1, "&cPlay.YounixMC.com");
		
		cf.saveData();

		
	}
	public void startTitle(){

	    new BukkitRunnable() {
public void run(){
	    for (Player all : Bukkit.getServer().getOnlinePlayers() ) {
	 	   if(!ArenaManager.getManager().playerstatus.get(all).contains("lobby") && ArenaManager.getManager().playerstatus.containsKey(all)) {
			   return;
		   }
	   if(ArenaManager.getManager().playerstatus.get(all).contains("lobby") && ArenaManager.getManager().playerstatus.containsKey(all)) {
		   Board.showScoreboard(all);
		   return;
	   }


    		}
   		}
	}.runTaskTimer(this, 0L, 10L);

} 
	public void SignUpdater(){

	    new BukkitRunnable() {
public void run(){

    for (Player all : Bukkit.getServer().getOnlinePlayers() ) {
    String Arena =  ArenaManager.getManager().playersarena.get(all);
    if(Arena == null) {
  	  return;
    
    }
    
    Location loc = all.getLocation();
	double x = (double) ad.getData().get("Arenas." + Arena + ".Sign.X");
	double y = (double) ad.getData().get("Arenas." + Arena + ".Sign.Y");
	double z = (double) ad.getData().get("Arenas." + Arena + ".Sign.Z");

	loc.setX(x);
	loc.setY(y);
	loc.setZ(z);
	
	loc.getBlock().getState().update();
	
    }

	
}
   		
	}.runTaskTimer(this, 0L, 10L);
	    
} 
	
    public static Main getInstance() {
        return instance;
    }
    
}
