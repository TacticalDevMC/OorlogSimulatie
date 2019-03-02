package me.zakpatat.api;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.zakpatat.data.ArenaData;
import me.zakpatat.data.PlayerData;

public class ArenaManager {
    private static ArenaData ad = ArenaData.getInstance();
    private static PlayerData pd = PlayerData.getInstance();

    public HashMap<String, Player> players = new HashMap<String, Player>();
    public HashMap<Player, String> playersarena = new HashMap<Player, String>();
    public HashMap<Player, String> playerstatus = new HashMap<Player, String>();
    public Map<String, Integer> playercount = new HashMap<String, Integer>();


    private ArenaManager() {

    }


    public static ArenaManager getManager() {
        if (arenaManager == null)
            arenaManager = new ArenaManager();

        return arenaManager;
    }

    public void createArena(String id) {
        ad.getData().set("Arenas." + id, "");
        ad.getData().set("Arenas." + id + ".Name", id);
        ad.saveData();

    }

    public void removeArena(String id) {
        ad.getData().set("Arenas." + id, null);
    }

    public void setSpawnTeam1(String id, Location t1) {
        ad.getData().set("Arenas." + id + ".SpawnTeam1", t1);
        ad.saveData();
    }

    public void setSpawnTeam2(String id, Location t2) {
        ad.getData().set("Arenas." + id + ".SpawnTeam2", t2);
        ad.saveData();
    }

    public void setGameSpawn(String id, double x, double y, double z, Vector direction) {
        ad.getData().set("Arenas." + id + ".Spawn.X", x);
        ad.getData().set("Arenas." + id + ".Spawn.Y", y);
        ad.getData().set("Arenas." + id + ".Spawn.Z", z);
        ad.getData().set("Arenas." + id + ".Spawn.Direction", direction);
        ad.saveData();
    }

    public void setSign(String id, double x, double y, double z) {
        ad.getData().set("Arenas." + id + ".Sign.X", x);
        ad.getData().set("Arenas." + id + ".Sign.Y", y);
        ad.getData().set("Arenas." + id + ".Sign.Z", z);
        ad.saveData();
    }

    public void setMinPlayers(String id, int players) {
        ad.getData().set("Arenas." + id + ".MinPlayers", players);
        ad.saveData();
    }

    public void setMaxPlayers(String id, int players) {
        ad.getData().set("Arenas." + id + ".MaxPlayers", players);
        ad.saveData();
    }

    public void setStatus(String id, String status) {
        ad.getData().set("Arenas." + id + ".Status", status);
        ad.saveData();

    }

    public String getStatus(String id) {
        ad.getData().get("Arenas." + id + ".Status");
        return instance;

    }

    public String getMaxPlayer(String id) {
        ad.getData().getString("Arenas." + id + ".MaxPlayers");
        return instance;
    }


    public Integer getPlayers(String id) {
        this.playercount.get(id);
        return null;
    }


    public void addPlayer(String id, Player p, int count) {
        playersarena.put(p, id);
        players.put(id, p);

        int i = playercount.get(id);
        int a = count;
        this.playercount.put(id, i + a);
        ad.saveData();
        pd.saveData();
    }


    public void removePlayer(String id, Player p, int count) {
        playersarena.remove(p);
        players.remove(id, p);

        int i = playercount.get(id);
        int a = count;
        this.playercount.put(id, i - a);
        ad.saveData();
    }
}


