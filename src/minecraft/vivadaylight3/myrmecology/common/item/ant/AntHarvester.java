package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntHarvester extends ItemAnt {

    public AntHarvester() {
	super();
    }

	@Override
    public String getSpeciesName() {

	return "Harvester Ant";

    }

    @Override
    public String getSpeciesSubName() {

	return Reference.ANT_HARVESTER_NAME;

    }

    @Override
    public String getSpeciesBinomialName() {

	return "Pogonomyrmex occidentalis";

    }

    @Override
    public boolean isHillAnt() {

	return false;

    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.forest,
		BiomeGenBase.forestHills, BiomeGenBase.plains };

	return biomes;

    }

    @Override
    public int getFertility() {

	return 3;

    }

    @Override
    public boolean eatsSavoury() {

	return true;

    }

    @Override
    public boolean eatsMeat() {

	return true;

    }

    @Override
    public boolean eatsLarvae() {

	return true;

    }

    @Override
    public boolean eatsSweet() {

	return true;

    }

    @Override
    public int getMaturingTime() {

	return Time.getTicksFromMinutes(15);

    }

    @Override
    public int[] getColours() {

	return new int[] { 0x967c5e, 0xc7b299 };

    }

}
