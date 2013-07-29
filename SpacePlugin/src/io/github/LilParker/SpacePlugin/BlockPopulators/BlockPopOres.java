package io.github.LilParker.SpacePlugin.BlockPopulators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class BlockPopOres extends BlockPopulator {

	@Override
	public void populate(World world, Random rand, Chunk chunk) {
		for(int x = 0; x < 16; x++){
			for(int z = 0; z < 16; z++){
				for(int y = 74; y < 256 && y > 75 ; y++){
					int realX = x + chunk.getX() * 16;
					int realZ = z + chunk.getZ() * 16;
					if(world.getBlockAt(realX, y, realZ).getType() == Material.STONE){
						world.getBlockAt(realX, y, realZ).setType(Material.OBSIDIAN);
					}		
				}
			}
		}
	}

}
