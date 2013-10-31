package vivadaylight3.myrmecology.api.util;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.lib.Maths;
import vivadaylight3.myrmecology.common.lib.Nbt;

public class AntProperties {

    private static ArrayList<Property[]> propertyList = new ArrayList<Property[]>();

    public static final String LIFETIME_TOTAL_KEY = "lifetimeTotal";
    public static final String LIFETIME_COMPLETE_KEY = "lifeTimeComplete";
    public static final String FERTILITY_KEY = "fertility";
    public static final String MATURING_COMPLETE_KEY = "maturingComplete";
    public static final String WINGED_KEY = "winged";
    public static final String MATED_KEY = "mated";
    public static final String NOCTURNAL_KEY = "nocturnal";

    /**
     * Set an ant's properties
     * 
     * @param itemStack
     * @param mated
     * @param lifetimeComplete
     */
    public static void setProperties(ItemStack itemStack, boolean mated,
	    int lifetimeComplete) {

	Nbt.setTag(itemStack);
	Nbt.set(itemStack, LIFETIME_COMPLETE_KEY, lifetimeComplete);
	Nbt.set(itemStack, MATED_KEY, mated);

    }

    public static void setProperties(ItemStack itemStack, int maturingComplete) {

	Nbt.setTag(itemStack);
	Nbt.set(itemStack, MATURING_COMPLETE_KEY, maturingComplete);

    }

    public static int getLifetimeComplete(ItemStack itemStack) {

	Nbt.setTag(itemStack);

	return Nbt.getInt(itemStack, LIFETIME_COMPLETE_KEY);

    }

    public static void setLifetimeComplete(ItemStack item, int value) {

	Nbt.setTag(item);

	Nbt.set(item, LIFETIME_COMPLETE_KEY, value);

    }

    public static void setMated(ItemStack item, boolean value) {

	Nbt.setTag(item);

	Nbt.set(item, MATED_KEY, value);

    }

    public static boolean getMated(ItemStack itemStack) {

	Nbt.setTag(itemStack);

	return Nbt.getBoolean(itemStack, MATED_KEY);

    }

    public static void setMaturingTime(ItemStack itemStack, int value) {

	Nbt.setTag(itemStack);

	Nbt.set(itemStack, MATURING_COMPLETE_KEY, value);

    }

    // TODO
    public static int getMaturingTimeComplete(ItemStack itemStack) {

	Nbt.setTag(itemStack);

	return Nbt.getInt(itemStack, MATURING_COMPLETE_KEY);

    }

}
