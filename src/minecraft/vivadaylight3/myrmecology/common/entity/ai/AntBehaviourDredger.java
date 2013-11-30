package vivadaylight3.myrmecology.common.entity.ai;

import java.sql.Time;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.api.entity.ai.EnumAntAIType;
import vivadaylight3.myrmecology.common.Log;
import vivadaylight3.myrmecology.common.lib.BlockPosEntry;
import vivadaylight3.myrmecology.common.lib.Environment;

public class AntBehaviourDredger extends EntityAIAntBehaviour {

    private BlockPosEntry block;
    private String state = "none";

    int radius = 20;
    int timeToWait = 0;

    private ArrayList<BlockPosEntry> blocks = new ArrayList<BlockPosEntry>();

    float maxHardness = 50.0f;

    public AntBehaviourDredger(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	super(parEntityAnt, parWorld, parPathFinder);
    }

    @Override
    public EnumAntAIType getAIType() {

	return EnumAntAIType.DESTRUCTION;

    }

    @Override
    public boolean shouldExecute() {

	Log.debug("");

	Log.debug("should");

	if (Environment.hasPheromoneBlockInRadius(Environment
		.getBlocksInRadius(world, getPosX(), getPosY(), getPosZ(), 5),
		(Entity) this.theAnt, 5)) {

	    Log.debug("hasPheromoneBlock");

	    return false;

	}

	sortBlocks();

	if (state.equals("none")) {

	    Log.debug("state == none");

	    if (block != null) {

		Log.debug("block != null");

		state = "moveTo";

		return true;

	    }

	} else if (state.equals("moveTo")) {

	    Log.debug("state == moveTo");

	    if (block != null) {

		Log.debug("block != null");

		if (Environment.coordinateIsCloseTo(getPosX(), getPosY(),
			getPosZ(), block.xCoord, block.yCoord, block.zCoord, 1)) {

		    Log.debug("isClose");

		    timeToWait = getTimeToWait();

		    state = "break";

		    return true;

		}

		return true;

	    }

	}

	return false;

    }

    public void updateTask() {

	Log.debug("");

	Log.debug("update");

	if (this.state.equals("moveTo")) {

	    Log.debug("moveTo");

	    this.moveTo();

	} else if (state.equals("break")) {

	    Log.debug("break");

	    this.breakBlock();

	}

    }

    private void breakBlock() {

	Log.debug("breakBlock()");

	if (block != null) {

	    Log.debug("block != null");

	    ArrayList<ItemStack> stacks = Block.blocksList[block.ID]
		    .getBlockDropped(world, block.xCoord, block.yCoord,
			    block.zCoord, block.metadata, 0);

	    world.setBlockToAir(block.xCoord, block.yCoord, block.zCoord);

	    for (ItemStack stack : stacks) {

		Environment.spawnItem(stack, world, block.xCoord, block.yCoord,
			block.zCoord);

	    }

	}

	reset();

    }

    private void moveTo() {

	Log.debug("moveTo()");

	if (block != null) {

	    Log.debug("block != null");

	    this.theAnt.moveEntityTo(block.xCoord, block.yCoord, block.zCoord);

	}

    }

    private void reset() {

	block = null;
	state = "none";
	timeToWait = 0;
	blocks.clear();

    }

    private void sortBlocks() {

	Log.debug("sort blocks");

	setBlocks();

	this.block = Environment.getNearestBlockFrom(blocks,
		(Entity) this.theAnt, getPosX(), getPosY(), getPosZ());

    }

    private int getTimeToWait() {
	return (int) (Block.blocksList[this.block.ID].getBlockHardness(world,
		block.xCoord, block.yCoord, block.zCoord) * 20);
    }

    private void setBlocks() {

	Log.debug("setBlocks");

	ArrayList<BlockPosEntry> temp = Environment.getBlocksInRadius(world,
		getPosX(), getPosY(), getPosZ(), 20);
	ItemStack tool = new ItemStack(Item.pickaxeStone);

	for (BlockPosEntry entry : temp) {

	    if (tool.canHarvestBlock(Block.blocksList[entry.ID])
		    && pathFinder.getPathToXYZ(entry.xCoord, entry.yCoord,
			    entry.zCoord) != null
		    && Block.blocksList[entry.ID].getBlockHardness(world,
			    entry.xCoord, entry.yCoord, entry.yCoord) < maxHardness) {

		Log.debug("found block");

		blocks.add(entry);

	    }

	}

    }

}
