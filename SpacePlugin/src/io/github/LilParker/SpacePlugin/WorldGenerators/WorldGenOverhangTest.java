package io.github.LilParker.SpacePlugin.WorldGenerators;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class WorldGenOverhangTest extends ChunkGenerator{

	@Override
	public byte[][] generateBlockSections(World world, Random rand, int ChunkX, int ChunkZ, BiomeGrid biome) {
		byte[][] result = new byte [world.getMaxHeight() / 16][];
		SimplexOctaveGenerator overhangs = new SimplexOctaveGenerator(world, 8); 
		SimplexOctaveGenerator bottoms = new SimplexOctaveGenerator(world, 8);
		
		overhangs.setScale(1/64.0);
		bottoms.setScale(1/128.0);
		
		int overhangsMagnitude = 16;
		int bottomsMagnitude = 32;
		
		for(int x = 0; x < 16; x++){
			for(int z = 0; z < 16; z++){
				int realX = x + ChunkX * 16;
				int realZ = z + ChunkZ * 16;
				
				int bottomHeight = (int) (bottoms.noise(realX, realZ, 0.5, 0.5) * bottomsMagnitude + 64);
				int maxHeight = (int) overhangs.noise(realX, realZ, 0.5, 0.5) * overhangsMagnitude + bottomHeight + 32;
				
				double threshold = 0.5;
				
				for(int y = 0; y < maxHeight; y++){
					if(y > bottomHeight) {
						double density = overhangs.noise(realX, y, realZ, 0.5, 0.5);
						
						if(density > threshold) setBlock(x, y, z, result, Material.BEDROCK);
						}else{
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
	 
	 byte getBlock(int x, int y, int z, byte[][] chunk) {
         if (chunk[y >> 4] == null)
                 return 0; 
         if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
                 return 0;
         try {
                 return chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x];
         } catch (Exception e) {
                 e.printStackTrace();
                 return 0;
         }
	 }
}
