package io.github.LilParker.SpacePlugin.Commands;

import java.io.File;

import io.github.LilParker.SpacePlugin.SpacePlugin;
import io.github.LilParker.SpacePlugin.WorldGenerators.*;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandGenerate extends SpacePluginCommand implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(checkSafeCommand(sender, cmd, label, args, 2, 2, "generate")){
			 File file = new File(Bukkit.getServer().getWorldContainer(), args[1]);
			if(file.exists() == false){
				executeCommand(sender, args);
				return true;
			}else{
				sender.sendMessage("World already exists");
				return true;
			}
		}
		return false;
	}
	
	private void executeCommand(CommandSender sender, String[] args){
		SpacePlugin.worldChunkGens.put(args[1], args[0]);
		if(args[0].equals("0")){
			Bukkit.createWorld(WorldCreator.name(args[1]).generator(new WorldGenEmptyWorld()));
			sender.sendMessage("Generated map " + args[1] + ". Used empty map.");
		}else if(args[0].equals("1")){
			Bukkit.createWorld(WorldCreator.name(args[1]).generator(new WorldGenHillTest()));
			sender.sendMessage("Generated map " + args[1] + ". Used hills terrain.");
		}else if(args[0].equals("2")){
			Bukkit.createWorld(WorldCreator.name(args[1]).generator(new WorldGenOverhangTest()));
			sender.sendMessage("Generated map " + args[1] + ". Used overhangs terrain.");
		}else if(args[0].equals("3")){
			Bukkit.createWorld(WorldCreator.name(args[1]).generator(new WorldGenFloatingIslands()));
			sender.sendMessage("Generated map " + args[1] + ". Used floating island terrain.");
		}else if (args[0].equals("4")){
			Bukkit.createWorld(WorldCreator.name(args[1]).generator(new WorldGenMoon()));
			sender.sendMessage("Generated map " + args[1] + ". Used moon terrain.");
		}else{
			sender.sendMessage("That gen id didn't match any. Are you sure you entered a valid number?");
		}
		
		
	}
}
