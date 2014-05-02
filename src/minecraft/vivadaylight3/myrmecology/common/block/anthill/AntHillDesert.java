package vivadaylight3.myrmecology.common.block.anthill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.block.BlockAntHill;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Environment;

public class AntHillDesert extends BlockAntHill {

    public AntHillDesert() {
	super(Material.ground);
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antDesert;
    }

    @Override
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Desert Ant Hill";
    }

    @Override
    public String getHillSubName() {
	return Reference.HILL_DESERT_NAME;
    }

    @Override
    public BiomeGenBase[] getHillBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.desert,
		BiomeGenBase.desertHills };

	return biomes;

    }

    @Override
    public boolean usesNativeGeneration() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean canGenerate(World world, int x, int y, int z) {

	int radius = 1;

	int[] blocks = new int[radius];

	blocks = Environment.getBlocksFrom("y", radius, world, x, y, z);

	if (blocks[0] != Block.ice.blockID
		&& blocks[0] != Block.waterStill.blockID) {

	    for (int k = 0; k < this.getHillBiomes().length; k++) {

		if (world.getBiomeGenForCoords(x, z) == this.getHillBiomes()[k]) {

		    return true;

		}

	    }

	}

	return false;

    }

    @Override
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 2;
    }

}
