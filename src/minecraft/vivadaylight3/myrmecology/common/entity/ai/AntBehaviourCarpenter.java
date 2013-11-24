package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.common.Log;
import vivadaylight3.myrmecology.common.lib.BlockIDEntry;
import vivadaylight3.myrmecology.common.lib.BlockPosEntry;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.TreeDictionary;

public class AntBehaviourCarpenter extends EntityAIAntBehaviour {

    private String state = "none";
    public static final int radius = 20;
    public BlockPosEntry block;

    private int ticks = 0;

    BlockPosEntry bottom;

    public AntBehaviourCarpenter(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {

	super(parEntityAnt, parWorld, parPathFinder);

    }

    @Override
    public boolean shouldExecute() {

	ticks++;

	Log.debug("");
	Log.debug("should");

	if (state.equals("none")) {

	    Log.debug("should : state == none");

	    block = Environment.getNearestTreeEntryFrom(Environment
		    .getBlocksInRadius(world, getPosX(), getPosY(), getPosZ(),
			    radius), (Entity) this.theAnt, getPosX(),
		    getPosY(), getPosZ());

	    if (block != null) {

		Log.debug("should : block != null");

		state = "moveTo";

		return true;

	    }

	} else if (state.equals("moveTo")) {

	    Log.debug("should : state == moveTo");

	    if (block != null) {

		Log.debug("should : block != null");

		Log.debug("should : block " + block.xCoord + ", "
			+ block.yCoord + ", " + block.zCoord);

		if (Environment.coordinateIsCloseTo(getPosX(), getPosY(),
			getPosZ(), block.xCoord, block.yCoord, block.zCoord, 1)) {

		    Log.debug("should : isClose");

		    state = "chopTree";

		}

	    }

	    return true;

	} else if (state.equals("chopTree")) {

	    Log.debug("should : state == chopTree");

	    if (bottom != null) {

		Log.debug("should : bottom != null");

		return true;

	    }

	}

	return false;

    }

    @Override
    public void updateTask() {

	Log.debug("");
	Log.debug("update");

	if (state.equals("moveTo")) {

	    Log.debug("update : state == moveTo");

	    this.moveTo();

	} else if (state.equals("chopTree")) {

	    Log.debug("update : state == chopTree");

	    this.chopTree();

	}

    }

    private void chopTree() {

	Log.debug("chopTree");

	if (this.bottom != null) {

	    Log.debug("chopTree : bottom != null");

	    ArrayList<BlockPosEntry> logs = getAllLogs(bottom);

	    for (BlockPosEntry entry : logs) {

		if (entry != null) {

		    Log.debug("chopTree : entry[" + entry.xCoord + ", "
			    + entry.yCoord + ", " + entry.zCoord + "]");

		    world.setBlock(entry.xCoord, entry.yCoord, entry.zCoord, 5);

		}

	    }

	}

	state = "none";

    }

    private void moveTo() {

	Log.debug("moveTo");

	if (bottom == null) {

	    Log.debug("moveTo : bottom == null");

	    bottom = getBottomLog(block);

	} else {

	    Log.debug("moveTo : bottom != null");

	    this.theAnt.moveEntityTo(bottom.xCoord, bottom.yCoord,
		    bottom.zCoord);

	}

    }

    private ArrayList<BlockPosEntry> getAllLogs(BlockPosEntry block) {

	ArrayList<BlockPosEntry> result = new ArrayList<BlockPosEntry>();

	for (int y = 0; y <= TreeDictionary.treeHeight; y++) {

	    for (int x = -1 * TreeDictionary.treeWidth; x <= TreeDictionary.treeWidth; x++) {

		for (int z = -1 * TreeDictionary.treeWidth; z <= TreeDictionary.treeWidth; z++) {

		    if (TreeDictionary.contains(new BlockIDEntry(world
			    .getBlockId(block.xCoord + x, block.yCoord + y,
				    block.zCoord + z), world.getBlockMetadata(
			    block.xCoord + x, block.yCoord + y, block.zCoord
				    + z)))) {

			result.add(new BlockPosEntry(world.getBlockId(
				block.xCoord + x, block.yCoord + y,
				block.zCoord + z), world.getBlockMetadata(
				block.xCoord + x, block.yCoord + y,
				block.zCoord + z), block.xCoord + x,
				block.yCoord + y, block.zCoord + z));
			Log.debug("getAllLogs : found log at " + x + ", " + y
				+ ", z");

		    }

		}

	    }

	}

	return result;

    }

    private BlockPosEntry getBottomLog(BlockPosEntry block) {

	ArrayList<BlockPosEntry> list = getAllLogs(block);

	BlockPosEntry lowest = null;

	if (list.size() > 0) {

	    lowest = list.get(0);

	    for (BlockPosEntry entry : list) {

		if (entry.yCoord < lowest.yCoord) {

		    lowest = entry;

		}

	    }

	}

	return lowest;

    }

}