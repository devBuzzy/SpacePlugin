package io.github.LilParker.SpacePlugin.Commands;

import io.github.LilParker.SpacePlugin.SpacePlugin;
import io.github.LilParker.SpacePlugin.WorldGenerators.*;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.*;

public class CommandLoadWorld extends SpacePluginCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(checkSafeCommand(sender, cmd, label, args, 1, 1, "load")){
			File file = new File(Bukkit.getServer().getWorldContainer(), args[0]); 
			if(file.exists()){
				executeCommand (sender, args);
			}else{
				sender.sendMessage("No world by that name");
				return true;
			}
		}
		return false;
	}
	
	public void executeCommand (CommandSender sender, String[] args) {
		WorldCreator wc = null;
		if(SpacePlugin.worldChunkGens.get(args[0]).equals("0")){
			wc = new WorldCreator(args[0]).generator(new WorldGenEmptyWorld());
		}else if(SpacePlugin.worldChunkGens.get(args[0]).equals("1")){
			wc = new WorldCreator(args[0]).generator(new WorldGenHillTest());
		}else if(SpacePlugin.worldChunkGens.get(args[0]).equals("2")){
			wc = new WorldCreator(args[0]).generator(new WorldGenOverhangTest());
		}else if(SpacePlugin.worldChunkGens.get(args[0]).equals("3")){
			wc = new WorldCreator(args[0]).generator(new WorldGenFloatingIslands());
		}else if(SpacePlugin.worldChunkGens.get(args[0]).equals("4")){
			wc = new WorldCreator(args[0]).generator(new WorldGenMoon());
		}else{
			wc = new WorldCreator(args[0]);
		}
		Bukkit.createWorld(wc);
		sender.sendMessage("Loaded " + args[0]);
	}
}
