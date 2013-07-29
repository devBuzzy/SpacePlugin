package io.github.LilParker.SpacePlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpacePluginLoginListener implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		player.sendMessage("Tunnel snakes rule!");
		if(SpacePlugin.playerData.containsKey(player.getName()) != true){
			player.sendMessage("Have some data!");
			String[] defaultData = new String[2];
			defaultData[0] = player.getName();
			SpacePlugin.playerData.put(player.getName(), defaultData);
		}
	}
}
