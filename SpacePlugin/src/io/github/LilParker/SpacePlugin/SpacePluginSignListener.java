package io.github.LilParker.SpacePlugin;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpacePluginSignListener implements Listener{

	@EventHandler
	public void onSignRightClick(PlayerInteractEvent e){
		if(e.getClickedBlock().getState() instanceof Sign){
			Sign sign = (Sign) e.getClickedBlock().getState();
			if(sign.getLine(0).equals("[Test]")){
				e.getPlayer().sendMessage(sign.getLine(1));
			}
		}
	}
}
