package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntCommon extends ItemAnt {

    public AntCommon() {
	super();
    }

    @Override
    public String getSpeciesName() {
	// TODO Auto-generated method stub
	return "Common Ant";
    }

    @Override
    public String getSpeciesSubName() {
	return Reference.ANT_COMMON_NAME;
    }

    @Override
    public boolean isHillAnt() {
	// TODO Auto-generated method stub
	return false;
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
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean eatsSavoury() {
	// TODO Auto-generated method stub
	return false;
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
	return "Antus Communus";
    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	return null;
    }

    @Override
    public int[] getColours() {

	return new int[] { 0x3f2a17, 0x684526 };

    }

}
