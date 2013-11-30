package vivadaylight3.myrmecology.common.lib;

import java.util.Random;

public class Maths {

    public static int getChance(int max) {

	Random num = new Random();

	int i = num.nextInt(max);

	return i;

    }

    public static double randDouble(int max, int places) {

	Random rand = new Random();
	int base = rand.nextInt(max);
	double decimal = rand.nextInt(9) / Math.pow(10, places);
	return base + decimal;

    }

    public static int incrementTo(int numToChange, int increment, int to,
	    int setTo) {

	if (numToChange == to) {

	    return setTo;

	} else {

	    return numToChange += increment;

	}

    }

    public static boolean chanceOf(int chance, int max) {

	Random num = new Random();

	int i = num.nextInt(max);

	if (i <= chance) {

	    return true;

	}

	return false;

    }

    public static int intToPowerOf(int base, int exponent) {

	int newInt = base;

	for (int k = 1; k < exponent; k++) {

	    newInt = newInt * base;

	}

	return newInt;

    }

}
