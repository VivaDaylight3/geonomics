package vivadaylight3.myrmecology.common.lib;

import net.minecraft.world.World;

public class Time {
    
    public static int getTicksFromMinutes(int minutes) {
	
	int seconds = minutes * 60;
	
	int ticks = seconds * 20;
	
	return ticks;
	
    }
    
    public static int getMinutesFromTicks(int ticks){
	
	int seconds = ticks / 20;
	
	int minutes = seconds / 60;
	
	return minutes;
	
    }
    
    public static String getWorldTimeString(World world){
	
	int time = (int) world.getWorldTime();
	
	if(time < 12500 && time > 0){
	    
	    return "day";
	    
	}else{
	    
	    return "night";
	    
	}
	
    }
    
}
