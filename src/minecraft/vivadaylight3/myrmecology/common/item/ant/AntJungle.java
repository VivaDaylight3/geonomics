package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntJungle extends ItemAnt {

    public AntJungle() {
	super();
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getSpeciesName() {
	// TODO Auto-generated method stub
	return "Red Ant";
    }

    @Override
    public String getSpeciesSubName() {
	return Reference.ANT_JUNGLE_NAME;
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
	return Time.getTicksFromMinutes(10);
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
	return false;
    }

    @Override
    public boolean eatsLarvae() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void performBehaviour(World world, int x, int y, int z) {

    }

    @Override
    public String getSpeciesBinomialName() {
	// TODO Auto-generated method stub
	return "Formica Rufa";
    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.jungle,
		BiomeGenBase.jungleHills };

	return biomes;
    }

    @Override
    public boolean getWinged() {

	return true;

    }

    @Override
    public boolean getNocturnal() {

	return false;

    }

    @Override
    public int[] getColours() {

	return new int[] { 0x3d0000, 0x790000 };

    }

}
