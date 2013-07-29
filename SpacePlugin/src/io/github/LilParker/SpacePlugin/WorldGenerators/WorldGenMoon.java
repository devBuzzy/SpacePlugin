package io.github.LilParker.SpacePlugin.WorldGenerators;

import io.github.LilParker.SpacePlugin.BlockPopulators.BlockPopCrater;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.*;

public class WorldGenMoon extends ChunkGenerator{
	
	private int middle = 64;
	
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world){
		List<BlockPopulator> pops = new ArrayList<BlockPopulator>();
		pops.add(new BlockPopCrater());
		return pops;
		}
	
	
	public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomes){
		byte[][] result = new byte[world.getMaxHeight() / 16][];
		PerlinOctaveGenerator gen = new PerlinOctaveGenerator(world, 8);
		SimplexOctaveGenerator gen2 = new SimplexOctaveGenerator(world, 8);
		gen.setScale(1/64.0);
		gen2.setScale(1/128.0);
		for(int x = 0; x < 16; x++){
			for(int z = 0; z< 16; z++){
				int realX = x + chunkX * 16;
				int realZ = z + chunkZ * 16;
				
				double height = middle + gen.noise(realX, realZ, 0.5, 0.5) * middle/10;
				
				for(int y = 0; y < height; y++){
					setBlock(x, y, z, result, Material.STONE);
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
