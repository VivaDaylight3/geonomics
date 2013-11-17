package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntMound;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntMound extends ItemAnt {

    public AntMound(int par1) {
	super(par1);
    }

    @Override
    public String getSpeciesName() {

	return "Mound Ant";

    }

    @Override
    public Entity getNewEntity(World world) {

	return new EntityAntMound(world);

    }

    @Override
    public String getSpeciesSubName() {

	return Reference.ANT_MOUND_NAME;

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
	return Time.getTicksFromMinutes(20);
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
    public void performBehaviour(World world, int x, int y, int z) {

    }

    @Override
    public String getSpeciesBinomialName() {
	// TODO Auto-generated method stub
	return "Solenopsis Invicta";
    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.plains,
		BiomeGenBase.forest, BiomeGenBase.forestHills };

	return biomes;
    }

    @Override
    public int[] getColours() {

	return new int[] { 0x332f00, 0x827b00 };

    }

}
