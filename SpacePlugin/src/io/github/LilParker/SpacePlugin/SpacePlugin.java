package io.github.LilParker.SpacePlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.github.LilParker.SpacePlugin.Commands.*;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpacePlugin extends JavaPlugin{
	
	public static HashMap<String, String> worldChunkGens = new HashMap<String, String>();
	
	//HashMap that holds player data and stuff. Current prefs format is: {Crew Captain, Crew Captain of most recent invite}
	public static HashMap<String, String[]> playerData = new HashMap<String, String[]>();
	
	public static Economy economy = null;
	
	@Override
	public void onEnable(){
		
		if(getConfig().getConfigurationSection("worldChunkGens") != null){
		Map<String, Object> chunkGensMap = getConfig().getConfigurationSection("worldChunkGens").getValues(false);
		for(Entry<String, Object> entry : chunkGensMap.entrySet()){
			worldChunkGens.put((String) entry.getKey(), (String) entry.getValue());
			}
		}
		
		if(getConfig().getConfigurationSection("playerData") != null){
			ConfigurationSection playerDataSection = getConfig().getConfigurationSection("playerData");
			for(String username : playerDataSection.getKeys(false)){
				ArrayList<String> tempVals = new ArrayList<String>();
				for(Object valObj : getConfig().getList("playerData." + username)){
					if(valObj != null){
						String val = valObj.toString();
						tempVals.add(val);
						}else{
							tempVals.add(null);
						}
					}
				String[] values = new String[tempVals.size()];
				tempVals.toArray(values);
				playerData.put(username, values);
				}
			}
		
		getCommand("generate").setExecutor(new CommandGenerate());
		getCommand("tpd").setExecutor(new CommandDimTele());
		getCommand("unload").setExecutor(new CommandUnloadWorld());
		getCommand("bbf").setExecutor(new CommandPlaceBlock());
		getCommand("load").setExecutor(new CommandLoadWorld());
		getCommand("crew").setExecutor(new CommandCrew());
		getCommand("viewdata").setExecutor(new CommandViewData());
		
		getServer().getPluginManager().registerEvents(new SpacePluginLoginListener(), this);
		getServer().getPluginManager().registerEvents(new SpacePluginSignListener(), this);
		
		setupEconomy();
	}
	
	@Override
	public void onDisable(){
		getConfig().createSection("worldChunkGens", worldChunkGens);
		getConfig().createSection("playerData", playerData);
		saveConfig();
		worldChunkGens = null;
		playerData = null;
	}
	
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
}
