package vivadaylight3.myrmecology.common.block.anthill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.block.BlockAntHill;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.util.AntDamageSource;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Environment;

public class AntHillJungle extends BlockAntHill {

    public AntHillJungle() {
	super(Material.ground);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
	    int par4, Entity par5Entity) {

	par5Entity.attackEntityFrom(new AntDamageSource(Reference.DEATH_MESSAGE
		+ Register.antJungle.getSpeciesName() + "s"), 1.0F);

    }

    @Override
    public ItemAnt getAnt() {
	return Register.antJungle;
    }

    @Override
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Jungle Ant Hill";
    }

    @Override
    public String getHillSubName() {
	return "antHillJungle";
    }

    @Override
    public BiomeGenBase[] getHillBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.jungle,
		BiomeGenBase.jungleHills };

	return biomes;

    }

    @Override
    public boolean usesNativeGeneration() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 2;
    }

}
