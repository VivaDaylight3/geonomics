package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntCarpenter extends ItemAnt {

    public AntCarpenter(int par1) {
	super(par1);
    }

    @Override
    public String getSpeciesName() {

	return "Carpenter Ant";

    }

    @Override
    public String getSpeciesSubName() {

	return "antCarpenter";

    }

    @Override
    public String getSpeciesBinomialName() {

	return "Camponotus atriceps";

    }

    @Override
    public boolean eatsSweet() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean eatsSavoury() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean eatsMeat() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean eatsLarvae() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public int getFertility() {

	return 2;

    }

    @Override
    public int getMaturingTime() {

	return Time.getTicksFromMinutes(10);

    }

    @Override
    public boolean getWinged() {

	return false;

    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.forest,
		BiomeGenBase.forestHills };

	return biomes;

    }

    @Override
    public int getLifetime() {

	return Time.getTicksFromMinutes(10);

    }

    @Override
    public boolean getNocturnal() {

	return false;

    }

}
