package vivadaylight3.myrmecology.common.block.anthill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.BlockAntHill;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;

public class AntHillStone extends BlockAntHill {

    public AntHillStone(int par1, Material material) {
	super(par1, material);
	// TODO Auto-generated constructor stub
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antDesert;
    }

    @Override
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Stone Ant Hill";
    }

    @Override
    public String getHillSubName() {
	return "antHillStone";
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
    public boolean isUnderground() {

	return true;

    }

    @Override
    public int[] getRequiredTouchingBlocks() {

	return null;
    }

    @Override
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 2;
    }

}
