package vivadaylight3.myrmecology.common.block.anthill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.BlockAntHill;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Register;

public class AntHillSwamp extends BlockAntHill {

    public AntHillSwamp(int par1, Material material) {
	super(par1, material);
	// TODO Auto-generated constructor stub
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antSwamp;
    }

    @Override
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Swamp Ant Hill";
    }

    @Override
    public String getHillSubName() {
	return "antHillSwamp";
    }

    @Override
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 2;
    }

    @Override
    public BiomeGenBase[] getHillBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.swampland };

	return biomes;

    }

    @Override
    public boolean usesNativeGeneration() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public int[] getRequiredTouchingBlocks() {

	int[] blocks = new int[] { Block.grass.blockID };

	return blocks;
    }

}
