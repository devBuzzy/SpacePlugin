package io.github.LilParker.SpacePlugin.Commands;

import io.github.LilParker.SpacePlugin.SpacePlugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCrew extends SpacePluginCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,	String[] args) {
		if(checkSafeCommand(sender, cmd, label, args, 0, 3, "crew")){
			executeCommand(sender, args);
			return true;
		}
		return false;
	}

	private void executeCommand(CommandSender sender, String[] args){
		if(args.length == 0){
			sender.sendMessage("Crew command help");
			sender.sendMessage("/crew crew: Shows your current crew members and captain");
			sender.sendMessage("/crew invite (player): Invites a player to your crew if you are captain.");
			sender.sendMessage("/crew invite accept: accepts the most recent crew invite.");
			sender.sendMessage("/crew invite deny: denies the most recent crew invite.");
		}else if(args.length != 0){
			if(args[0].equals("crew")){
			String crew = "Crew list: ";
			for(Player player : Bukkit.getOnlinePlayers()){
				String[] playerData = SpacePlugin.playerData.get(player.getName());
					if(playerData[0].equals(SpacePlugin.playerData.get(sender.getName())[0])){
						if(SpacePlugin.playerData.get(player.getName())[0].equals(player)){
							crew = crew + "Captain " + player.getName() + ", ";
						}else{
							crew = crew + player.getName() + ", ";
						}
					}
				}
			}
		}
	}
}
