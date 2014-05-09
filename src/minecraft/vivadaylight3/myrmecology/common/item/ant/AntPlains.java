package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntPlains extends ItemAnt {

    public AntPlains() {
	super();
    }

    @Override
    public String getSpeciesName() {
	// TODO Auto-generated method stub
	return "Field Ant";
    }

    @Override
    public String getSpeciesSubName() {
	return Reference.ANT_PLAINS_NAME;
    }

    @Override
    public boolean isHillAnt() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public int getFertility() {
	// TODO Auto-generated method stub
	return 3;
    }

    @Override
    public int getLifetime() {
	// TODO Auto-generated method stub
	return Time.getTicksFromMinutes(20);
    }

    @Override
    public boolean eatsSweet() {
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
    public void performBehaviour(World world, int x, int y, int z) {

    }

    @Override
    public String getSpeciesBinomialName() {
	// TODO Auto-generated method stub
	return "Antus Fieldia";
    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.extremeHills,
		BiomeGenBase.extremeHillsEdge, BiomeGenBase.plains };

	return biomes;
    }

    @Override
    public boolean getWinged() {

	return false;

    }

    @Override
    public boolean getNocturnal() {

	return true;

    }

    @Override
    public int[] getColours() {

	return new int[] { 0x162308, 0x406618 };

    }

}
