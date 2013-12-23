package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.common.Log;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;

public class Environment {

    public static Entity getNearestEntityFrom(List list, double x, double y,
	    double z, int distance) {

	return getNearestEntityFrom(list, (int) x, (int) y, (int) z, distance);

    }

    public static Entity getNearestEntityFrom(List list, int x, int y, int z,
	    int distance) {

	double d4 = -1.0D;
	Entity entity = null;

	if (list.size() <= 0 || list == null) {

	    return null;

	}

	for (int i = 0; i < list.size(); ++i) {
	    Entity entity1 = (Entity) list.get(i);
	    double d5 = entity1.getDistanceSq(x, y, z);

	    if ((distance < 0.0D || d5 < distance * distance)
		    && (d4 == -1.0D || d5 < d4)) {
		d4 = d5;
		entity = entity1;
	    }
	}

	return entity;

    }

    public static TileEntity getNearestTileEntityFrom(
	    ArrayList<TileEntity> list, Entity entity, double x, double y,
	    double z) {

	TileEntity nearest = null;

	if (list == null || list.size() < 1) {

	    return null;

	}

	nearest = list.get(0);

	for (TileEntity tile : list) {

	    if (entity.getDistance(tile.xCoord, tile.yCoord, tile.zCoord) < entity
		    .getDistance(nearest.xCoord, nearest.yCoord, nearest.zCoord)) {

		nearest = tile;

	    }

	}

	return nearest;

    }

    public static TileEntity getNearestAntChestFrom(ArrayList<TileEntity> list,
	    Entity entity, double x, double y, double z) {

	TileEntity nearest = null;

	if (list == null || list.size() < 1) {

	    return null;

	}

	if (list.get(0) instanceof TileEntityAntChest) {

	    nearest = list.get(0);

	}

	for (TileEntity tile : list) {

	    if (nearest != null) {

		if (entity.getDistance(tile.xCoord, tile.yCoord, tile.zCoord) < entity
			.getDistance(nearest.xCoord, nearest.yCoord,
				nearest.zCoord)
			&& tile instanceof TileEntityAntChest) {

		    nearest = tile;

		}

	    }

	}

	return nearest;

    }

    public static boolean hasPheromoneBlockInRadius(
	    ArrayList<BlockPosEntry> list, Entity entity, int distance) {

	for (BlockPosEntry entry : list) {

	    if (entity.getDistance(entry.xCoord, entry.yCoord, entry.zCoord) <= distance
		    && entry.ID == Register.blockPheromone.blockID) {

		return true;

	    }

	}

	return false;

    }

    public static BlockPosEntry getNearestTreeEntryFrom(
	    ArrayList<BlockPosEntry> list, Entity entity, double x, double y,
	    double z) {

	BlockPosEntry nearest = null;

	if (list == null || list.size() < 1) {

	    return null;

	} else {

	}

	nearest = list.get(0);

	for (BlockPosEntry tile : list) {

	    if (entity.getDistance(tile.xCoord, tile.yCoord, tile.zCoord) < entity
		    .getDistance(nearest.xCoord, nearest.yCoord, nearest.zCoord)
		    && TreeDictionary.contains(tile.toBlockIDEntry())) {

		nearest = tile;

	    }

	}

	return nearest;

    }

    public static BlockPosEntry getNearestBlockFrom(
	    ArrayList<BlockPosEntry> list, Entity entity, double x, double y,
	    double z) {

	BlockPosEntry nearest = null;

	if (list == null || list.size() < 1) {

	    return null;

	} else {

	}

	nearest = list.get(0);

	for (BlockPosEntry tile : list) {

	    if (entity.getDistance(tile.xCoord, tile.yCoord, tile.zCoord) < entity
		    .getDistance(nearest.xCoord, nearest.yCoord, nearest.zCoord)) {

		nearest = tile;

	    }

	}

	return nearest;

    }

    public static Entity spawnEntity(World par0World, Entity entity,
	    double par2, double par4, double par6) {

	if (entity != null && entity instanceof EntityLivingBase) {
	    EntityLiving entityliving = (EntityLiving) entity;
	    entity.setLocationAndAngles(par2, par4, par6, MathHelper
		    .wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F),
		    0.0F);
	    entityliving.rotationYawHead = entityliving.rotationYaw;
	    entityliving.renderYawOffset = entityliving.rotationYaw;
	    entity.setPosition(par2, par4, par6);
	    par0World.spawnEntityInWorld(entity);
	    entityliving.playLivingSound();
	}

	return entity;

    }

    public static boolean coordinateIsCloseTo(double x, double y, double z,
	    int x2, int y2, int z2, int distance) {

	return coordinateIsCloseTo((int) x, (int) y, (int) z, x2, y2, z2,
		distance);

    }

    public static boolean coordinateIsCloseTo(int x, int y, int z, int x2,
	    int y2, int z2, int distance) {

	if (Math.max(x, x2) - Math.min(x2, x) <= distance
		&& Math.max(y, y2) - Math.min(y2, y) <= distance
		&& Math.max(z, z2) - Math.min(z2, z) <= distance) {

	    return true;

	}

	return false;

    }

    public static List getEntityItemsInRadius(World world, double x, double y,
	    double z, int radius) {

	List list = world.getEntitiesWithinAABB(
		EntityItem.class,
		AxisAlignedBB.getAABBPool().getAABB(x - radius, y - radius,
			z - radius, x + radius, y + radius, z + radius));

	return list;

    }

    public static ArrayList<TileEntity> getTileEntitiesInRadius(World world,
	    double x, double y, double z, int radius) {

	return getTileEntitiesInRadius(world, (int) x, (int) y, (int) z, radius);

    }

    public static ArrayList<TileEntity> getTileEntitiesInRadius(World world,
	    int x, int y, int z, int radius) {

	ArrayList<TileEntity> result = new ArrayList<TileEntity>();

	for (int newX = -1 * radius; newX <= radius; newX++) {

	    for (int newY = -1 * radius; newY <= radius; newY++) {

		for (int newZ = -1 * radius; newZ <= radius; newZ++) {

		    if (newX * newX + newY * newY + newZ * newZ <= radius
			    * radius) {

			if (world.getBlockTileEntity(newX + x, newY + y, newZ
				+ z) != null) {

			    result.add(world.getBlockTileEntity(newX + x, newY
				    + y, newZ + z));

			}

		    }

		}

	    }

	}

	return result;

    }

    public static ArrayList<Entity> getEntitiesInRadius(World world, double x,
	    double y, double z, int radius) {

	AxisAlignedBB axisalignedbb = AxisAlignedBB
		.getAABBPool()
		.getAABB(x, y, z, (double) (x + 1), (double) (y + 1),
			(double) (z + 1)).expand(radius, radius, radius);
	axisalignedbb.maxY = (double) world.getHeight();
	List list = world.getEntitiesWithinAABB(EntityPlayer.class,
		axisalignedbb);

	ArrayList<Entity> result = new ArrayList<Entity>();

	for (int k = 0; k < list.size(); k++) {

	    result.add((Entity) list.get(k));

	}
	return result;
    }

    public static ArrayList<Entity> getAnimalsInRadius(World world,
	    Entity excluding, double x, double y, double z, int radius) {

	AxisAlignedBB axisalignedbb = AxisAlignedBB
		.getAABBPool()
		.getAABB(x, y, z, (double) (x + 1), (double) (y + 1),
			(double) (z + 1)).expand(radius, radius, radius);
	axisalignedbb.maxY = (double) world.getHeight();
	List list = world.getEntitiesWithinAABB(Entity.class, axisalignedbb);

	ArrayList<Entity> result = new ArrayList<Entity>();

	for (int k = 0; k < list.size(); k++) {

	    if (list.get(k) != excluding) {

		result.add((Entity) list.get(k));

	    }

	}

	return result;
    }

    public static ArrayList<EntityAnimal> getAnimalsInRadius(World world,
	    Entity excluding, Class<? extends Entity> class1, double x,
	    double y, double z, int radius) {

	AxisAlignedBB axisalignedbb = AxisAlignedBB
		.getAABBPool()
		.getAABB(x, y, z, (double) (x + 1), (double) (y + 1),
			(double) (z + 1)).expand(radius, radius, radius);
	axisalignedbb.maxY = (double) world.getHeight();
	List list = world.getEntitiesWithinAABB(class1, axisalignedbb);

	ArrayList<EntityAnimal> result = new ArrayList<EntityAnimal>();

	for (int k = 0; k < list.size(); k++) {

	    if (list.get(k) != excluding) {

		result.add((EntityAnimal) list.get(k));

	    }

	}
	return result;
    }

    public static boolean blockIsPowered(IBlockAccess world, int x, int y, int z) {

	if (((World) world).getBlockPowerInput(x, y, z) > 0) {

	    return true;

	}

	return false;

    }

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

    public static ArrayList<BlockPosEntry> getBlockIDsInRadius(World world,
	    int x, int y, int z, int radius) {

	ArrayList<BlockPosEntry> result = new ArrayList<BlockPosEntry>();

	for (int newX = -1 * radius; newX <= radius; newX++) {

	    for (int newY = -1 * radius; newY <= radius; newY++) {

		for (int newZ = -1 * radius; newZ <= radius; newZ++) {

		    if (newX * newX + newY * newY + newZ * newZ <= radius
			    * radius) {

			result.add(new BlockPosEntry(newX + x, newY + y, newZ
				+ z, world.getBlockId(x, y, z), world
				.getBlockMetadata(newX + x, newY + y, newZ + z)));

		    }

		}

	    }

	}

	return result;

    }

    public static ArrayList<BlockPosEntry> getBlocksInRadius(World world,
	    double x, double y, double z, int radius) {

	return getBlocksInRadius(world, (int) x, (int) y, (int) z, radius);

    }

    public static ArrayList<BlockPosEntry> getBlocksInRadius(World world,
	    int x, int y, int z, int radius) {

	ArrayList<BlockPosEntry> result = new ArrayList<BlockPosEntry>();

	for (int newX = -1 * radius; newX <= radius; newX++) {

	    for (int newY = -1 * radius; newY <= radius; newY++) {

		for (int newZ = -1 * radius; newZ <= radius; newZ++) {

		    if (newX * newX + newY * newY + newZ * newZ <= radius
			    * radius) {

			if (world.getBlockId(newX + x, newY + y, newZ + z) > 0) {

			    result.add(new BlockPosEntry(newX + x, newY + y,
				    newZ + z, world.getBlockId(newX + x, newY
					    + y, newZ + z), world
					    .getBlockMetadata(newX + x, newY
						    + y, newZ + z)));

			}

		    }

		}

	    }

	}

	return result;

    }

    public int getMaxFromRadius(int radius) {

	// Num of blocks in segments + Num of blocks in middle slice

	return (int) (4 * Math.pow(radius, 3) + (6 * radius) + (4 * Math.pow(
		radius, 2)));

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

    public static void spawnItem(ItemStack item, World world, double x,
	    double y, double z) {

	spawnItem(item, world, (int) x, (int) y, (int) z);

    }

    public static void spawnItem(ItemStack item, World world, int x, int y,
	    int z) {

	if (item != null) {
	    EntityItem entityitem = new EntityItem(world, x, y, z, item);
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

    public static int getBlockSide(String side, int metadata, int basemeta) {

	if (side.equals("top")) {

	    return 1;

	} else if (side.equals("bottom")) {

	    return 0;

	} else if (side.equals("front")) {

	    if (metadata == basemeta) {

		return 2;

	    } else if (metadata == basemeta + 1) {

		return 5;

	    } else if (metadata == basemeta + 2) {

		return 3;

	    } else if (metadata == basemeta + 3) {

		return 4;

	    }

	} else if (side.equals("input")) {

	    if (metadata == basemeta) {

		return 4;

	    } else if (metadata == basemeta + 1) {

		return 2;

	    } else if (metadata == basemeta + 2) {

		return 5;

	    } else if (metadata == basemeta + 3) {

		return 3;

	    }

	} else if (side.equals("output")) {

	    if (metadata == basemeta) {

		return 5;

	    } else if (metadata == basemeta + 1) {

		return 3;

	    } else if (metadata == basemeta + 2) {

		return 4;

	    } else if (metadata == basemeta + 3) {

		return 2;

	    }

	} else if (side.equals("back")) {

	    if (metadata == basemeta) {

		return 4;

	    } else if (metadata == basemeta + 1) {

		return 3;

	    } else if (metadata == basemeta + 2) {

		return 5;

	    } else if (metadata == basemeta + 3) {

		return 2;

	    }

	}

	return -1;

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

    public static EnumBlockSide getBlockSide(int side, int metadata,
	    int basemeta) {

	int meta1 = basemeta;
	int meta2 = basemeta + 1;
	int meta3 = basemeta + 2;
	int meta4 = basemeta + 3;

	if (side == 1) {

	    return EnumBlockSide.TOP;

	}
	if (side == 0) {

	    return EnumBlockSide.BOTTOM;

	} else if ((metadata == meta1 && side == 2)
		|| (metadata == meta2 && side == 5)
		|| (metadata == meta3 && side == 3)
		|| (metadata == meta4 && side == 4)) {

	    return EnumBlockSide.FRONT;

	} else if ((metadata == meta1 && side == 4)
		|| (metadata == meta2 && side == 2)
		|| (metadata == meta3 && side == 5)
		|| (metadata == meta4 && side == 3)) {

	    return EnumBlockSide.LEFT;

	} else if ((metadata == meta1 && side == 5)
		|| (metadata == meta2 && side == 3)
		|| (metadata == meta3 && side == 4)
		|| (metadata == meta4 && side == 2)) {

	    return EnumBlockSide.RIGHT;

	} else {

	    return EnumBlockSide.BACK;

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

	if (item == null) {

	    return false;

	}

	int left = item.stackSize;

	for (int k = 0; k < item.stackSize; k++) {

	    Log.debug("");
	    Log.debug("NEW STACK SIZE CHECK");

	    if (left < 1) {

		return true;

	    }

	    for (int i = 0; i < inventory.length; i++) {

		Log.debug("NEW SLOT CHECK");
		Log.debug("left == " + left);

		if (left < 1) {

		    return true;

		}

		if (inventory[i] == null) {

		    Log.debug("inv[" + i + "] == null");

		    if (left <= max) {

			Log.debug("left <= max");
			left -= max;

		    }

		} else {

		    if (inventory[i].getItem() == item.getItem()
			    && inventory[i].getItemDamage() == item
				    .getItemDamage()) {

			Log.debug("inv[" + i + "] == item");

			if (inventory[i].stackSize + left <= max) {

			    Log.debug("inv[" + i + "].stackSize + " + left
				    + " <= " + max);
			    left -= max;

			} else {

			    Log.debug("inv[" + i + "].stackSize + " + left
				    + " > " + max);
			    left -= (max - inventory[i].stackSize);

			}

		    }

		}

	    }

	    if (left < 1) {

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

	return -1;

    }

    public static boolean inventoryHas(ItemStack item, ItemStack[] inventory) {

	for (int k = 0; k < inventory.length; k++) {

	    if (inventory[k] != null) {

		if (inventory[k].getItem() == item.getItem()) {

		    if (inventory[k].stackSize >= item.stackSize) {

			return true;

		    }

		}

	    }

	}

	return false;

    }

    public static void removeItemStackFromIventory(ItemStack item,
	    ItemStack[] inventory, TileEntity tileEntity) {

	int amount = item.stackSize;

	for (int k = 0; k < inventory.length; k++) {

	    while (amount > 0) {

		if (inventory[k].getItem() == item.getItem()
			&& inventory[k].getItemDamage() == item.getItemDamage()) {

		    if (amount > inventory[k].stackSize) {

			amount -= inventory[k].stackSize;

			inventory[k] = null;

			if (tileEntity != null) {
			    tileEntity.onInventoryChanged();
			}

		    } else if (amount <= inventory[k].stackSize) {

			inventory[k] = new ItemStack(item.getItem(),
				inventory[k].stackSize - amount,
				item.getItemDamage());

			amount = 0;

			if (tileEntity != null) {
			    tileEntity.onInventoryChanged();
			}

		    }

		}

	    }

	}

    }

    public static void addItemStackToInventory(ItemStack item,
	    ItemStack[] inventory, int max, TileEntity tileEntity) {

	addItemStackToInventory(item, inventory, max, tileEntity, 0);

    }

    public static void addItemStackToInventory(ItemStack item,
	    ItemStack[] inventory, int max, TileEntity tileEntity, int startSlot) {

	int left = item.stackSize;

	for (int k = 0; k < item.stackSize; k++) {

	    if (left < 1) {

		return;

	    }

	    for (int i = startSlot; i < inventory.length; i++) {

		if (left < 1) {

		    return;

		}

		if (inventory[i] == null) {

		    if (left <= max) {

			ItemStack stack = item.copy();
			stack.stackSize = left;
			inventory[i] = stack;
			left -= max;

		    } else {

			ItemStack stack = item.copy();
			stack.stackSize = max;
			inventory[i] = stack;
			left -= max;

		    }

		} else {

		    if (inventory[i].getItem() == item.getItem()
			    && inventory[i].getItemDamage() == item
				    .getItemDamage()) {

			if (inventory[i].stackSize < max) {

			    if (inventory[i].stackSize + left <= max) {

				inventory[i].stackSize += left;
				left -= max;

			    } else {

				left -= (max - inventory[i].stackSize);
				inventory[i].stackSize = max;

			    }

			}

		    }

		}

	    }

	}

	return;

    }

}
