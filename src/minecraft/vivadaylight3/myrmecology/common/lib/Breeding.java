package vivadaylight3.myrmecology.common.lib;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.Myrmecology;

public class Breeding
{
	
	private static Map breedingList = new HashMap();
	static Ants ants = new Ants();
	private static int[][] combinations = new int[ants.getNumAntsOfType(ants.getQueenMeta())][2];
		/*
	public static void register(){
		
		int[] allBasicDrones = ants.getAllBasicAntsOfType(ants.getDroneMeta());
		int[] allBasicQueens = ants.getAllBasicAntsOfType(ants.getQueenMeta());
		System.out.println("Drones: "+Variables.arrayToString(allBasicDrones));
		System.out.println("Queens: "+Variables.arrayToString(allBasicQueens));
		
		for(int k = 0; k < allBasicQueens.length; k++){
			
			for(int i = 0; i < allBasicDrones.length; i++){
				
				ItemStack common = new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getCommonAntMeta());
								
				addBreeding(allBasicQueens[k], allBasicDrones[i], common);
				System.out.println("Queen: "+allBasicQueens[k]);
				System.out.println("Drone: "+allBasicDrones[i]);
				System.out.println("Common: "+common);
				
			}
			
		}
		
		ItemStack result = new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Harvester"));
		addBreeding(ants.getAntMetaFromString("Common"), ants.getAntMetaFromString("Black"), result);
		addBreeding(ants.getAntMetaFromString("Common"), ants.getAntMetaFromString("Field"), result);
				
	}
	
	private static int newQueenDroneCombination(int queen, int drone)
	{
		
		int a = 0;
		
		if(!Variables.arrayIndexIsInt(combinations, a)){
			
			combinations[a][0] = queen;
			combinations[a][1] = drone;
						
		}else{
			
			a = Variables.getLastSetIndex(combinations) + 1;
			
			combinations[a][0] = queen;
			combinations[a][1] = drone;
			
		}
		
		return a;
	}

	public static void addBreeding(int queen, int drone, ItemStack result){
		
		int a = newQueenDroneCombination(queen, drone);
		
		getBreedingList().put(Integer.valueOf(a), result);
		
		System.out.println("Added new breeding option: "+combinations[a][0]+", "+combinations[a][1]+"-->"+result);
		
	}
	*/
	
	public static Map getBreedingList(){
		
		return breedingList;
		
	}
	
	public int getQueen(int a){
		
		return combinations[a][0];
		
	}
	
	public int getDrone(int a){
		
		return combinations[a][1];
		
	}
		
	public boolean antsCanBreed(ItemStack queen, ItemStack drone){
		
		if(ants.antIsType(queen, ants.getQueenMeta()) && ants.antIsType(drone, ants.getDroneMeta())){
			
			int queenMeta = queen.getItemDamage();
			int droneMeta = drone.getItemDamage();
			
			if(getBreedingResult(queenMeta, droneMeta) != null || getBreedingResult(droneMeta, queenMeta) != null){
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	/*
	public ItemStack getBreedingResult(int queen, int drone){
		
		if(haveBreedingResult(queen, drone)){
		
			int[][] comb = combinations;
		
			for(int k = 0; k < comb.length; k++){
							
				if(comb[k][0] == queen && comb[k][1] == drone){
				
					return (ItemStack) getBreedingList().get(k);
									
				}
			
			}
		
		}
		
		return null;
		
	}
	public boolean haveBreedingResult(int queen, int drone){
		
		//int[][] comb = combinations;
		
		//System.out.println("Comb is: "+Variables.arrayToString(comb));
		
		for(int k = 0; k < combinations.length; k++){
		
			System.out.println("Comb["+k+"][0] is: "+combinations[k][0]);
			System.out.println("Comb["+k+"][1] is: "+combinations[k][1]);
							
			if(combinations[k][0] == queen && combinations[k][1] == drone){
				
				return true;
			}
			
		}
		
		return false;
		
	}
	*/
	
	public ItemStack getBreedingResult(int ant1, int ant2){
		
		for(int k = 0; k < ants.getAllBasicAntsOfType(ant1).length; k++){
			
			for(int i = 0; i < ants.getAllBasicAntsOfType(ant2).length; i++){
				
				if(ant1 == ants.getAllBasicAntsOfType(ant1)[k] && ant2 == ants.getAllBasicAntsOfType(ant2)[k]){
					
					System.out.println("Common: "+ants.getAntMetaFromString("Common"));
					return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Common"));
					
				}
				
			}
						
		}
		
		if(ant1 == ants.getAntMetaFromString("Common") && (ant2 == ants.getAntMetaFromString("Black") || ant2 == ants.getAntMetaFromString("Field"))){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Harvester"));
			
		}else if(ant1 == ants.getAntMetaFromString("Harvester") && ant2 == ants.getAntMetaFromString("Black")){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Carpenter"));
			
		}else if(ant1 == ants.getAntMetaFromString("Harvester") && ant2 == ants.getAntMetaFromString("Field")){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Mound"));
			
		}else if(ant1 == ants.getAntMetaFromString("Common") && (ant2 == ants.getAntMetaFromString("Red") || ant2 == ants.getAntMetaFromString("Desert"))){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Barbaric"));
			
		}else if(ant1 == ants.getAntMetaFromString("Barbaric") && ant2 == ants.getAntMetaFromString("Desert")){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Odorous"));
			
		}else if(ant1 == ants.getAntMetaFromString("Barbaric") && ant2 == ants.getAntMetaFromString("Red")){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Hostile"));
			
		}else if(ant1 == ants.getAntMetaFromString("Common") && (ant2 == ants.getAntMetaFromString("Hibernus") || ant2 == ants.getAntMetaFromString("Amber"))){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Plentiful"));
			
		}else if(ant1 == ants.getAntMetaFromString("Plentiful") && ant2 == ants.getAntMetaFromString("Amber")){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Dredger"));
			
		}else if(ant1 == ants.getAntMetaFromString("Plentiful") && ant2 == ants.getAntMetaFromString("Hibernus")){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Scavenger"));
			
		}else if(ant1 == ants.getAntMetaFromString("Common") && (ant2 == ants.getAntMetaFromString("Argentine") || ant2 == ants.getAntMetaFromString("Hillside"))){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Cultivator"));
			
		}else if(ant1 == ants.getAntMetaFromString("Cultivator") && ant2 == ants.getAntMetaFromString("Hillside")){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Sprouter"));
			
		}else if(ant1 == ants.getAntMetaFromString("Cultivator") && ant2 == ants.getAntMetaFromString("Argentine")){
			
			return new ItemStack(Myrmecology.itemAnt.itemID, 1, ants.getAntMetaFromString("Fungal"));
			
		}else{
			
			return null;
			
		}
		
	}

	
}
