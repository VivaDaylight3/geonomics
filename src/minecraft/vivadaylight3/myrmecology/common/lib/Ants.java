package vivadaylight3.myrmecology.common.lib;

import net.minecraft.item.ItemStack;

public class Ants
{
	/* When adding another basic ant, add one (+1) to the antMeta index in getMaxBasicMeta() and make sure they come before
	 * common ant and all non-basic (hill) ants.
	 */
	 //Ant names must also be changed in the Breeding.register() method
	public static final String[] antNames = {"Black","Hillside","Desert","Argentine","Field", 
		"Red","Hibernus","Amber","Common","Harvester","Carpenter","Mound","Barbaric","Odorous", 
		"Hostile","Plentiful","Dredger","Scavenger","Cultivator","Sprouter","Fungal"};
	
	public static final String[] typeNames = {"Queen", "Drone", "Worker", "Larva"};
		
	public static final String[] biomeSubNames = {"forest", "hills", "desert", "swamp", "plains", "jungle", "snow", 
		"rock"};
	
	public static final String[] biomeNames = {"Forest", "Hillside", "Desert", "Swamp", "Flatland", "Jungle", "Snowy", 
		"Amber"};
	
	public static final int[] typeMeta = {0, 1, 2, 3};
	
	public static final int[] antMeta = Variables.antMeta(); /* {0, 4, 8, 12, 16, 20, 24, 28, 32} */
				
	public static int getQueenMeta(){
		
		return 0;
		
	}
	
	public static int getDroneMeta(){
		
		return 1;
		
	}
	
	public boolean antIsType(ItemStack ant, int type){
		
		for(int k = 0; k < antMeta.length; k++){
			
			if(ant.getItemDamage() == antMeta[k] + typeMeta[type]){
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public int[] getAllAntsOfType(int type){
		
		int[] array = new int[antMeta.length];
		
		for(int k = 0; k < antMeta.length; k++){
			
			int i = antMeta[k] + type;
			
			array[k] = i;
			
		}
		
		return array;
		
	}
	
	public int[] getAllBasicAntsOfType(int type){
		
		int[] array = new int[antMeta.length];
		
		for(int k = 0; k < antMeta.length; k++){
			
			int i = antMeta[k] + type;
			
			if(i < getMaxBasicMeta()){
				
				array[k] = i;
				
			}
						
		}
		
		return array;
		
	}

	private int getMaxBasicMeta()
	{
		return antMeta[7] + Variables.getLastInt(typeMeta);
	}

	public int getCommonAntMeta()
	{
		return getMaxBasicMeta() + 1;
	}

	public int getNumAntsOfType(int type)
	{
		int total = 0;
		
		for(int k = 0; k < getAllAntsOfType(type).length; k++){
			
			total++;
			
		}
		
		return total;
	}
	
	public int getAntMetaFromString(String ant){
		
		for(int k = 0; k < antNames.length; k++){
			
			System.out.println("k = "+k);
			
			if(antNames[k] == ant){
				
				return antMeta[k];
				
			}
			
		}
		
		return 0;
		
	}

}
