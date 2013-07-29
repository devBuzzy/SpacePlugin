package io.github.LilParker.SpacePlugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDimTele extends SpacePluginCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(checkSafeCommand(sender, cmd, label, args, 1, 1, "tpd")){
			if(Bukkit.getWorld(args[0]) != null){
				executeCommand(sender, args);
				return true;
			}else{
				sender.sendMessage("Bad world name");
				return true;
			}
		}
		return false;
	}
	
	public void executeCommand (CommandSender sender, String[] args){
		Player p = (Player)sender;
		p.teleport(new Location(Bukkit.getWorld(args[0]), 0,65,0));
	}
}
