package de.davboecki.multimodworld.craftbukkit;

import org.bukkit.Material;

public class ItemInformation {
	
	private int id;
	public ItemInformation(int id) {
		this.id = id;
	}
	
	public String getName() {
		try {
			return net.minecraft.server.Item.byId[id].l();
		} catch(Exception e) {
			return Material.getMaterial(id).name();
		}
	}
}
