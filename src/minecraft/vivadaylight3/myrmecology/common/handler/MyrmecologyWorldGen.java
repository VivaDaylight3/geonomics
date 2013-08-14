package vivadaylight3.myrmecology.common.handler;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import vivadaylight3.myrmecology.api.BlockAntHill;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Maths;
import vivadaylight3.myrmecology.common.lib.Register;
import cpw.mods.fml.common.IWorldGenerator;

public class MyrmecologyWorldGen implements IWorldGenerator {
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
	    IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
	switch (world.provider.dimensionId) {
	
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
    
    private void generateNether(World world, Random random, int i, int j) {
	
    }
    
    private void generateSurface(World world, Random random, int i, int j) {
	
	int chance = Maths.getChance(9);
	
	if (chance < 11) {
	    
	    generateAntHill(world, random, i, j);
	    
	}
	
    }
    
    private void generateEnd(World world, Random random, int i, int j) {
	
    }
    
    private void generateAntHill(World world, Random random, int i, int j) {
	
	int blockX = i + random.nextInt(16);
	int blockZ = j + random.nextInt(16);
	int blockY = world.getHeightValue(blockX, blockZ) + 1;
	
	BiomeGenBase biome = Environment.getBiome(world, i, j);
	int meta = 0;
	
	Object[] hillArray = Register.getHillList().toArray();
	
	for (int k = 0; k < Register.getHillList().toArray().length; k++) {
	    
	    BlockAntHill currentHill = (BlockAntHill) Register.getHillList()
		    .toArray()[k];
	    
	    if (currentHill.usesNativeGeneration()) {
		
		for (int a = 0; a < currentHill.getHillBiomes().length; a++) {
		    
		    if (currentHill.getHillBiomes()[a] == biome) {
			
			if (currentHill.getRequiredTouchingBlocks() != null) {
			    
			    for (int b = 0; b < currentHill
				    .getRequiredTouchingBlocks().length; b++) {
				
				if (Environment
					.blockIsTouching(
						world,
						blockX,
						blockY,
						blockZ,
						currentHill
							.getRequiredTouchingBlocks()[b])) {
				    
				    world.setBlock(blockX, blockY, blockY,
					    currentHill.blockID);
				    
				}
				
			    }
			    
			} else {
			    
			    world.setBlock(blockX, blockY, blockY,
				    currentHill.blockID);
			    
			}
			
		    }
		    
		}
		
	    }
	    
	}
	/*
	 * if (biome instanceof BiomeGenForest){
	 * 
	 * if (isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.wood.blockID) || isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.leaves.blockID)){
	 * 
	 * world.setBlock(blockX, blockY, blockZ,
	 * Register.blockAntHill.blockID);
	 * world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
	 * 
	 * }
	 * 
	 * }else if (biome instanceof BiomeGenHills){
	 * 
	 * meta = BlockAntHill.hillMeta[1];
	 * 
	 * if (isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.grass.blockID)){
	 * 
	 * world.setBlock(blockX, blockY, blockZ,
	 * Register.blockAntHill.blockID);
	 * world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
	 * 
	 * }
	 * 
	 * }else if (biome instanceof BiomeGenDesert){
	 * 
	 * meta = BlockAntHill.hillMeta[2];
	 * 
	 * if (isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.sand.blockID)){
	 * 
	 * world.setBlock(blockX, blockY, blockZ,
	 * Register.blockAntHill.blockID);
	 * world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
	 * 
	 * }
	 * 
	 * }else if (biome instanceof BiomeGenSwamp){
	 * 
	 * meta = BlockAntHill.hillMeta[3];
	 * 
	 * if (isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.grass.blockID)){
	 * 
	 * world.setBlock(blockX, blockY, blockZ,
	 * Register.blockAntHill.blockID);
	 * world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
	 * 
	 * }
	 * 
	 * }else if (biome instanceof BiomeGenPlains){
	 * 
	 * meta = BlockAntHill.hillMeta[4];
	 * 
	 * if (isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.grass.blockID)){
	 * 
	 * world.setBlock(blockX, blockY, blockZ,
	 * Register.blockAntHill.blockID);
	 * world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
	 * 
	 * }
	 * 
	 * }else if (biome instanceof BiomeGenJungle){
	 * 
	 * meta = BlockAntHill.hillMeta[5];
	 * 
	 * if (isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.grass.blockID)){
	 * 
	 * world.setBlock(blockX, blockY, blockZ,
	 * Register.blockAntHill.blockID);
	 * world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
	 * 
	 * }
	 * 
	 * }else if (biome instanceof BiomeGenSnow || biome instanceof
	 * BiomeGenTaiga){
	 * 
	 * meta = BlockAntHill.hillMeta[6];
	 * 
	 * if (isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.dirt.blockID) || isBlockTouching(world, blockX, blockY, blockZ,
	 * Block.grass.blockID) || isBlockTouching(world, blockX, blockY,
	 * blockZ, Block.snow.blockID)){
	 * 
	 * world.setBlock(blockX, blockY, blockZ,
	 * Register.blockAntHill.blockID);
	 * world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta, 3);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */
	
    }
}
