package io.github.LilParker.SpacePlugin.WorldGenerators;

import io.github.LilParker.SpacePlugin.BlockPopulators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.bukkit.util.noise.PerlinOctaveGenerator;

public class WorldGenHillTest extends ChunkGenerator{

	int middle = 64;
	double scale = 16.0;
	double threshold = 0.0;
	
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world){
		List<BlockPopulator> pops = new ArrayList<BlockPopulator>();
		pops.add(new BlockPopLavaLakes());
		pops.add(new BlockPopOres());
		return pops;
	}
	
	@Override
	public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomes){
		Random rand = new Random(world.getSeed());
		SimplexOctaveGenerator gen = new SimplexOctaveGenerator(rand, 8);
		PerlinOctaveGenerator gen2 = new PerlinOctaveGenerator(world.getSeed(), 8);
		gen.setScale(1/scale); 
		gen2.setScale(1/scale);
		byte[][] result = new byte[world.getMaxHeight() / 16][];
		for(int x = 0; x < 16; x++){
			for(int z = 0; z < 16; z++){
				int realx = x + chunkX * 16;
				int realz = z + chunkZ * 16;
				double height = middle + gen2.noise(realx, realz, 1, 1)*middle/3;
				for(int y = 0; y < height && y < 256; y++){
					if(y > middle-middle/3) {
						double noise = gen.noise(realx, y, realz, 0.5, 0.5);
						if(noise > threshold){
							setBlock( x, y, z, result, Material.STONE);
						}
					}
					setBlock( x, y, z, result, Material.STONE);
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
