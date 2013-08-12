package vivadaylight3.myrmecology.common.lib;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Nbt {
    
    public static void setTag(ItemStack itemStack){
	
	if(itemStack.stackTagCompound == null){
	    
	    itemStack.setTagCompound(new NBTTagCompound());
	    
	}
	
    }
    
    public static void set(ItemStack itemStack, String key, boolean value){
	
	itemStack.stackTagCompound.setBoolean(key, value);
	
    }
    
    public static void set(ItemStack itemStack, String key, int value){
	
	itemStack.stackTagCompound.setInteger(key, value);
	
    }
    
    public static void set(ItemStack itemStack, String key, String value){
	
	itemStack.stackTagCompound.setString(key, value);
	
    }
    
    public static void set(ItemStack itemStack, String key, byte value){
	
	itemStack.stackTagCompound.setByte(key, value);
	
    }
    
    public static void set(ItemStack itemStack, String key, short value){
	
	itemStack.stackTagCompound.setShort(key, value);
	
    }
    
    public static void set(ItemStack itemStack, String key, long value){
	
	itemStack.stackTagCompound.setLong(key, value);
	
    }
    
    public static void set(ItemStack itemStack, String key, float value){
	
	itemStack.stackTagCompound.setFloat(key, value);
	
    }
    
    public static void set(ItemStack itemStack, String key, double value){
	
	itemStack.stackTagCompound.setDouble(key, value);
	
    }
    
    public static void set(ItemStack itemStack, String key, int[] value){
	
	itemStack.stackTagCompound.setIntArray(key, value);
	
    }
    
    public static void set(ItemStack itemStack, String key, byte[] value){
	
	itemStack.stackTagCompound.setByteArray(key, value);
	
    }
    
    public static int getInt(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getInteger(key);
	
    }
     
    public static String getString(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getString(key);
	
    }
    
    public static byte getByte(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getByte(key);
	
    }
    
    public static short getShort(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getShort(key);
	
    }
    
    public static long getLong(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getLong(key);
	
    }
    
    public static float getFloat(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getFloat(key);
	
    }
    
    public static double getDouble(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getDouble(key);
	
    }
    
    public static byte[] getByteArray(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getByteArray(key);
	
    }
    
    public static int[] getIntArray(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getIntArray(key);
	
    }
    
    public static boolean getBoolean(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.getBoolean(key);
	
    }
    
    
    
    public static boolean hasKey(ItemStack itemStack, String key){
	
	return itemStack.stackTagCompound.hasKey(key);
	
    }
    
    public static void deleteKey(ItemStack itemStack, String key){
	
	itemStack.stackTagCompound.removeTag(key);
	
    }
    
}
