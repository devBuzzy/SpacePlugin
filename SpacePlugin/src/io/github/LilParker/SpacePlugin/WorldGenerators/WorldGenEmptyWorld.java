package io.github.LilParker.SpacePlugin.WorldGenerators;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

public class WorldGenEmptyWorld extends ChunkGenerator{
	
	@Override
	public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomes){
		byte[][] result = new byte[world.getMaxHeight() / 16][];
		for(int x = 0; x < 16; x++){
			for(int z = 0; z < 16; z++){
		biomes.setBiome(x, z, Biome.DESERT);
			}
		}
		return result;
	}
}
