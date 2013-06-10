package vivadaylight3.myrmecology.common.handler;

import java.util.Random;

import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.block.BlockAntHill;
import vivadaylight3.myrmecology.common.lib.Blocks;
import vivadaylight3.myrmecology.common.lib.Maths;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class MyrmecologyWorldGen implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId){
			
			case -1:
				generateNether(world, random, chunkX * 16, chunkZ * 16);
				break;
				
			case 0:
				generateSurface(world, random, chunkX * 16, chunkZ * 16);
				break;
				
			case 1:
				generateEnd(world, random, chunkX * 16, chunkZ * 16);
				break;
			
		}
	}
	
	private void generateNether(World world, Random random, int i, int j){
		
	}
	
	private void generateSurface(World world, Random random, int i, int j){
		
		int chance = Maths.getChance(9);
		
		if (chance < 11){
			
			generateAntHill(world, random, i, j);
			
		}
		
	}
	
	private void generateEnd(World world, Random random, int i, int j){
		
	}
	
	private void generateAntHill(World world, Random random, int i, int j){
		
		int blockX = i + random.nextInt(16);
		int blockZ = j + random.nextInt(16);
		int blockY = world.getHeightValue(blockX, blockZ) + 1;
		
		BiomeGenBase biome = Blocks.getBiome(world, i, j);
		int meta = 0;
		
		if (biome instanceof BiomeGenForest){
			
			meta = BlockAntHill.hillMeta[0];
			
			if (isBlockTouching(world, blockX, blockY, blockZ, Block.wood.blockID) || isBlockTouching(world, blockX, blockY, blockZ, Block.leaves.blockID)){
				
				world.setBlock(blockX, blockY, blockZ, Myrmecology.blockAntHillID);
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
				
			}
			
		}else if (biome instanceof BiomeGenHills){
			
			meta = BlockAntHill.hillMeta[1];
			
			if (isBlockTouching(world, blockX, blockY, blockZ, Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ, Block.grass.blockID)){
				
				world.setBlock(blockX, blockY, blockZ, Myrmecology.blockAntHillID);
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
				
			}
			
		}else if (biome instanceof BiomeGenDesert){
			
			meta = BlockAntHill.hillMeta[2];
			
			if (isBlockTouching(world, blockX, blockY, blockZ, Block.sand.blockID)){
				
				world.setBlock(blockX, blockY, blockZ, Myrmecology.blockAntHillID);
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
				
			}
			
		}else if (biome instanceof BiomeGenSwamp){
			
			meta = BlockAntHill.hillMeta[3];
			
			if (isBlockTouching(world, blockX, blockY, blockZ, Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ, Block.grass.blockID)){
				
				world.setBlock(blockX, blockY, blockZ, Myrmecology.blockAntHillID);
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
				
			}
			
		}else if (biome instanceof BiomeGenPlains){
			
			meta = BlockAntHill.hillMeta[4];
			
			if (isBlockTouching(world, blockX, blockY, blockZ, Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ, Block.grass.blockID)){
				
				world.setBlock(blockX, blockY, blockZ, Myrmecology.blockAntHillID);
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
				
			}
			
		}else if (biome instanceof BiomeGenJungle){
			
			meta = BlockAntHill.hillMeta[5];
			
			if (isBlockTouching(world, blockX, blockY, blockZ, Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ, Block.grass.blockID)){
				
				world.setBlock(blockX, blockY, blockZ, Myrmecology.blockAntHillID);
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
				
			}
			
		}else if (biome instanceof BiomeGenSnow || biome instanceof BiomeGenTaiga){
			
			meta = BlockAntHill.hillMeta[6];
			
			if (isBlockTouching(world, blockX, blockY, blockZ, Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ, Block.grass.blockID) || isBlockTouching(world, blockX, blockY, blockZ, Block.snow.blockID)){
				
				world.setBlock(blockX, blockY, blockZ, Myrmecology.blockAntHillID);
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
				
			}
			
		}
		
	}
	
	public boolean isBlockTouching(World world, int blockID, int x, int y, int z){
		
		if(world.getBlockId(x, y-1, z) == blockID || 
			world.getBlockId(x, y+1, z) == blockID || 
			world.getBlockId(x-1, y, z) == blockID || 
			world.getBlockId(x+1, y, z) == blockID || 
			world.getBlockId(x, y, z-1) == blockID || 
			world.getBlockId(x, y, z+1) == blockID){
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}

}
