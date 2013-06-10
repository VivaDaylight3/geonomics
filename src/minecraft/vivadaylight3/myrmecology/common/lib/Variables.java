package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class Variables
{
	
	private static List<Icon> iconList = new ArrayList<Icon>();
	
	private static List<String> nameList = new ArrayList<String>();
	
	public static int getLastInt(int[] array){
		
		int a;
		
		a = array[array.length - 1];
		
		return a;
		
	}
	
	public String getLastString(String[] array){
		
		String a;
		
		a = array[array.length - 1];
		
		return a;
		
	}
	
	public static Icon[] iconsToArray(IconRegister iconRegister, String[] icons){
		
		for(int k = 0; k < icons.length; k++){
			
			System.out.println("Icon "+k+": "+icons[k]);
						
			iconList.add(iconRegister.registerIcon(icons[k]));
			
		}
		
		Icon[] iconArray = iconList.toArray(new Icon[0]);
		
		return iconArray;
		
	}
	
	public static String[] antNamesAndTypesToArray(String[] names, String [] types){
						
		for (int k = 0; k < names.length; k++){
			
			for (int i = 0; i < types.length; i++){
				
				nameList.add(names[k] + " " + types[i]);
				
			}
			
		}
		
		String[] result = nameList.toArray(new String[0]);
		
		return result;
		
	}
	
	public String arrayToString(String[] array){
		
		return Arrays.toString(array);
		
	}

}
