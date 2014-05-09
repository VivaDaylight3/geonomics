package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntOdourous;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntOdourous extends ItemAnt {

    public AntOdourous() {
	super();
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getSpeciesName() {

	return "Odourous Ant";

    }

    @Override
    public String getSpeciesSubName() {

	return Reference.ANT_ODOUROUS_NAME;

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
	return "Tapinoma Sessile";
    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	return null;
    }

    @Override
    public int[] getColours() {

	return new int[] { 0x8c5210, 0xf7941d };

    }

    @Override
    public Entity getNewEntity(World parWorld) {

	return new EntityAntOdourous(parWorld);

    }

}
