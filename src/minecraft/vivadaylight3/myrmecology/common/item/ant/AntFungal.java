package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntFungal extends ItemAnt {

    public AntFungal(int par1) {
	super(par1);
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getSpeciesName() {

	return "Fungal Ant";

    }

    @Override
    public String getSpeciesSubName() {

	return "antFungal";

    }

    @Override
    public String getSpeciesBinomialName() {

	return "Atta Colombica";

    }

    @Override
    public int getLifetime() {

	return Time.getTicksFromMinutes(10);

    }

    @Override
    public int getMaturingTime() {

	return Time.getTicksFromMinutes(20);

    }

    @Override
    public int getFertility() {

	return 2;

    }

    @Override
    public boolean eatsSavoury() {

	return false;

    }

    @Override
    public boolean eatsLarvae() {

	return false;

    }

    @Override
    public boolean eatsMeat() {

	return false;

    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.swampland };

	return biomes;

    }

    @Override
    public boolean getNocturnal() {

	return false;

    }

}
