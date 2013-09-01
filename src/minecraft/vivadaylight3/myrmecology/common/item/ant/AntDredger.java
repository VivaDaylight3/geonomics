package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntDredger extends ItemAnt {

    public AntDredger(int par1) {
	super(par1);
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getSpeciesName() {

	return "Dredger Ant";

    }

    @Override
    public String getSpeciesSubName() {

	return "antDredger";

    }

    @Override
    public String getSpeciesBinomialName() {

	return "Formicadae Lapideas";

    }

    @Override
    public int getLifetime() {

	return Time.getTicksFromMinutes(15);

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

	return null;

    }

    @Override
    public boolean getNocturnal() {

	return true;

    }

}
