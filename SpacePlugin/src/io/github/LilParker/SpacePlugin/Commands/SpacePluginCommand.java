package io.github.LilParker.SpacePlugin.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpacePluginCommand {

	
	public boolean checkSafeCommand(CommandSender sender, Command cmd, String label, String[] args, int minArgs, int maxArgs, String cmdName) {
		if(cmd.getName().equalsIgnoreCase(cmdName)){
			if(isPlayer(sender)){
				if(isRightArgs (sender, args, minArgs, maxArgs)){
					return true;
				}
			}
		}
		return false;
	}

	
	
	public boolean isPlayer (CommandSender sender) {
		if(sender instanceof Player){
			return true;
		}
		sender.sendMessage("Not a player");
		return false;
	}
	
	
	
	public boolean isRightArgs (CommandSender sender, String[] args, int minArgs, int maxArgs) {
		if(args.length <= maxArgs && args.length >= minArgs){
			return true;
		}
		sender.sendMessage("Wrong arg number");
		return false;
	}
	
	
	
	public boolean isItemInHand (CommandSender sender, int itemId){
		if(((Player)sender).getItemInHand().getTypeId() == itemId){
			return true;
		}
		sender.sendMessage("You must have a " + Material.getMaterial(itemId).name() + " in your hand to use this command");
		return false;
	}
}
