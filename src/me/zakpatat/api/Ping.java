package me.zakpatat.api;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Ping {

	  public static int pingPlayer(Player p)
	  {
	    try
	    {
	      String bukkitversion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
	      Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + bukkitversion + ".entity.CraftPlayer");
	      Object handle = craftPlayer.getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
	      Integer ping = (Integer)handle.getClass().getDeclaredField("ping").get(handle);
	      return ping.intValue();
	    }
	    catch (ClassNotFoundException|IllegalAccessException|IllegalArgumentException|InvocationTargetException|NoSuchMethodException|SecurityException|NoSuchFieldException e) {}
	    return -1;
	  }
	}

