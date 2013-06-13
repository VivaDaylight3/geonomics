package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.block.BlockAntHill;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class Variables
{
	
	private static List<Icon> iconList = new ArrayList<Icon>();
	
	private static List<String> antIconNameList = new ArrayList<String>();
	
	private static List<String> hillIconNameList = new ArrayList<String>();
	
	private static List<String> nameList = new ArrayList<String>();
	
	private static List<String> hillList = new ArrayList<String>();
	
	private static List<Integer> metaList = new ArrayList<Integer>();
		
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
	
	private static int[] metaArray = new int[Myrmecology.antNames.length];
	
	public static int[] antMeta(){
		
		int i = 0;
		
		for (int k = 0; k < Myrmecology.antNames.length; k++){
						
			metaArray[k] = i;
			
			i = i + Variables.getLastInt(Myrmecology.typeMeta) + 1;
			
		}
		
		return metaArray;
		
	}
	
	public static Icon[] iconsToArray(IconRegister iconRegister, String[] icons){
		
		for(int k = 0; k < icons.length; k++){
			
			System.out.println("Icon "+k+": "+icons[k]);
						
			iconList.add(iconRegister.registerIcon(icons[k]));
			
		}
		
		Icon[] iconArray = iconList.toArray(new Icon[0]);
		
		return iconArray;
		
	}
	
	public static String[] antNames(){
						
		for (int k = 0; k < Myrmecology.antNames.length; k++){
			
			for (int i = 0; i < Myrmecology.typeNames.length; i++){
				
				nameList.add(Myrmecology.antNames[k] + " " + Myrmecology.typeNames[i]);
				
			}
			
		}
		
		String[] result = nameList.toArray(new String[0]);
		
		return result;
		
	}
	
	public static String arrayToString(String[] array){
		
		return Arrays.toString(array);
		
	}
	
	public static String[] hillNames(){
		
		String[] name = Myrmecology.biomeNames;
		
		for (int k = 0; k < name.length; k++){
						
			hillList.add(name[k] + " " + BlockAntHill.NAME);
			
		}
		
		String[] result = hillList.toArray(new String[0]);
		
		return result;
		
	}
	
	public static String[] antIconNames(){
		
		for (int k = 0; k < Myrmecology.biomeSubNames.length; k++){
			
			for(int i = 0; i < Myrmecology.typeNames.length; i++){
			
				antIconNameList.add(Myrmecology.TEXTURE_PREFIX + Myrmecology.ANT_PATH + Myrmecology.ITEM_ANT_NAME + "_" + Myrmecology.biomeSubNames[k] + Myrmecology.typeNames[i]);
			}
				
		}
		
		String[] result = antIconNameList.toArray(new String[0]);
		
		return result;
		
	}
	
	public static String[] hillIconNames(){
		
		for (int k = 0; k < BlockAntHill.hillNames.length; k++){
			
			hillIconNameList.add(Myrmecology.TEXTURE_PREFIX + /*Myrmecology.HILL_PATH + */Myrmecology.BLOCK_ANTHILL_NAME + "_" + Myrmecology.biomeSubNames[k]);
			
		}
		
		String[] result = hillIconNameList.toArray(new String[0]);
		
		return result;
		
	}

}
