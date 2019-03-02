package me.zakpatat.api;

import java.util.ArrayList;
import java.util.UUID;


public class LobbyManager {
	
    private String name;
    private ArrayList<UUID> playerInGame;

    public LobbyManager(String name, ArrayList<UUID> inLobby) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<UUID> getPlayerInGame() {
        return playerInGame;
    }

    public void setPlayerInGame(ArrayList<UUID> playerInGame) {
        this.playerInGame = playerInGame;
    }

}
