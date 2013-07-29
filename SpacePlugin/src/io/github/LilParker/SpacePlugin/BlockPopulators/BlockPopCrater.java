package io.github.LilParker.SpacePlugin.BlockPopulators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

public class BlockPopCrater extends BlockPopulator{

	int craterChance = 45;
	int craterSmallSize = 3;
	int craterMedSize = 8;
	int craterBigSize = 16;
	int craterBigChance = 10;
	
	@Override
	public void populate(World world, Random rand, Chunk source) {
		if(rand.nextInt(100) <= craterChance){
            int centerX = (source.getX() << 4) + rand.nextInt(16);
            int centerZ = (source.getZ() << 4) + rand.nextInt(16);
            int centerY = world.getHighestBlockYAt(centerX, centerZ);
            Vector center = new BlockVector(centerX, centerY, centerZ);
            int radius = 0;
            
            if(rand.nextInt(100) <= craterBigChance){
            	radius = rand.nextInt(craterBigSize - craterSmallSize + 1) + craterSmallSize;
            }else{
            	radius = rand.nextInt(craterMedSize - craterSmallSize + 1) + craterSmallSize;
            }
            
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        Vector position = center.clone().add(new Vector(x, y, z));

                        if (center.distance(position) <= radius + 0.5) {
                            world.getBlockAt(position.toLocation(world)).setType(Material.AIR);
                        }
                    }
                }
            }
		}
	}
}
