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

public class AntHillSwamp extends BlockAntHill {

    public AntHillSwamp() {
	super();
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
	return Reference.HILL_SWAMP_NAME;
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

}
