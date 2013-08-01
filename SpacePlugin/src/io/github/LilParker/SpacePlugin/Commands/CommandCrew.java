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
			sender.sendMessage("/crew list: Shows your current crew members and captain");
			sender.sendMessage("/crew invite (player): Invites a player to your crew if you are captain.");
			sender.sendMessage("/crew invite accept: accepts the most recent crew invite.");
			sender.sendMessage("/crew invite deny: denies the most recent crew invite.");
		}else if(args.length != 0){
			if(args[0].equalsIgnoreCase("list")){
				String crew = "Crew list: ";
				for(Player player : Bukkit.getOnlinePlayers()){
					String[] playerData = SpacePlugin.playerData.get(player.getName());
						if(playerData[0].equals(SpacePlugin.playerData.get(sender.getName())[0])){
							if(SpacePlugin.playerData.get(player.getName())[0].equals(player.getName())){
								crew = crew + "Captain " + player.getName() + ", ";
							}else{
								crew = crew + player.getName() + ", ";
						}
					}
				}
				sender.sendMessage(crew);
			}
			if(args[0].equalsIgnoreCase("invite")){
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("accept") == false && args[1].equalsIgnoreCase("deny") == false){
						if(SpacePlugin.playerData.get(sender.getName())[0].equals(sender.getName())){
							if(Bukkit.getPlayer(args[1]) != null){
								Player player = Bukkit.getPlayer(args[1]);
								sender.sendMessage("Invited " + args[1]);
								player.sendMessage("Invited to " + sender.getName() + "'s crew.");
								String[] newValue = SpacePlugin.playerData.get(player.getName());
								newValue[1] = sender.getName();
								SpacePlugin.playerData.put(player.getName(), newValue);
							}else{
								sender.sendMessage("No player by that name");
							}
						}else{
							sender.sendMessage("Must be Captain to invite to crew");
						}
					}else{
						if(args[1].equalsIgnoreCase("accept")){
							String[] values = SpacePlugin.playerData.get(sender.getName());
							values[0] = values[1];
							sender.sendMessage("Joined " + values[0] + "'s crew");
							if(Bukkit.getPlayer(values[0]) != null){
								Bukkit.getPlayer(values[0]).sendMessage(sender.getName() + " has joined your crew.");
							}
							SpacePlugin.playerData.put(sender.getName(), values);
						}else{
							String[] values = SpacePlugin.playerData.get(sender.getName());
							sender.sendMessage("You have denied " + values[1] + "'s crew invite.");
							if(Bukkit.getPlayer(values[1]) != null){
								Bukkit.getPlayer(values[1]).sendMessage(sender.getName() + " has denied your crew invite.");
							}
							values[1] = null;
							SpacePlugin.playerData.put(sender.getName(), values);
						}
					}
				}else{
					sender.sendMessage("Wrong arg count");
				}
			}
		}
	}
}
