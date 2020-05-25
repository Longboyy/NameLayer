package vg.civcraft.mc.namelayer.group.log.impl;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import vg.civcraft.mc.civmodcore.api.ItemAPI;
import vg.civcraft.mc.namelayer.group.log.LoggedGroupActionPersistence;
import vg.civcraft.mc.namelayer.group.log.abstr.PropertyChange;

public class SetPassword extends PropertyChange {

	public static final String ID = "SET_PASSWORD";
	
	public SetPassword(long time, UUID player, String oldValue, String newValue) {
		super(time, player, oldValue, newValue);
	}
	
	public SetPassword(LoggedGroupActionPersistence persist) {
		this(persist.getTimeStamp(), persist.getPlayer(), persist.getRank(), persist.getName());
	}
	
	private String getStateChange() {
		if (oldValue == null) {
			return "added a";
		}
		if (newValue == null) {
			return "removed the";
		}
		return "updated the";
	}
	
	@Override
	public ItemStack getGUIRepresentation() {
		ItemStack is = new ItemStack(Material.ACACIA_DOOR);
		ItemAPI.setDisplayName(is, String.format("%s%s%s %s group password", ChatColor.GOLD,
				getPlayerName(), ChatColor.GREEN, getStateChange()));
		enrichItem(is);
		return is;
	}

	@Override
	public String getChatRepresentation() {
		return String.format("%s%s%s %s group password", ChatColor.GOLD, getPlayerName(), ChatColor.GREEN, getStateChange());
	}

	@Override
	public String getIdentifier() {
		return ID;
	}

}
