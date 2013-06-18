package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;
import java.util.List;

import vivadaylight3.myrmecology.common.Myrmecology;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class Properties
{
	
	private static int[] lifeTimes = {getTicksFromMinutes(10), getTicksFromMinutes(10), getTicksFromMinutes(5), 
		getTicksFromMinutes(10), getTicksFromMinutes(20), getTicksFromMinutes(10), getTicksFromMinutes(20), 
		getTicksFromMinutes(15)};
	
	private boolean[] eatsSweet = {true, false, false, true, true, true, true, true};
	
	private boolean[] eatsSavoury = {false, true, false, true, true, true, false, false};
	
	private boolean[] eatsMeat = {true, false, true, false, true, false, true, false};
	
	private boolean[] eatsLarva = {false, false, true, false, true, false, false, false};
	
	private static List<String> dietList = new ArrayList<String>();
	
	Ants ants = new Ants();
	
	public int getAntMeta(String name){
		
		for (int k = 0; k < Ants.antMeta.length; k++){
			
			if(Ants.antNames[k] == name){
				
				return Ants.antMeta[k];
				
			}
			
		}
		
		return 0;
		
	}

	public static int getLifetime(ItemStack queen)
	{
		int meta = queen.getItemDamage();
		
		for(int k = 0; k < Ants.antMeta.length; k++){
			
			if(meta == k){
				
				return lifeTimes[k];
						
			}
			
		}
		
		return 1;
	}
	
	public String[] getDiet(ItemStack ant){
		
		int meta = ant.getItemDamage();
		
		if(eatsSweet[meta]){
			
			dietList.add("true");
			
		}else{
			
			dietList.add("false");
			
		}
		
		if(eatsSavoury[meta]){
			
			dietList.add("true");
			
		}else{
			
			dietList.add("false");
			
		}
		
		if(eatsMeat[meta]){
			
			dietList.add("true");
			
		}else{
			
			dietList.add("false");
			
		}
		
		if(eatsLarva[meta]){
			
			dietList.add("true");
			
		}else{
			
			dietList.add("false");
			
		}
		
		String[] dietArray = dietList.toArray(new String[0]);
		
		return dietArray;
				
	}
	
	public boolean eatsSweet(ItemStack ant){
		
		int meta = ant.getItemDamage();
		
		for(int k = 0; k < Ants.antMeta.length; k++){
			
			if(k == meta){
				
				return eatsSweet[k];
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean eatsSavoury(ItemStack ant){
		
		int meta = ant.getItemDamage();
		
		for(int k = 0; k < Ants.antMeta.length; k++){
			
			if(k == meta){
				
				return eatsSavoury[k];
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean eatsMeat(ItemStack ant){
		
		int meta = ant.getItemDamage();
		
		for(int k = 0; k < Ants.antMeta.length; k++){
			
			if(k == meta){
				
				return eatsMeat[k];
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean eatsLarva(ItemStack ant){
		
		int meta = ant.getItemDamage();
		
		for(int k = 0; k < Ants.antMeta.length; k++){
			
			if(k == meta){
				
				return eatsLarva[k];
				
			}
			
		}
		
		return false;
		
	}
	
	public static int getTicksFromMinutes(int minutes){
		
		int seconds = minutes * 60;
		
		int ticks = seconds * 20;
		
		return ticks;
		
	}

}
