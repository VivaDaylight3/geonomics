package vivadaylight3.myrmecology.api;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.lib.Maths;
import vivadaylight3.myrmecology.common.lib.Nbt;

public class AntProperties {

    @Deprecated
    private static ArrayList<Property[]> propertyList = new ArrayList<Property[]>();

    public static final String LIFETIME_TOTAL_KEY = "lifetimeTotal";
    public static final String LIFETIME_COMPLETE_KEY = "lifeTimeComplete";
    public static final String FERTILITY_KEY = "fertility";
    public static final String MATURING_TIME_KEY = "maturingTime";
    public static final String WINGED_KEY = "winged";
    public static final String MATED_KEY = "mated";
    public static final String NOCTURNAL_KEY = "nocturnal";
/*
    private static int[] foodSweet = { Item.appleRed.itemID, Item.cake.itemID,
	    Item.appleGold.itemID, Item.sugar.itemID, Item.cookie.itemID,
	    Item.melon.itemID };

    private static int[] foodSavoury = { Item.bowlSoup.itemID,
	    Item.bread.itemID, Item.carrot.itemID, Item.potato.itemID,
	    Item.bakedPotato.itemID, Item.pumpkinPie.itemID };

    private static int[] foodMeat = { Item.beefCooked.itemID,
	    Item.beefRaw.itemID, Item.porkRaw.itemID, Item.porkCooked.itemID,
	    Item.fishRaw.itemID, Item.fishCooked.itemID,
	    Item.chickenRaw.itemID, Item.chickenCooked.itemID,
	    Item.rottenFlesh.itemID };

    public static int[] getFoodSweet() {

	return foodSweet;

    }

    public static int[] getFoodSavoury() {

	return foodSavoury;

    }

    public static int[] getFoodMeat() {

	return foodMeat;

    }
    */

    /**
     * Set an ant's properties
     * 
     * @param itemStack
     * @param fertility
     * @param maturingTime
     * @param winged
     * @param lifetime
     * @param lifetimeComplete
     * @param mated
     * @param nocturnal
     */
    public static void setProperties(ItemStack itemStack, boolean mated,
	    int lifetimeComplete) {

	Nbt.setTag(itemStack);
	Nbt.set(itemStack, LIFETIME_COMPLETE_KEY, lifetimeComplete);
	Nbt.set(itemStack, MATED_KEY, mated);

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

}
