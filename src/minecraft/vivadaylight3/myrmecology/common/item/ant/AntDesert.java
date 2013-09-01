package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntDesert extends ItemAnt {

    public AntDesert(int par1) {
	super(par1);
    }

    @Override
    public String getSpeciesName() {
	// TODO Auto-generated method stub
	return "Pharoah Ant";
    }

    @Override
    public String getSpeciesSubName() {
	return "antDesert";
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
    public String[] getTypeNames() {

	String[] names = new String[] { "Queen", "Drone", "Worker", "Larva" };

	return Register.standardTypeNames;
    }

}
