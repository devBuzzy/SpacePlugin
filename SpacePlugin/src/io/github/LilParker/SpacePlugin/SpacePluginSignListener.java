package io.github.LilParker.SpacePlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpacePluginSignListener implements Listener{

	@EventHandler
	public void onSignRightClick(final PlayerInteractEvent e){
		if(e.getClickedBlock() != null){
			if(e.getClickedBlock().getState() instanceof Sign){
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).equals("[Buy Ship]")){
					File file = new File(Bukkit.getServer().getWorldContainer(), sign.getLine(1)); 
					if(file.exists()){
					String shipName = sign.getLine(1);
					File worldFolder = Bukkit.getWorldContainer();
					File[] playerShips = worldFolder.listFiles(new FilenameFilter() {
						@Override
						public boolean accept(final File dir, final String name){
							return name.matches("Ship" + e.getPlayer().getName() + "*");
						}
					});
				     try {
				    	    File old = new File(Bukkit.getWorldContainer().getAbsolutePath() + File.separator + shipName);
				    	    File mew = new File(Bukkit.getWorldContainer().getAbsolutePath() + File.separator + "Ship" + e.getPlayer().getName() + playerShips.length);
				    	     
				    	    InputStream in = new FileInputStream(mew);
				    	    OutputStream out = new FileOutputStream(old);
				    	     
				    	    byte[] buff = new byte[1024];
				    	     
				    	    int length;
				    	     
				    	    while((length = in.read(buff)) > 0)out.write(buff, 0, length);
				    	     
				    	    in.close();
				    	    out.close();
				    	     
				    	    	} catch(IOException ex){
				    	    		ex.printStackTrace();
				    	    }
						int shipCost = Integer.parseInt(sign.getLine(2));
						e.getPlayer().sendMessage("You bought a " + shipName + " for " + shipCost + ".");
						SpacePlugin.economy.withdrawPlayer(e.getPlayer().getName(), shipCost);
						e.getPlayer().sendMessage("Your new balance is " + SpacePlugin.economy.getBalance(e.getPlayer().getName()));
						Bukkit.createWorld(WorldCreator.name("Ship" + e.getPlayer().getName() + playerShips.length));
						e.getPlayer().teleport(new Location(Bukkit.getWorld("Ship" + e.getPlayer().getName() + playerShips.length), 0, 65 ,0));
						e.getPlayer().sendMessage("You have been teleported to your new ship");
					}else{
						e.getPlayer().sendMessage("No ship by that name. Please tell your server administrator so they can correct the problem.");
					}
				}
			}
		}
	}
}
