package vivadaylight3.myrmecology.common.handler;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import vivadaylight3.myrmecology.api.BlockAntHill;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Maths;
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
	    generateOverworld(world, random, chunkX * 16, chunkZ * 16);
	    break;

	case 1:
	    generateEnd(world, random, chunkX * 16, chunkZ * 16);
	    break;

	}
    }

    private void generateNether(World world, Random random, int i, int j) {

    }

    private void generateOverworld(World world, Random random, int i, int j) {

	if(Maths.chanceOf(1, 16)){
	
	    generateAntHill(world, random, i, j);
	    
	}

    }

    private void generateEnd(World world, Random random, int i, int j) {

    }

    private void generateAntHill(World world, Random random, int i, int j) {

	int blockX = i + random.nextInt(16);
	int blockZ = j + random.nextInt(16);
	int blockY = world.getHeightValue(blockX, blockZ);

	BiomeGenBase biome = world.getBiomeGenForCoords(i, j);// Environment.getBiome(world,
							      // i, j);
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

				    world.setBlock(blockX, blockY, blockZ,
					    currentHill.blockID);

				}

			    }

			} else {

			    if (currentHill.isUnderground()) {

				int minY = 3;
				int maxY = 30;

				blockY = minY + random.nextInt(maxY - minY);

				new WorldGenMinable(currentHill.blockID, 1)
					.generate(world, random, blockX,
						blockY, blockZ);

			    } else {
				
				//(new WorldGenMinable(currentHill.blockID, 1)).generate(world, random, blockX, blockY, blockZ);

				world.setBlock(blockX, blockY, blockZ, currentHill.blockID);

			    }

			}

		    }

		}

	    }

	}

    }
}
