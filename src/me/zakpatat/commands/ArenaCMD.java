package me.zakpatat.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zakpatat.api.ArenaManager;
import me.zakpatat.api.sendMsg;
import me.zakpatat.data.ArenaData;

public class ArenaCMD implements CommandExecutor {

    ArenaData ad = ArenaData.getInstance();

    public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {

        Player p1 = (Player) p;
        if (cmd.getName().equalsIgnoreCase("arena") && args.length == 0) {
            if (!(p.hasPermission("ol.admin"))) {
                sendMsg.noPerm((Player) p);
                return true;
            }
            p.sendMessage("§c§l  OorlogSimulatie");
            p.sendMessage("§8● §c/arena create §7| Maak een arena aan.");
            p.sendMessage("§8● §c/arena remove §7| Verwijder een arena.");
            p.sendMessage("§8● §c/arena setspawn §7| Zet de lobby spawn van de arena.");
            p.sendMessage("§8● §c/arena setmaxplayers §7| Stel in hoeveel spelers maximaal mogen spelen.");
            p.sendMessage("§8● §c/arena setminplayers §7| Stel in hoeveel spelers minimaal moeten spelen.");
            p.sendMessage("§8● §c/arena forcestart §7| Start de game meteen.");
            p.sendMessage("§8● §c/arena info §7| Krijg info over de arena.");
            return true;
        }
        if (args[0].contains("create")) {
            if (args.length == 1) {

                p.sendMessage("§cGebruik: §7/arena create <Arena> <MinPlayers> <MaxPlayers>");
                return true;
            }

            if (!(ad.getData().get("Arenas." + args[1]) == null)) {
                p.sendMessage("§c§lLetop! §7Deze arena bestaat al!");
                return true;
            }
            if (args.length == 2) {
                p.sendMessage("§aJe hebt arena §8" + args[1] + " §asuccesvol gemaakt");
                p.sendMessage("§c§lLetop! §7Je hebt nog geen spelers ingesteld!");
                ArenaManager.getManager().createArena(args[1]);
                return true;
            }
            if (args.length == 3) {
                p.sendMessage("§cGebruik: §7/arena create <Arena> <MinPlayers> <MaxPlayers>");
                return true;
            }
            if (args.length == 4) {
                p.sendMessage("§aJe hebt arena §7" + args[1] + " §asuccesvol gemaakt");
                p.sendMessage("§aJe de minimale spelers op §7" + args[2] + " §agezet.");
                p.sendMessage("§aJe de maximale spelers op §7" + args[3] + " §agezet.");

                ArenaManager.getManager().createArena(args[1]);
                ArenaManager.getManager().setMinPlayers(args[1], Integer.parseInt(args[2]));
                ArenaManager.getManager().setMaxPlayers(args[1], Integer.parseInt(args[3]));

                return true;
            }
        }
        if (args[0].contains("remove")) {
            if (args.length == 1) {

                p.sendMessage("§cGebruik: §7/arena remove <Arena>");
                return true;
            }
            if (ad.getData().get("Arenas." + args[1]) == null) {
                p.sendMessage("§c§lLetop! §7Deze arena bestaat niet!");
                return true;
            }
            if (args.length == 2) {
                p.sendMessage("§aJe hebt arena §8" + args[1] + " §csuccesvol verwijderd.");
                ArenaManager.getManager().removeArena(args[1]);
                return true;
            }
        }
        if (args[0].contains("setspawn")) {
            if (args.length == 1) {

                p.sendMessage("§cGebruik: §7/arena setspawn <Arena>");
                return true;
            }

            if (args.length == 2) {
                p.sendMessage("§aDe gamespawn succesvol gezet!");
                Location l = p1.getLocation();
                ArenaManager.getManager().setGameSpawn(args[1], l.getX(), l.getY(), l.getZ(), l.getDirection());
                return true;
            }
        }
        if (args[0].contains("setmaxplayers")) {
            if (args.length == 1 || args.length == 2) {
                p.sendMessage("§cGebruik: §7/arena setmaxplayers <Arena> <MaxPlayers>");
                return true;
            }
            if (args.length == 3) {
                p.sendMessage("§aDe maximale spelers voor game §7" + args[1] + " §is §7" + args[2]);
                ArenaManager.getManager().setMaxPlayers(args[1], Integer.parseInt(args[2]));
                return true;
            }
        }
        if (args[0].contains("setminplayers")) {
            if (args.length == 1 || args.length == 2) {
                p.sendMessage("§cGebruik: §7/arena setminplayers <Arena> <MaxPlayers>");
                return true;
            }
            if (args.length == 3) {
                p.sendMessage("§aDe minimale spelers voor game §7" + args[1] + " §ais §7" + args[2]);
                ArenaManager.getManager().setMaxPlayers(args[1], Integer.parseInt(args[2]));
                return true;
            }
        }
        return true;

    }

}


















