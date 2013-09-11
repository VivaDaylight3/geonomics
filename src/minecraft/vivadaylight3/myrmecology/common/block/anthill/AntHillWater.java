package vivadaylight3.myrmecology.common.block.anthill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.BlockAntHill;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Maths;

public class AntHillWater extends BlockAntHill {

    public AntHillWater(int par1, Material material) {
	super(par1, material);
	// TODO Auto-generated constructor stub
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antWater;
    }

    @Override
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Water Ant Hill";
    }

    @Override
    public String getHillSubName() {
	return Reference.HILL_WATER_NAME;
    }

    @Override
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 2;
    }

    @Override
    public BiomeGenBase[] getHillBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.ocean,
		BiomeGenBase.river };

	return biomes;

    }

    @Override
    public boolean usesNativeGeneration() {
	// TODO Auto-generated method stub
	return true;
    }
    
    @Override
    public int getGenerationHeightOffset(World world, int x, int currentHeight, int z){
	
	return -2;
	
    }

    @Override
    public int[] getRequiredTouchingBlocks() {
	
	/*

	int[] blocks1 = new int[] { Block.sand.blockID,
		Block.waterStill.blockID };
	int[] blocks2 = new int[] { Block.gravel.blockID,
		Block.waterStill.blockID };
	int[] blocks3 = new int[] { Block.blockClay.blockID,
		Block.waterStill.blockID };
	int[] blocks4 = new int[] { Block.dirt.blockID,
		Block.waterStill.blockID };

	int chance = Maths.getChance(3);

	switch (chance) {

	case 1:
	    return blocks1;
	case 2:
	    return blocks2;
	case 3:
	    return blocks3;
	default:
	    return blocks4;

	}
	*/
	
	return null;
    }

}
