package vivadaylight3.myrmecology.api;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Nbt;

public class AntopediaProperties {
    
    public static void initiateAntopedia(ItemStack itemStack){
	
	Nbt.setTag(itemStack);
		
	for(int k = 0; k < Register.getAntList().toArray().length; k++){
	    
	    Nbt.set(itemStack, ((ItemAnt) Register.getAntList().toArray()[k]).getSpeciesSubName(), false);
	    
	}
	
    }
    
    public static void addAntToAntopedia(ItemStack itemstack, ItemAnt ant){
	
	Nbt.set(itemstack, ant.getSpeciesSubName(), true);
		
    }
    
    public static ArrayList<ItemAnt> getAntopediaAnts(ItemStack itemStack){
	
	ArrayList<ItemAnt> result = new ArrayList<ItemAnt>();
	
	for(int k = 0; k < Register.getAntList().toArray().length; k++){
	    
	    if(Nbt.getBoolean(itemStack, ((ItemAnt) Register.getAntList().toArray()[k]).getSpeciesSubName())){
	    
		result.add((ItemAnt) Register.getAntList().toArray()[k]);
	    
	    }
	    	    
	}
	
	return result;
	
    }

}
