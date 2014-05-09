package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntFungal;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntFungal extends ItemAnt {

    public AntFungal() {
	super();
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getSpeciesName() {

	return "Fungal Ant";

    }

    @Override
    public String getSpeciesSubName() {

	return Reference.ANT_FUNGAL_NAME;

    }

    @Override
    public String getSpeciesBinomialName() {

	return "Atta Colombica";

    }

    @Override
    public int getLifetime() {

	return Time.getTicksFromMinutes(10);

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
    public Entity getNewEntity(World world) {

	return new EntityAntFungal(world);
    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.swampland };

	return biomes;

    }

    @Override
    public boolean getNocturnal() {

	return false;

    }

    @Override
    public int[] getColours() {

	return new int[] { 0x680026, 0x9e0039 };

    }

}
