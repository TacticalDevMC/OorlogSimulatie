package me.zakpatat.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class PlayerData {

    static PlayerData instance = new PlayerData();
    Plugin plugin;
    FileConfiguration config;
    File file;

    public static PlayerData getInstance() {
        return instance;
    }

    public void setup(Plugin p) {
        this.file = new File(p.getDataFolder(), "Data/PlayerData.yml");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "There went something wrong!");
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getData() {
        return this.config;
    }

    public void saveData() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "There went something wrong!");
        }
    }

    public void reloadData() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }
}
