package vivadaylight3.myrmecology.common.lib;

import java.util.Random;

public class Maths
{

	public static int getChance(int max){
		
		Random num = new Random();
		
		int i = num.nextInt(max);
		
		return i;
		
	}
	
}
