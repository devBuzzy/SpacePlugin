package io.github.LilParker.SpacePlugin.Commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class CommandPlaceBlock extends SpacePluginCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(checkSafeCommand(sender, cmd, label, args, 0, 0, "bbf")){
			executeCommand(sender);
			return true;
		}
		return false;
	}
	
	public void executeCommand (CommandSender sender){
		Location block = new Location(((Player)sender).getWorld(), ((Player)sender).getLocation().getBlockX(), ((Player)sender).getLocation().getBlockY() - 1, ((Player)sender).getLocation().getBlockZ());
		block.getBlock().setType(Material.BEDROCK);
	}
}
