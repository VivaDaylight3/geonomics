package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntCarpenter;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntCarpenter extends ItemAnt {

    public AntCarpenter() {
	super();
    }

    @Override
    public String getSpeciesName() {

	return "Carpenter Ant";

    }

    @Override
    public String getSpeciesSubName() {

	return Reference.ANT_CARPENTER_NAME;

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

    @Override
    public EntityAnt getNewEntity(World world) {

	return new EntityAntCarpenter(world);

    }

    @Override
    public int[] getColours() {

	return new int[] { 0x253810, 0x4b7021 };

    }

}
