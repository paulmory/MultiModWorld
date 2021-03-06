package de.davboecki.multimodworld.exchangeworld;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;

import de.davboecki.multimodworld.MultiModWorld;
import de.davboecki.multimodworld.exchangeworld.listener.PlayerAccessListener;
import de.davboecki.multimodworld.exchangeworld.listener.PlayerMoveListener;
import de.davboecki.multimodworld.exchangeworld.listener.WeatherListener;
import de.davboecki.multimodworld.exchangeworld.rooms.RoomManager;

public class ExchnageWorldController {
	
	private MultiModWorld plugin;
	public static final ExchangeWorldGenerator generator = new ExchangeWorldGenerator();
	public final RoomManager manager = new RoomManager();

	public ExchnageWorldController() {
		plugin = MultiModWorld.getInstance();
	}

	public void onLoad() {
	}

	public void onDisable() {
	}

	public void onEnable() {
		final PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new ExchangeWorldSpawnListener(), plugin);
		pm.registerEvents(new PlayerMoveListener(), plugin);
		pm.registerEvents(new PlayerAccessListener(), plugin);
		pm.registerEvents(new WeatherListener(), plugin);
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new TimeControler(), 1, 120);
		manager.LoadRooms();
	}
	
	public boolean isChestWorld(World world) {
		if (world.getGenerator() == null) {
			return false;
		}
		return world.getGenerator().equals(generator);
	}
}
