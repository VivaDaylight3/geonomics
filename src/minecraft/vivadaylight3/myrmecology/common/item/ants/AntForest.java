package vivadaylight3.myrmecology.common.item.ants;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.item.ItemAnt;
import vivadaylight3.myrmecology.common.lib.IAnt;
import vivadaylight3.myrmecology.common.lib.Properties;
import vivadaylight3.myrmecology.common.lib.Register;

public class AntForest extends ItemAnt {
        
    public AntForest(int par1) {
	super(par1, Myrmecology.MOD_ID);
    }
    
    @Override
    public String getSpeciesName() {
	// TODO Auto-generated method stub
	return "Black Ant";
    }
    
    @Override
    public String getSpeciesSubName() {
	return "antForest";
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
    public int getFertilityChance() {
	// TODO Auto-generated method stub
	return 0;
    }
    
    @Override
    public int getLifetime() {
	// TODO Auto-generated method stub
	return 0;
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
	return false;
    }
    
    @Override
    public boolean eatsLarvae() {
	// TODO Auto-generated method stub
	return false;
    }
    
    @Override
    public void performBehaviour(World world, int x, int y, int z) {
	// TODO Auto-generated method stub
	
    }
    
    @Override
    public String getSpeciesBinomialName() {
	// TODO Auto-generated method stub
	return "Antus Forestus";
    }
    
    @Override
    public BiomeGenBase[] getAntBiomes() {
	
	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.extremeHills,
		BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest,
		BiomeGenBase.forestHills };
	
	return biomes;
    }
    
    @Override
    public String[] getTypeNames() {
	
	String[] names = new String[] { "Queen", "Drone", "Worker", "Larva" };
	
	return names;
    }
    
}
