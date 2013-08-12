package vivadaylight3.myrmecology.common.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Properties {
    
    public static final String LIFETIME_TOTAL_KEY = "lifetimeTotal";
    public static final String LIFETIME_COMPLETE_KEY = "lifeTimeComplete";
    public static final String FERTILITY_KEY = "fertility";
    public static final String MATURING_TIME_KEY = "maturingTime";
    public static final String WINGED_KEY = "winged";
    public static final String MATED_KEY = "mated";
    public static final String NOCTURNAL_KEY = "nocturnal";
    
    private static int[] foodSweet = { Item.appleRed.itemID, Item.cake.itemID,
	    Item.appleGold.itemID, Item.sugar.itemID, Item.cookie.itemID,
	    Item.melon.itemID, };
    
    private static int[] foodSavoury = { Item.bowlSoup.itemID,
	    Item.bread.itemID, Item.carrot.itemID, Item.potato.itemID,
	    Item.bakedPotato.itemID, Item.pumpkinPie.itemID };
    
    private static int[] foodMeat = { Item.beefCooked.itemID,
	    Item.beefRaw.itemID, Item.porkRaw.itemID, Item.porkCooked.itemID,
	    Item.fishRaw.itemID, Item.fishCooked.itemID,
	    Item.chickenRaw.itemID, Item.chickenCooked.itemID,
	    Item.rottenFlesh.itemID, };
    
    public static int[] getFoodSweet(){
	
	return foodSweet;
	
    }
    
    public static int[] getFoodSavoury(){
	
	return foodSavoury;
	
    }
    
    public static int[] getFoodMeat(){
	
	return foodMeat;
	
    }
    
    public static int calculateFertility(ItemStack item1, ItemStack item2){
	
	int result = (getFertility(item1) + getFertility(item2)) / 2;
	
	return result;
	
    }
    
    public int calculateMaturingTime(ItemStack item1, ItemStack item2){
	
	int result = (getMaturingTime(item1) + getMaturingTime(item2)) / 2;
	
	return result;
	
    }
    
    public boolean calculateWinged(ItemStack item1, ItemStack item2){
	
	if(getWinged(item1) || getWinged(item2)){
	    
	    if(Maths.chanceOf(1, 2)){
	    
		return true;
	    
	    }
	    
	    return false;
	    
	}
	
	return false;
	
    }
    
    public int calculateLifetime(ItemStack item1, ItemStack item2){
	
	int result = (getLifetime(item1) + getLifetime(item2)) / 2;
	
	return result;
	
    }
    
    public boolean calculateNocturnal(ItemStack item1, ItemStack item2){
	
	if(getNocturnal(item1) || getNocturnal(item2)){
	    
	    if(Maths.chanceOf(1, 2)){
	    
		return true;
	    
	    }
	    
	    return false;
	    
	}
	
	return false;
	
    }
    
    /**
     * Set an ant's properties
     * @param itemStack
     * @param fertility
     * @param maturingTime
     * @param winged
     * @param lifetime
     * @param lifetimeComplete
     * @param mated
     * @param nocturnal
     */
    public static void setProperties(ItemStack itemStack, int fertility, int maturingTime, boolean winged, 
	    int lifetime, int lifetimeComplete, boolean mated, boolean nocturnal){
	
	Nbt.setTag(itemStack);
	
	Nbt.set(itemStack, FERTILITY_KEY, fertility);
	Nbt.set(itemStack, MATURING_TIME_KEY, maturingTime);
	Nbt.set(itemStack, WINGED_KEY, winged);
	Nbt.set(itemStack, LIFETIME_COMPLETE_KEY, lifetimeComplete);
	Nbt.set(itemStack, MATED_KEY, mated);
	Nbt.set(itemStack, LIFETIME_TOTAL_KEY, lifetime);
	Nbt.set(itemStack, NOCTURNAL_KEY, nocturnal);
	
    }
    
    public static int getFertility(ItemStack itemStack){
	
	Nbt.setTag(itemStack);
	
	return Nbt.getInt(itemStack, FERTILITY_KEY);
	
    }
    
    public int getMaturingTime(ItemStack itemStack){
	
	Nbt.setTag(itemStack);
	
	return Nbt.getInt(itemStack, MATURING_TIME_KEY);
	
    }
    
    public static boolean getWinged(ItemStack itemStack){
	
	Nbt.setTag(itemStack);
	
	return Nbt.getBoolean(itemStack, WINGED_KEY);
	
    }
    
    public static int getLifetimeComplete(ItemStack itemStack){
	
	Nbt.setTag(itemStack);
	
	return Nbt.getInt(itemStack, LIFETIME_COMPLETE_KEY);
	
    }
    
    public static int getLifetime(ItemStack itemStack){
	
	Nbt.setTag(itemStack);
	
	return Nbt.getInt(itemStack, LIFETIME_TOTAL_KEY);
	
    }
    
    public static void setLifetimeComplete(ItemStack item, int value){
	
	Nbt.setTag(item);
	
	Nbt.set(item, LIFETIME_COMPLETE_KEY, value);
	
    }
    
    public static void setMated(ItemStack item, boolean value){
	
	Nbt.setTag(item);
	
	Nbt.set(item, MATED_KEY, value);
	
    }
    
    public static boolean getMated(ItemStack itemStack){
	
	Nbt.setTag(itemStack);
	
	return Nbt.getBoolean(itemStack, MATED_KEY);
	
    }
    
    public static boolean getNocturnal(ItemStack itemStack){
	
	Nbt.setTag(itemStack);
	
	return Nbt.getBoolean(itemStack, NOCTURNAL_KEY);
	
    }
    
}
