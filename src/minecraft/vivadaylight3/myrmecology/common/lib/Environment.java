package vivadaylight3.myrmecology.common.lib;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class Environment {

    public static boolean isRaining(World world) {

	return world.isRaining();

    }

    public static boolean isThundering(World world) {

	return world.isThundering();

    }

    /**
     * Gets all blocks with negative axis values within a radius from the given
     * coordinates
     * 
     * @param axis
     * @param radius
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static int[] getBlocksFrom(String axis, int radius, World world,
	    int x, int y, int z) {

	int[] result = new int[radius + 1];

	if (axis == "x") {

	    for (int k = 1; k < result.length; k++) {

		if (!world.isAirBlock(x - k, y, z)) {

		    result[k] = world.getBlockId(x - k, y, z);

		} else {

		    result[k] = 0;

		}

	    }

	} else if (axis == "y") {

	    for (int k = 1; k < result.length; k++) {

		if (!world.isAirBlock(x, y - k, z)) {

		    result[k] = world.getBlockId(x, y - k, z);

		} else {

		    result[k] = 0;

		}

	    }

	} else if (axis == "z") {

	    for (int k = 1; k < result.length; k++) {

		if (!world.isAirBlock(x, y, z - k)) {

		    result[k] = world.getBlockId(x, y, z - k);

		} else {

		    result[k] = 0;

		}

	    }

	} else {

	    // To prevent null pointer exceptions when using the returned array
	    // + an invalid axis is provided

	    for (int k = 0; k < result.length; k++) {

		result[k] = 0;

	    }

	}

	return result;

    }

    // TODO
    public int[][] getBlocksInRadius(World world, int x, int y, int z,
	    int radius) {

	int progress = 0;

	int offsetX = 0;
	int offsetY = 0;
	int offsetZ = 0;

	int blocksInSegment = radius * radius;
	int blocksInCorner = blocksInSegment * radius;
	int blocksInAxes = radius * 2;
	int planes = 3;
	int segments = 4;

	// int totalBlocks = ((blocksInSegment) * 4) * planes + (blocksInAxes *
	// 2) + (blocksInCorner * segments);
	int totalBlocks = Maths.intToPowerOf((radius * 2 + 1), 3) - 1;

	int[][] blocksArray = new int[totalBlocks][3];

	for (int k = 1; k <= radius; k++) {

	    for (int i = 1; i <= radius; i++) {

		blocksArray[progress][0] = x + offsetX;
		blocksArray[progress][1] = y + offsetY;
		blocksArray[progress][2] = z + offsetZ;

		offsetX += i;

	    }

	}

	return null;

    }

    public static boolean blockIsTouching(World world, int blockID, int x,
	    int y, int z) {

	if (world.getBlockId(x, y - 1, z) == blockID
		|| world.getBlockId(x, y + 1, z) == blockID
		|| world.getBlockId(x - 1, y, z) == blockID
		|| world.getBlockId(x + 1, y, z) == blockID
		|| world.getBlockId(x, y, z - 1) == blockID
		|| world.getBlockId(x, y, z + 1) == blockID) {

	    return true;

	} else {

	    return false;

	}

    }

    public static void spawnItem(ItemStack item, World world, int x, int y,
	    int z) {

	if (!world.isRemote
		&& world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
	    float f = 0.7F;
	    double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
	    double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
	    double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
	    EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z
		    + d2, item);
	    entityitem.delayBeforeCanPickup = 10;
	    world.spawnEntityInWorld(entityitem);
	}

    }

    public static BiomeGenBase getBiome(World world, int x, int z) {

	BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x, z);

	return biome;

    }

    public static int getBlockOrientation(int x, int y, int z,
	    EntityLiving entity) {

	int angle = MathHelper
		.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	int change = 0;

	switch (angle) {

	case 0:
	    change = 0;
	    break;

	case 1:
	    change = 1;
	    break;

	case 2:
	    change = 2;
	    break;

	case 3:
	    change = 3;
	    break;

	}

	return change;

    }

    /**
     * Returns the side name of the block depending on its metadata and side
     * 
     * @author VivaDaylight3
     * @param side
     * @param metadata
     * @param basemeta
     * @return String 'top', 'bottom', 'front', 'input', 'output' or 'back'
     */

    public static String getBlockSide(int side, int metadata, int basemeta) {

	int meta1 = basemeta;
	int meta2 = basemeta + 1;
	int meta3 = basemeta + 2;
	int meta4 = basemeta + 3;

	if (side == 1) {

	    return "top";

	}
	if (side == 0) {

	    return "bottom";

	} else if ((metadata == meta1 && side == 2)
		|| (metadata == meta2 && side == 5)
		|| (metadata == meta3 && side == 3)
		|| (metadata == meta4 && side == 4)) {

	    return "front";

	} else if ((metadata == meta1 && side == 4)
		|| (metadata == meta2 && side == 2)
		|| (metadata == meta3 && side == 5)
		|| (metadata == meta4 && side == 3)) {

	    return "input";

	} else if ((metadata == meta1 && side == 5)
		|| (metadata == meta2 && side == 3)
		|| (metadata == meta3 && side == 4)
		|| (metadata == meta4 && side == 2)) {

	    return "output";

	} else {

	    return "back";

	}

    }

    /**
     * Gets the input side depending on metadata
     * 
     * @param int metadata
     * @param int block's lowest metadata
     * @return int input side number
     */
    public static int getBlockInput(int metadata, int basemetadata) {

	if (metadata == basemetadata) {

	    return 4;

	} else if (metadata == basemetadata + 1) {

	    return 2;

	} else if (metadata == basemetadata + 2) {

	    return 5;

	} else {

	    return 3;

	}

    }

    /**
     * Gets the output side depending on metadata
     * 
     * @param int metadata
     * @param int block's lowest metadata
     * @return int output side number
     */
    public static int getBlockOutput(int metadata, int basemetadata) {

	if (metadata == basemetadata) {

	    return 5;

	} else if (metadata == basemetadata + 1) {

	    return 3;

	} else if (metadata == basemetadata + 2) {

	    return 4;

	} else {

	    return 2;

	}

    }

    /**
     * Returns true if the inventory can store an itemstack
     * 
     * @param item
     *            - The itemstack to be stored
     * @param inventory
     *            - The inventory to store the itemstack in
     * @param max
     *            - The inventory's maximum stack size
     * @return
     */
    public static boolean inventoryCanHold(ItemStack item,
	    ItemStack[] inventory, int max) {

	int itemAmount = item.stackSize;

	for (int k = 0; k <= inventory.length; k++) {

	    if (inventory[k] == null) {

		itemAmount -= max;

	    }

	    if (inventory[k] == item) {

		int slotSpace = max - inventory[k].stackSize;

		itemAmount -= slotSpace;

	    }

	    if (itemAmount <= 0) {

		return true;

	    }

	}

	return false;

    }

    /**
     * Returns the first available slot that can store a certain amount of
     * items.
     * 
     * @param item
     *            - The itemstack that needs to be stored.
     * @param inventory
     *            - The inventory variable
     * @param max
     *            - The maximum stack size for the inventory
     * @return int
     */
    public static int getAvailableSlot(ItemStack item, ItemStack[] inventory,
	    int max) {

	for (int k = 0; k < inventory.length; k++) {

	    if (inventory[k] == null) {

		return k;

	    } else if (inventory[k] == item) {

		if (inventory[k].stackSize + item.stackSize <= max) {

		    return k;

		}

	    }

	}

	return 0;

    }

    public static void addItemStackToInventory(ItemStack item,
	    ItemStack[] inventory, int max, TileEntity tileEntity) {

	int amount = item.stackSize;

	for (int k = 0; k < inventory.length; k++) {

	    if (amount > 0) {

		if (inventory[k] == null) {

		    if (amount > max) {

			amount -= max;

			inventory[k] = new ItemStack(item.getItem(), max,
				item.getItemDamage());

			tileEntity.onInventoryChanged();

		    } else if (amount < max) {

			inventory[k] = new ItemStack(item.getItem(), amount,
				item.getItemDamage());

			amount = 0;

			tileEntity.onInventoryChanged();

		    }

		} else if (inventory[k] == item) {

		    if (inventory[k].stackSize + item.stackSize <= max) {

			inventory[k] = new ItemStack(item.getItem(), max,
				item.getItemDamage());

			amount = max - inventory[k].stackSize;

			tileEntity.onInventoryChanged();

		    }

		}

	    }

	}

    }

}
