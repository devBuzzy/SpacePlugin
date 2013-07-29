package io.github.LilParker.SpacePlugin.WorldGenerators;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.*;

public class WorldGenFloatingIslands extends ChunkGenerator{

	@Override
	public byte[][] generateBlockSections(World world, Random rand, int ChunkX, int ChunkZ, BiomeGrid biome) {
		byte[][] result = new byte[world.getMaxHeight() / 16][];
		SimplexOctaveGenerator gen = new SimplexOctaveGenerator(world, 8);
		gen.setScale(1/64.0);
		double threshold = 0.5;
		for(int x = 0; x < 16; x++){
			for(int z = 0; z < 16; z++){
				int realX = x + ChunkX * 16;
				int realZ = z + ChunkZ * 16;
				
				for(int y = 0; y < world.getMaxHeight(); y++){
					double noise = gen.noise(realX, y, realZ, 1.0, 1.0);
					if(noise > threshold){
						setBlock(x, y, z, result, Material.STONE);
					}
				}
			}
		}
		return result;
	}
	
	 void setBlock(int x, int y, int z, byte[][] chunk, Material material) {
	        if (chunk[y>>4] == null)
	            chunk[y>>4] = new byte[16*16*16];
	        if (!(y<=256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
	            return;
	        try {
	            chunk[y>>4][((y & 0xF) << 8) | (z << 4) | x] = (byte)material.getId();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
