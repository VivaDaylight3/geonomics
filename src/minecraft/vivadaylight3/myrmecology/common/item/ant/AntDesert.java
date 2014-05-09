package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntDesert extends ItemAnt {

    public AntDesert() {
	super();
    }

    @Override
    public String getSpeciesName() {
	// TODO Auto-generated method stub
	return "Pharoah Ant";
    }

    @Override
    public String getSpeciesSubName() {
	return Reference.ANT_DESERT_NAME;
    }

    @Override
    public boolean isHillAnt() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public int getFertility() {
	// TODO Auto-generated method stub
	return 2;
    }

    @Override
    public int getLifetime() {
	// TODO Auto-generated method stub
	return Time.getTicksFromMinutes(5);
    }

    @Override
    public boolean eatsSweet() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean eatsSavoury() {
	// TODO Auto-generated method stub
	return false;
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
    public void performBehaviour(World world, int x, int y, int z) {

    }

    @Override
    public String getSpeciesBinomialName() {
	// TODO Auto-generated method stub
	return "Antus Desertus";
    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.desert,
		BiomeGenBase.desertHills };

	return biomes;
    }

    @Override
    public int[] getColours() {

	return new int[] { 0x898000, 0xeada00 };

    }

}
