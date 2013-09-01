package vivadaylight3.myrmecology.common.lib;

import net.minecraft.world.World;

public class Time {

    public static final int TIME_NIGHT = 12500;
    public static final int TIME_DAY = 0;

    public static int getTicksFromMinutes(int minutes) {

	int seconds = minutes * 60;

	int ticks = seconds * 20;

	return ticks;

    }

    public static int getMinutesFromTicks(int ticks) {

	int seconds = ticks / 20;

	int minutes = seconds / 60;

	return minutes;

    }

    public static String toString(int time) {

	if (time < 12500) {

	    return "day";

	} else {

	    return "night";

	}

    }

    public static int getWorldTime(World world) {

	return (int) world.getWorldTime();

    }

}
