package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.api.entity.ai.EnumAntAIType;
import vivadaylight3.myrmecology.common.Log;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;

public class AntBehaviourMound extends EntityAIAntBehaviour {

    public AntBehaviourMound(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	super(parEntityAnt, parWorld, parPathFinder);
    }

    private String state = "none";
    private static int radius = 20;
    private static int flag = 1;

    TileEntityAntChest targetChest;
    EntityAnimal targetEntity;
    EntityAnimal targetEntity2;
    ArrayList<Entity> entityList;

    EntityAnimal entity = null;

    @Override
    public EnumAntAIType getAIType() {

	return EnumAntAIType.FARMING;

    }

    @Override
    public boolean shouldExecute() {

	// Log.debug("should");

	entityList = Environment.getAnimalsInRadius(world, null, getPosX(),
		getPosY(), getPosX(), radius);

	if (state.equalsIgnoreCase("none")
		&& Environment.getNearestAntChestFrom(Environment
			.getTileEntitiesInRadius(world, getPosX(), getPosY(),
				getPosZ(), radius), (Entity) this.theAnt,
			getPosX(), getPosY(), getPosZ()) != null) {

	    // Log.debug("should : state == none");

	    targetChest = (TileEntityAntChest) Environment
		    .getNearestAntChestFrom(Environment
			    .getTileEntitiesInRadius(world, getPosX(),
				    getPosY(), getPosZ(), radius),
			    (Entity) this.theAnt, getPosX(), getPosY(),
			    getPosZ());

	    state = "itemPickup";

	    return true;

	} else if (state.equalsIgnoreCase("itemPickup") && sortAnimals()) {

	    // Log.debug("should : state == itemPickup");

	    state = "animalCare";

	    return true;

	}

	return false;

    }

    @Override
    public void updateTask() {

	// Log.debug("update");

	if (state.equalsIgnoreCase("itemPickup")) {

	    // Log.debug("update : state == itemPickup");

	    pickupFood();

	} else if (state.equalsIgnoreCase("animalCare")) {

	    // Log.debug("update : state == animalCare");

	    breedAnimal();

	}

    }

    @Override
    public void startExecuting() {

	this.updateTask();

    }

    private void pickupFood() {

	// Log.debug("pickupFood");

	this.theAnt.moveEntityTo(targetChest.xCoord, targetChest.yCoord,
		targetChest.zCoord);

	if (Environment.coordinateIsCloseTo(getPosX(), getPosY(), getPosZ(),
		targetChest.xCoord, targetChest.yCoord, targetChest.zCoord, 1)) {

	    // Log.debug("pickupFood : isClose");

	    if (Environment.inventoryHas(new ItemStack(Item.wheat, 2),
		    targetChest.chestContents)) {

		// Log.debug("pickupFood : has");

		Environment.addItemStackToInventory(
			new ItemStack(Item.wheat, 2), this.theAnt.inventory,
			64, null);

		Environment.removeItemStackFromIventory(new ItemStack(
			Item.wheat, 2), this.theAnt.inventory, null);

		this.state = "animalCare";

	    }

	}

    }

    private void breedAnimal() {

	this.sortAnimals();

	if (targetEntity != null && targetEntity2 != null) {

	    // Log.debug("breed");

	    if (flag == 1) {

		// Log.debug("breed : flag == 1");

		entity = targetEntity;

	    } else if (flag == 1) {

		// Log.debug("breed : flag == 2");

		entity = targetEntity2;

	    }

	    this.theAnt.moveEntityTo(entity.serverPosX, entity.serverPosY,
		    entity.serverPosZ);

	    if (Environment.coordinateIsCloseTo(getPosX(), getPosY(),
		    getPosZ(), entity.serverPosX, entity.serverPosY,
		    entity.serverPosZ, 1)) {

		// Log.debug("breed : isClose");

		if (entity.getGrowingAge() == 0) {

		    // Log.debug("breed : age == 0");

		    if (flag == 1) {

			// Log.debug("breed : age == 0 : flag == 1");

			Environment.removeItemStackFromIventory(new ItemStack(
				Item.wheat, 1), this.theAnt.inventory, null);

			flag = 2;

			this.entity.func_110196_bT();

		    }
		    if (flag == 2) {

			// Log.debug("breed : age == 0 : flag == 2");

			Environment.removeItemStackFromIventory(new ItemStack(
				Item.wheat, 1), this.theAnt.inventory, null);

			flag = 1;

			this.entity.func_110196_bT();

			state = "none";

		    }

		}

	    }

	}

    }

    private boolean sortAnimals() {

	Log.debug("sortAnimals");

	entityList = Environment.getEntitiesInRadius(world, getPosX(),
		getPosY(), getPosX(), radius);

	int chickenCount = 0;
	int cowCount = 0;
	int pigCount = 0;
	int sheepCount = 0;

	Log.debug("sortAnimals : size = " + entityList.size());

	for (int k = 0; k < entityList.size(); k++) {

	    if (entityList.get(k) instanceof EntityChicken) {

		Log.debug("chicken++");

		chickenCount++;

	    } else if (entityList.get(k) instanceof EntityCow) {

		Log.debug("cow++");

		cowCount++;

	    } else if (entityList.get(k) instanceof EntityPig) {

		Log.debug("pig++");

		pigCount++;

	    } else if (entityList.get(k) instanceof EntitySheep) {

		Log.debug("sheep++");

		sheepCount++;

	    }

	}

	if (chickenCount > 1) {

	    Log.debug("chicken > 1");

	    targetEntity = (EntityAnimal) Environment.getNearestEntityFrom(
		    Environment.getAnimalsInRadius(world, null,
			    EntityChicken.class, getPosX(), getPosY(),
			    getPosZ(), radius), getPosX(), getPosY(),
		    getPosZ(), radius);
	    targetEntity2 = (EntityAnimal) Environment.getNearestEntityFrom(
		    Environment.getAnimalsInRadius(world, targetEntity,
			    EntityChicken.class, getPosX(), getPosY(),
			    getPosZ(), radius), getPosX(), getPosY(),
		    getPosZ(), radius);

	    return true;

	} else if (cowCount > 1) {

	    Log.debug("cow > 1");

	    targetEntity = (EntityAnimal) Environment.getNearestEntityFrom(
		    Environment.getAnimalsInRadius(world, null,
			    EntityCow.class, getPosX(), getPosY(), getPosZ(),
			    radius), getPosX(), getPosY(), getPosZ(), radius);
	    targetEntity2 = (EntityAnimal) Environment.getNearestEntityFrom(
		    Environment.getAnimalsInRadius(world, targetEntity,
			    EntityCow.class, getPosX(), getPosY(), getPosZ(),
			    radius), getPosX(), getPosY(), getPosZ(), radius);

	    return true;

	} else if (pigCount > 1) {

	    Log.debug("pig > 1");

	    targetEntity = (EntityAnimal) Environment.getNearestEntityFrom(
		    Environment.getAnimalsInRadius(world, null,
			    EntityPig.class, getPosX(), getPosY(), getPosZ(),
			    radius), getPosX(), getPosY(), getPosZ(), radius);
	    targetEntity2 = (EntityAnimal) Environment.getNearestEntityFrom(
		    Environment.getAnimalsInRadius(world, targetEntity,
			    EntityPig.class, getPosX(), getPosY(), getPosZ(),
			    radius), getPosX(), getPosY(), getPosZ(), radius);

	    return true;

	} else if (sheepCount > 1) {

	    Log.debug("sheep > 1");

	    targetEntity = (EntityAnimal) Environment.getNearestEntityFrom(
		    Environment.getAnimalsInRadius(world, null,
			    EntitySheep.class, getPosX(), getPosY(), getPosZ(),
			    radius), getPosX(), getPosY(), getPosZ(), radius);
	    targetEntity2 = (EntityAnimal) Environment.getNearestEntityFrom(
		    Environment.getAnimalsInRadius(world, targetEntity,
			    EntitySheep.class, getPosX(), getPosY(), getPosZ(),
			    radius), getPosX(), getPosY(), getPosZ(), radius);

	    return true;

	}

	return false;

    }

}
