package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.block.BlockAntHill;

public class Variables
{
	
	private static List<Icon> iconList = new ArrayList<Icon>();
	
	private static List<String> antIconNameList = new ArrayList<String>();
	
	private static List<String> hillIconNameList = new ArrayList<String>();
	
	private static List<String> nameList = new ArrayList<String>();
	
	private static List<String> hillList = new ArrayList<String>();
	
	private static List<Integer> metaList = new ArrayList<Integer>();
	
	//private static int[] metaArray = new int[Ants.antNames.length];
		
	public static int getLastInt(int[] array){
		
		int a;
		
		a = array[array.length - 1];
		
		return a;
		
	}
	
	public static String getLastString(String[] array){
		
		String a;
		
		a = array[array.length - 1];
		
		return a;
		
	}
		
	public static int[] antMeta(){
		
		int[] array = new int[Ants.antNames.length];
		
		int i = 0;
		
		for (int k = 0; k < Ants.antNames.length; k++){
						
			array[k] = i;
			
			i = i + Variables.getLastInt(Ants.typeMeta) + 1;
			
		}
		
		return array;
		
	}
	
	public static Icon[] iconsToArray(IconRegister iconRegister, String[] icons){
		
		for(int k = 0; k < icons.length; k++){
									
			iconList.add(iconRegister.registerIcon(icons[k]));
			
		}
		
		Icon[] iconArray = iconList.toArray(new Icon[0]);
		
		return iconArray;
		
	}
	
	public static String[] antNames(){
						
		for (int k = 0; k < Ants.antNames.length; k++){
			
			for (int i = 0; i < Ants.typeNames.length; i++){
				
				nameList.add(Ants.antNames[k] + " Ant " + Ants.typeNames[i]);
				
			}
			
		}
		
		String[] result = nameList.toArray(new String[0]);
		
		return result;
		
	}
	
	public static String arrayToString(String[] array){
		
		return Arrays.toString(array);
		
	}
	
	public static String arrayToString(int[][] array){
		
		return Arrays.toString(array);
		
	}
	
	public static String arrayToString(int[] array){
		
		return Arrays.toString(array);
		
	}
	
	public static String[] hillNames(){
		
		String[] name = Ants.biomeNames;
		
		for (int k = 0; k < name.length; k++){
						
			hillList.add(name[k] + " " + BlockAntHill.NAME);
			
		}
		
		String[] result = hillList.toArray(new String[0]);
		
		return result;
		
	}
	
	public static String[] antIconNames(){
		
		for (int k = 0; k < Ants.biomeSubNames.length; k++){
			
			for(int i = 0; i < Ants.typeNames.length; i++){
			
				antIconNameList.add(Myrmecology.TEXTURE_PREFIX + Myrmecology.ANT_PATH + Myrmecology.ITEM_ANT_NAME + "_" + Ants.biomeSubNames[k] + Ants.typeNames[i]);
			}
				
		}
		
		String[] result = antIconNameList.toArray(new String[0]);
		
		return result;
		
	}
	
	public static String[] hillIconNames(){
		
		for (int k = 0; k < BlockAntHill.hillNames.length; k++){
			
			hillIconNameList.add(Myrmecology.TEXTURE_PREFIX + /*Myrmecology.HILL_PATH + */Myrmecology.BLOCK_ANTHILL_NAME + "_" + Ants.biomeSubNames[k]);
			
		}
		
		String[] result = hillIconNameList.toArray(new String[0]);
		
		return result;
		
	}
	
	public static int getLastSetInt(int[] array){
		
		for(int k = 0; k < array.length; k++){
			
			if (array[k] != 0 && !(array[k] > 0) && !(array[k] < 0)){
				
				return array[k - 1];
				
			}
			
		}
		
		return getLastInt(array);
		
	}
	
	public static int getLastSetIndex(int[][] combinations){
		
		for(int k = 0; k < combinations.length; k++){
			
			if (combinations[k][0] != 0 && !(combinations[k][0] > 0) && !(combinations[k][0] < 0)){
				
				return k-1;
				
			}
			
		}
		
		return combinations.length - 1;
		
	}
	
	public static boolean arrayIndexIsInt(int[][] combinations, int index){
		
		if(combinations[index][0] != 0 && combinations[index][1] != 0 && !(combinations[index][0] > 0) && !(combinations[index][1] > 0) && !(combinations[index][0] < 0) && !(combinations[index][1] < 0)){
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
	public static boolean intIsSet(int[][] array, int index1, int index2){
		
		if (array[index1][index2] != 0 && !(array[index1][index2] > 0) && !(array[index1][index2] < 0)){
			
			return false;
			
		}else{
			
			return true;
			
		}
		
	}

}
