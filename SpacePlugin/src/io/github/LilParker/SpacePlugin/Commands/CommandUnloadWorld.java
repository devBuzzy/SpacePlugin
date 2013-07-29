package io.github.LilParker.SpacePlugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandUnloadWorld extends SpacePluginCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(checkSafeCommand(sender, cmd, label, args, 1, 1, "unload")){
			if(Bukkit.getWorld(args[0]) != null){
				executeCommand (sender, args);
				return true;
			}else{
				sender.sendMessage("Not a world");
				return true;
			}
		}
		return false;
	}
	
	public void executeCommand (CommandSender sender, String[] args){
		Bukkit.getServer().unloadWorld(args[0], true);
		sender.sendMessage("Unloaded " + args[0]);
	}
}
