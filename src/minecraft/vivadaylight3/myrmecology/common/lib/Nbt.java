package vivadaylight3.myrmecology.common.lib;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Nbt {

    public static boolean hasTag(ItemStack itemStack) {

	return itemStack.hasTagCompound();

    }

    public static void setTag(ItemStack itemStack) {

	if (itemStack.getTagCompound() == null || !itemStack.hasTagCompound()) {

	    itemStack.setTagCompound(new NBTTagCompound());

	}

    }

    public static void set(ItemStack itemStack, String key, boolean value) {

	itemStack.getTagCompound().setBoolean(key, value);

    }

    public static void set(ItemStack itemStack, String key, int value) {

	itemStack.getTagCompound().setInteger(key, value);

    }

    public static void set(ItemStack itemStack, String key, String value) {

	itemStack.getTagCompound().setString(key, value);

    }

    public static void set(ItemStack itemStack, String key, byte value) {

	itemStack.getTagCompound().setByte(key, value);

    }

    public static void set(ItemStack itemStack, String key, short value) {

	itemStack.getTagCompound().setShort(key, value);

    }

    public static void set(ItemStack itemStack, String key, long value) {

	itemStack.getTagCompound().setLong(key, value);

    }

    public static void set(ItemStack itemStack, String key, float value) {

	itemStack.getTagCompound().setFloat(key, value);

    }

    public static void set(ItemStack itemStack, String key, double value) {

	itemStack.getTagCompound().setDouble(key, value);

    }

    public static void set(ItemStack itemStack, String key, int[] value) {

	itemStack.getTagCompound().setIntArray(key, value);

    }

    public static void set(ItemStack itemStack, String key, byte[] value) {

	itemStack.getTagCompound().setByteArray(key, value);

    }

    public static int getInt(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getInteger(key);

    }

    public static String getString(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getString(key);

    }

    public static byte getByte(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getByte(key);

    }

    public static short getShort(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getShort(key);

    }

    public static long getLong(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getLong(key);

    }

    public static float getFloat(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getFloat(key);

    }

    public static double getDouble(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getDouble(key);

    }

    public static byte[] getByteArray(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getByteArray(key);

    }

    public static int[] getIntArray(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getIntArray(key);

    }

    public static boolean getBoolean(ItemStack itemStack, String key) {

	return itemStack.getTagCompound().getBoolean(key);

    }

    public static boolean hasKey(ItemStack itemStack, String key) {

	if (itemStack.getTagCompound() == null) {

	    return false;

	}

	return itemStack.getTagCompound().hasKey(key);

    }

    public static void deleteKey(ItemStack itemStack, String key) {

	itemStack.getTagCompound().removeTag(key);

    }

}
