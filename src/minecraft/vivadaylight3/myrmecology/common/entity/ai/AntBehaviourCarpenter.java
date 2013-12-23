package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.api.entity.ai.EnumAntAIType;
import vivadaylight3.myrmecology.common.Log;
import vivadaylight3.myrmecology.common.lib.BlockPosEntry;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.TreeDictionary;

public class AntBehaviourCarpenter extends EntityAIAntBehaviour {

    private String state = "none";
    public static final int radius = 20;
    public BlockPosEntry block;
    ArrayList<BlockPosEntry> logs = new ArrayList<BlockPosEntry>();

    private int ticks = 0;

    int timeToWait = 0;

    BlockPosEntry bottom;
    private boolean isMoving = false;

    public AntBehaviourCarpenter(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {

	super(parEntityAnt, parWorld, parPathFinder);

    }

    @Override
    public EnumAntAIType getAIType() {

	return EnumAntAIType.DESTRUCTION;

    }

    @Override
    public boolean shouldExecute() {

	if (isMoving
		&& bottom != null
		&& !Environment.coordinateIsCloseTo(getPosX(), getPosY(),
			getPosZ(), bottom.xCoord, bottom.yCoord, bottom.zCoord,
			1)) {

	    this.moveTo();
	    return false;

	}

	if (Environment.hasPheromoneBlockInRadius(Environment
		.getBlocksInRadius(world, getPosX(), getPosY(), getPosZ(), 5),
		(Entity) this.theAnt, 5)) {

	    return false;

	}
	if (state.equals("finished")
		|| Environment.getNearestTreeEntryFrom(Environment
			.getBlocksInRadius(world, getPosX(), getPosY(),
				getPosZ(), radius), (Entity) this.theAnt,
			getPosX(), getPosY(), getPosZ()) == null
		|| (block != null && block.yCoord < 1)) {

	    reset();

	}

	if (state.equals("none")) {

	    block = Environment.getNearestTreeEntryFrom(Environment
		    .getBlocksInRadius(world, getPosX(), getPosY(), getPosZ(),
			    radius), (Entity) this.theAnt, getPosX(),
		    getPosY(), getPosZ());

	    if (block != null) {

		state = "moveTo";

		return true;

	    }

	} else if (state.equals("moveTo")) {

	    if (block != null) {

		if (Environment.coordinateIsCloseTo(getPosX(), getPosY(),
			getPosZ(), block.xCoord, block.yCoord, block.zCoord, 1)
			|| isNearBottom()) {

		    timeToWait = getTimeToWait();
		    this.isMoving = false;

		    state = "chopTree";

		}

	    }

	    return true;

	} else if (state.equals("chopTree")) {

	    if (bottom != null) {

		return true;

	    }

	}

	return false;

    }

    @Override
    public void updateTask() {

	if (state.equals("moveTo")) {

	    Log.debug("update : state == moveTo");

	    this.moveTo();

	} else if (state.equals("chopTree")) {

	    ticks++;

	    Log.debug(ticks + "/" + timeToWait);

	    if (ticks >= timeToWait) {

		Log.debug("update : state == chopTree");

		this.chopTree();

	    }

	} else if (state.equals("finished")) {

	    reset();

	} else {

	    reset();

	}

    }

    private void chopTree() {

	timeToWait = 0;

	if (this.logs != null) {

	    for (BlockPosEntry entry : logs) {

		if (entry != null) {

		    ArrayList<ItemStack> stacks = Block.blocksList[entry.ID]
			    .getBlockDropped(world, entry.xCoord, entry.yCoord,
				    entry.zCoord, entry.metadata, 0);

		    world.setBlockToAir(entry.xCoord, entry.yCoord,
			    entry.zCoord);

		    // ItemStack stack = new ItemStack(entry.ID, 1,
		    // entry.metadata);

		    for (ItemStack item : stacks) {

			Environment.spawnItem(item, world, entry.xCoord,
				entry.yCoord, entry.zCoord);

		    }

		} else {

		    reset();

		}

	    }

	} else {

	    reset();

	}

	reset();

    }

    private void reset() {

	state = "none";
	bottom = null;
	block = null;
	ticks = 0;
	this.logs.clear();

    }

    private void moveTo() {

	this.isMoving = true;

	Log.debug("moveTo");

	if (bottom == null) {

	    Log.debug("moveTo : bottom == null");

	    bottom = getBottomLog(block);

	    if (bottom == null) {

		reset();

	    }

	} else {

	    logs = getAllLogs(bottom);

	    Log.debug("moveTo : bottom != null");

	    this.theAnt.moveEntityTo(bottom.xCoord, bottom.yCoord,
		    bottom.zCoord);

	}

    }

    private int getTimeToWait() {

	float hardness = 0;

	for (BlockPosEntry entry : logs) {

	    if (entry != null) {

		hardness += Block.blocksList[entry.ID].getBlockHardness(world,
			entry.xCoord, entry.yCoord, entry.zCoord);

	    }

	}

	return (int) Math.floor(hardness / 7) * 20;

    }

    private ArrayList<BlockPosEntry> getAllLogs(BlockPosEntry block) {

	ArrayList<BlockPosEntry> result = new ArrayList<BlockPosEntry>();
	result.add(block);
	BlockPosEntry newLog;
	int k = 0;

	while (k < result.size()) {

	    BlockPosEntry current = result.get(k);

	    for (int x = -1 * TreeDictionary.treeWidth; x <= TreeDictionary.treeWidth; x++) {

		for (int y = -1; y <= 1; y++) {

		    for (int z = -1 * TreeDictionary.treeWidth; z <= TreeDictionary.treeWidth; z++) {

			newLog = new BlockPosEntry(
				current.xCoord + x,
				current.yCoord + y,
				current.zCoord + z,
				world.getBlockId(current.xCoord + x,
					current.yCoord + y, current.zCoord + z),
				world.getBlockMetadata(current.xCoord + x,
					current.yCoord + y, current.zCoord + z));

			if (TreeDictionary.contains(newLog.toBlockIDEntry())
				&& !logIsSet(result, newLog)) {

			    result.add(newLog);

			}

		    }

		}

	    }

	    k++;

	}

	return result;

    }

    private BlockPosEntry getBottomLog(BlockPosEntry block) {

	ArrayList<BlockPosEntry> list = getAllLogs(block);

	BlockPosEntry lowest = null;

	if (list.size() > 0) {

	    lowest = list.get(0);

	    for (BlockPosEntry entry : list) {

		if (lowest == null) {

		    lowest = entry;

		}

		if (entry.yCoord < lowest.yCoord) {

		    lowest = entry;

		}

	    }

	}

	return lowest;

    }

    private boolean logIsSet(ArrayList<BlockPosEntry> list, BlockPosEntry entry) {

	for (BlockPosEntry log : list) {

	    if (log.equals(entry)) {

		return true;

	    }

	}

	return false;

    }

    private boolean isNearBottom() {

	if (this.bottom != null) {

	    for (BlockPosEntry entry : logs) {

		if (entry.yCoord == bottom.yCoord) {

		    if (Environment.coordinateIsCloseTo(getPosX(), getPosY(),
			    getPosZ(), entry.xCoord, entry.yCoord,
			    entry.zCoord, 1)) {

			return true;

		    }

		}

	    }

	}

	return false;

    }

}