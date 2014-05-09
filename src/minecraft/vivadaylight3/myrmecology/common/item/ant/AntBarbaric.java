package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntBarbaric extends ItemAnt {

    public AntBarbaric() {
	super();
    }

    @Override
    public String getSpeciesName() {

	return "Barbaric Ant";

    }

    @Override
    public String getSpeciesSubName() {

	return Reference.ANT_BARBARIC_NAME;

    }

    @Override
    public String getSpeciesBinomialName() {

	return "Eciton burchellii";

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
	return true;
    }

    @Override
    public boolean eatsLarvae() {
	// TODO Auto-generated method stub
	return true;
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

	return true;

    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.desert,
		BiomeGenBase.desertHills, BiomeGenBase.jungle,
		BiomeGenBase.jungleHills };

	return biomes;

    }

    @Override
    public int getLifetime() {

	return Time.getTicksFromMinutes(10);

    }

    @Override
    public boolean getNocturnal() {

	return true;

    }

    @Override
    public Entity getNewEntity(World parWorld) {

	return null;

    }

    @Override
    public int[] getColours() {

	return new int[] { 0x382000, 0x6d3f00 };

    }

}
