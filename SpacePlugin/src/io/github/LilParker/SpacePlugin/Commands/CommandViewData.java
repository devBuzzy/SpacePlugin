package io.github.LilParker.SpacePlugin.Commands;

import io.github.LilParker.SpacePlugin.SpacePlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandViewData extends SpacePluginCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(checkSafeCommand(sender, cmd, label, args, 1, 1, "viewdata")){
			executeCommand(sender, args);
			return true;
		}
		return false;
	}

	private void executeCommand(CommandSender sender, String[] args){
		if(SpacePlugin.playerData.containsKey(args[0])){
			String[] playerData = SpacePlugin.playerData.get(args[0]);
			String dataMessage = "Data: ";
			for(String data : playerData){
				dataMessage = dataMessage + data + ", ";
			}
			sender.sendMessage(dataMessage);
		}else{
			sender.sendMessage("That player has no data. Please contact your server owner and inform them.");
		}
	}
}
