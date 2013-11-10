package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vivadaylight3.myrmecology.api.item.ItemAnt;

public class Variables {

    public static ArrayList listToArrayList(List list) {

	ArrayList result = new ArrayList();

	for (int k = 0; k < list.size(); k++) {

	    result.add(list.get(k));

	}

	return result;

    }

    public static ItemAnt getAntFromItemStack(ItemStack item) {

	if (item.getItem() instanceof ItemAnt) {

	    return (ItemAnt) item.getItem();

	} else {

	    return null;

	}

    }

    public static int getLastInt(int[] array) {

	int a;

	a = array[array.length - 1];

	return a;

    }

    public static String getLastString(String[] array) {

	String a;

	a = array[array.length - 1];

	return a;

    }

    public static Icon[] iconsToArray(IconRegister iconRegister, String[] icons) {

	List<Icon> iconList = new ArrayList<Icon>();

	for (int k = 0; k < icons.length; k++) {

	    iconList.add(iconRegister.registerIcon(icons[k]));

	}

	Icon[] iconArray = iconList.toArray(new Icon[0]);

	return iconArray;

    }

    public static String arrayToString(String[] array) {

	return Arrays.toString(array);

    }

    public static String arrayToString(Object[] array) {

	return Arrays.toString(array);

    }

    public static String arrayToString(int[][] array) {

	return Arrays.toString(array);

    }

    public static String arrayToString(int[] array) {

	return Arrays.toString(array);

    }

    public static int getLastSetInt(int[] array) {

	for (int k = 0; k < array.length; k++) {

	    if (array[k] != 0 && !(array[k] > 0) && !(array[k] < 0)) {

		return array[k - 1];

	    }

	}

	return getLastInt(array);

    }

    public static int getLastSetIndex(int[][] combinations) {

	for (int k = 0; k < combinations.length; k++) {

	    if (combinations[k][0] != 0 && !(combinations[k][0] > 0)
		    && !(combinations[k][0] < 0)) {

		return k - 1;

	    }

	}

	return combinations.length - 1;

    }

    public static boolean arrayIndexIsInt(int[][] combinations, int index) {

	if (combinations[index][0] != 0 && combinations[index][1] != 0
		&& !(combinations[index][0] > 0)
		&& !(combinations[index][1] > 0)
		&& !(combinations[index][0] < 0)
		&& !(combinations[index][1] < 0)) {

	    return true;

	} else {

	    return false;

	}

    }

    public static boolean intIsSet(int[][] array, int index1, int index2) {

	if (array[index1][index2] != 0 && !(array[index1][index2] > 0)
		&& !(array[index1][index2] < 0)) {

	    return false;

	} else {

	    return true;

	}

    }

}
