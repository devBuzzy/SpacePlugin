package io.github.LilParker.SpacePlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.github.LilParker.SpacePlugin.Commands.*;

import org.bukkit.plugin.java.JavaPlugin;

public final class SpacePlugin extends JavaPlugin{
	
	public static HashMap<String, String> worldChunkGens = new HashMap<String, String>();
	
	//HashMap that holds player data and stuff. Current prefs format is: {Crew Captain, Crew Captain of most recent invite}
	public static HashMap<String, String[]> playerData = new HashMap<String, String[]>();
	
	@Override
	public void onEnable(){
		
		if(getConfig().getConfigurationSection("worldChunkGens") != null){
		Map<String, Object> chunkGensMap = getConfig().getConfigurationSection("worldChunkGens").getValues(false);
		for(Entry<String, Object> entry : chunkGensMap.entrySet()){
			worldChunkGens.put((String) entry.getKey(), (String) entry.getValue());
			}
		}
		
		if(getConfig().getConfigurationSection("playerData") != null){
			Map<String, Object> playerDataMap = getConfig().getConfigurationSection("playerData").getValues(false);
			for(Entry<String, Object> entry : playerDataMap.entrySet()){
				String[] values = (String[]) entry.getValue();
				playerDataMap.put((String) entry.getKey(), values);
				}
			}
		
		getCommand("generate").setExecutor(new CommandGenerate());
		getCommand("tpd").setExecutor(new CommandDimTele());
		getCommand("unload").setExecutor(new CommandUnloadWorld());
		getCommand("bbf").setExecutor(new CommandPlaceBlock());
		getCommand("load").setExecutor(new CommandLoadWorld());
		getCommand("crew").setExecutor(new CommandCrew());
		
		getServer().getPluginManager().registerEvents(new SpacePluginLoginListener(), this);
	}
	
	@Override
	public void onDisable(){
		getConfig().createSection("worldChunkGens", worldChunkGens);
		getConfig().createSection("playerData", playerData);
		saveConfig();
	}
}
