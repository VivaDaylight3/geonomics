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

public class AntHillStone extends BlockAntHill {

    public AntHillStone() {
	super();
	// TODO Auto-generated constructor stub
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antStone;
    }

    @Override
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Stone Ant Hill";
    }

    @Override
    public String getHillSubName() {
	return Reference.HILL_STONE_NAME;
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
    public boolean canGenerate(World world, int x, int y, int z) {

	return true;

    }

    @Override
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 2;
    }

}
