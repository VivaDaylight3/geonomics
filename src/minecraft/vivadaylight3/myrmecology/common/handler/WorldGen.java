package vivadaylight3.myrmecology.common.handler;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import vivadaylight3.myrmecology.api.block.BlockAntHill;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Maths;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator {

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

	Random rand = new Random();
	
	int chance = rand.nextInt(Register.hillSpawnRate)+1;
	
	if(chance == 1){
	
	    generateAntHill(world, random, i, j);
	
	}

	int blockX = i + random.nextInt(16);
	int blockZ = j + random.nextInt(16);
	int blockY = world.getHeightValue(blockX, blockZ);

	BiomeGenBase biome = world.getBiomeGenForCoords(i, j);

	if (biome == BiomeGenBase.swampland) {

	    world.setBlock(blockX, blockY, blockZ, Register.blockFungi.blockID);

	}

    }

    private void generateEnd(World world, Random random, int i, int j) {

    }

    private void generateAntHill(World world, Random random, int i, int j) {

	int blockX = i + random.nextInt(16);
	int blockZ = j + random.nextInt(16);
	int blockY = world.getHeightValue(blockX, blockZ);

	BiomeGenBase biome = world.getBiomeGenForCoords(i, j);

	Object[] hillArray = Register.getHillList().toArray();

	for (int k = 0; k < Register.getHillList().toArray().length; k++) {

	    BlockAntHill currentHill = (BlockAntHill) Register.getHillList()
		    .toArray()[k];

	    if (currentHill.usesNativeGeneration()) {

		// currentHill.generate(world, blockX, blockY, blockZ);

		blockY += currentHill.getGenerationHeightOffset(world, blockX,
			blockY, blockZ);

		if (currentHill.canGenerate(world, blockX, blockY, blockZ)) {

		    if (currentHill.isUnderground()) {

			int maxY = 55;

			blockY = random.nextInt(maxY);

			new WorldGenMinable(currentHill.blockID, 1).generate(
				world, random, blockX, blockY, blockZ);

		    } else {

			world.setBlock(blockX, blockY, blockZ,
				currentHill.blockID);

		    }

		}

	    }

	}
    }

}
