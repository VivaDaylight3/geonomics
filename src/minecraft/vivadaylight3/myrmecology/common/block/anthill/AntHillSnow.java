package vivadaylight3.myrmecology.common.block.anthill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.BlockAntHill;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Environment;

public class AntHillSnow extends BlockAntHill {

    public AntHillSnow(int par1, Material material) {
	super(par1, material);
	// TODO Auto-generated constructor stub
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antSnow;
    }

    @Override
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Snow Ant Hill";
    }

    @Override
    public String getHillSubName() {
	return Reference.HILL_SNOW_NAME;
    }

    @Override
    public BiomeGenBase[] getHillBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.taiga,
		BiomeGenBase.taigaHills, BiomeGenBase.iceMountains,
		BiomeGenBase.icePlains };

	return biomes;

    }

    @Override
    public boolean usesNativeGeneration() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean canGenerate(World world, int x, int y, int z) {
	
	System.out.println("antHillSnow");

	int radius = 1;

	int[] blocks = new int[radius];

	blocks = Environment.getBlocksFrom("y", radius, world, x, y, z);

	if (blocks[0] != Block.ice.blockID && blocks[0] != Block.waterStill.blockID) {
	    
	    System.out.println(this.getHillName()+" has a valid block underneath");

	    for(int k = 0; k < this.getHillBiomes().length; k++){
		
		System.out.println(this.getHillName()+" is at a valid biome");
		
		if(world.getBiomeGenForCoords(x, z) == this.getHillBiomes()[k]){
		    
		    System.out.println("Valid biome");
		    
		    return true;
		    
		}
		
	    }

	}

	return false;

    }

    @Override
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 2;
    }

}
