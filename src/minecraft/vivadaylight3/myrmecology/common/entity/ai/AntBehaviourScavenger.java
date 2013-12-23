package vivadaylight3.myrmecology.common.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.api.entity.ai.EnumAntAIType;
import vivadaylight3.myrmecology.common.Log;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;

public class AntBehaviourScavenger extends EntityAIAntBehaviour {

    private String state = "none";
    private EntityItem targetItem;
    private TileEntity targetChest;

    public AntBehaviourScavenger(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	super(parEntityAnt, parWorld, parPathFinder);
	this.targetChest = world.getBlockTileEntity(
		(int) this.theAnt.getPosX(), (int) this.theAnt.getPosY() - 1,
		(int) this.theAnt.getPosZ());
    }

    @Override
    public EnumAntAIType getAIType() {

	return EnumAntAIType.SCAVENGING;

    }

    @Override
    public boolean shouldExecute() {

	if (targetChest == null) {

	    return false;

	}

	Log.debug("should");

	if (state.equalsIgnoreCase("none")
		&& nearestItemExists(searchForNearestItem())) {

	    Log.debug("should : state == none");

	    targetItem = (EntityItem) searchForNearestItem();

	    state = "itemPickup";

	    return true;

	} else if (state.equalsIgnoreCase("itemPickup") && targetItem != null) {

	    Log.debug("should : state == itemPickup");

	    return true;

	} else if (state.equalsIgnoreCase("itemDropOff")
		&& antChestExists(targetChest)) {

	    Log.debug("should : state == itemDropOff");

	    return true;

	} else {

	    return false;

	}

    }

    private void reset() {

	state = "none";
	targetItem = null;

    }

    @Override
    public void updateTask() {

	if (state.equalsIgnoreCase("itemPickup")) {

	    Log.debug("update : state == itemPickup");

	    pickUpItem();

	} else if (state.equalsIgnoreCase("itemDropOff")) {

	    Log.debug("update : state == itemDropOff");

	    dropOffItem();

	} else {

	    reset();

	}

    }

    @Override
    public void startExecuting() {

	this.updateTask();

    }

    private Entity searchForNearestItem() {

	Log.debug("searchForNearest");

	List list = Environment.getEntityItemsInRadius(world, this.getPosX(),
		this.getPosY(), this.getPosZ(), 20);

	return Environment.getNearestEntityFrom(list, this.getPosX(),
		this.getPosY(), this.getPosZ(), 20);

    }

    private boolean nearestItemExists(Entity entity) {

	Log.debug("nearestItemExists()");

	if (entity != null) {

	    if (entity instanceof EntityItem) {

		if (!entity.isDead) {

		    Log.debug("true");

		    return true;

		}

	    }

	}

	Log.debug("false");
	reset();

	return false;

    }

    private void pickUpItem() {

	Log.debug("pickUpItem()");

	if (Environment.inventoryCanHold(targetItem.getEntityItem(),
		this.theAnt.inventory, 64)) {

	    Log.debug("pickUpItem() : canHold");

	    this.theAnt.moveEntityTo(targetItem.posX, targetItem.posY,
		    targetItem.posZ);

	    double itemX = Math.ceil(targetItem.posX);

	    double itemY = Math.ceil(targetItem.posY), itemZ = (int) Math
		    .ceil(targetItem.posZ);

	    if (targetItem.posX > getPosX()) {

		itemY = Math.floor(targetItem.posX);

	    }

	    if (targetItem.posY > getPosY()) {

		itemY = Math.floor(targetItem.posY);

	    }

	    if (targetItem.posY > getPosZ()) {

		itemZ = Math.floor(targetItem.posZ);

	    }

	    if (Environment.coordinateIsCloseTo(itemX, itemY, itemZ,
		    (int) getPosX(), (int) getPosY(), (int) getPosZ(), 2)) {

		Log.debug("pickUpItem() : isClose");

		Environment.addItemStackToInventory(targetItem.getEntityItem(),
			this.theAnt.inventory, 64, null);

		targetItem.setDead();

		state = "itemDropOff";

	    }

	} else {

	    reset();

	}

    }

    private void dropOffItem() {

	Log.debug("dropOffItem");

	this.theAnt.moveEntityTo(targetChest.xCoord, targetChest.yCoord,
		targetChest.zCoord);

	if (Environment.coordinateIsCloseTo(getPosX(), getPosY(), getPosZ(),
		targetChest.xCoord, targetChest.yCoord, targetChest.zCoord, 1)) {

	    Log.debug("dropOffItem : isClose");

	    if (Environment.inventoryCanHold(this.theAnt.inventory[0],
		    ((TileEntityAntChest) this.targetChest).getContents(), 64)
		    && this.theAnt.inventory[0] != null) {

		Log.debug("dropOffItem : canHold");

		Environment.addItemStackToInventory(this.theAnt.inventory[0],
			((TileEntityAntChest) this.targetChest).getContents(),
			64, targetChest);

		this.theAnt.inventory[0] = null;

		this.state = "none";
	    }

	}

    }

    public boolean antChestExists(TileEntity te) {

	if (te != null) {

	    if (te instanceof TileEntityAntChest) {

		return true;

	    }

	}

	reset();

	return false;

    }

}
