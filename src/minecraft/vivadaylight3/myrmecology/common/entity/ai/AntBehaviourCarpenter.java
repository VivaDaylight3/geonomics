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
import vivadaylight3.myrmecology.api.entity.ai.EnumAntAIType;
import vivadaylight3.myrmecology.common.Log;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
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
    public EnumAntAIType getAIType(){
	
	return EnumAntAIType.DESTRUCTION;
	
    }

    @Override
    public boolean shouldExecute() {

	ticks++;
	
	if(this.block != null && this.theAnt instanceof EntityAnt){
	    
	   ((EntityAnt) this.theAnt).behaviourErrorMessage = "State = " + state + " : block = " + block.toString() + " : bottom = " + bottom.toString();  
	    
	}
	
	if(state.equals("finished")
		|| Environment.getNearestTreeEntryFrom(Environment
		    .getBlocksInRadius(world, getPosX(), getPosY(), getPosZ(),
			    radius), (Entity) this.theAnt, getPosX(),
		    getPosY(), getPosZ()) == null 
		    || (block != null && block.yCoord < 1)){
	    
	    reset();
	    
	    return false;
	    
	}else if (state.equals("none")) {

	    block = Environment.getNearestTreeEntryFrom(Environment
		    .getBlocksInRadius(world, getPosX(), getPosY(), getPosZ(),
			    radius), (Entity) this.theAnt, getPosX(),
		    getPosY(), getPosZ());

	    if (block != null) {

		state = "moveTo";

		return true;

	    }

	}else if (state.equals("moveTo")) {

	    if (block != null) {

		if (Environment.coordinateIsCloseTo(getPosX(), getPosY(),
			getPosZ(), block.xCoord, block.yCoord, block.zCoord, 1)) {

		    state = "chopTree";

		}

	    }

	    return true;

	}else if (state.equals("chopTree")) {

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

	    Log.debug("update : state == chopTree");

	    this.chopTree();

	}else if(state.equals("finished")){
	    
	    reset();
	    
	}else{
	    
	    reset();
	    
	}

    }

    private void chopTree() {

	if (this.bottom != null) {

	    ArrayList<BlockPosEntry> logs = getAllLogs(bottom);

	    for (BlockPosEntry entry : logs) {

		if (entry != null) {

		    world.setBlockToAir(entry.xCoord, entry.yCoord, entry.zCoord);
		    
		    ItemStack stack = new ItemStack(Block.blocksList[entry.ID].idDropped(entry.metadata, world.rand, 0), Block.blocksList[entry.ID].quantityDropped(world.rand), Block.blocksList[entry.ID].damageDropped(entry.metadata));
		    
		    Environment.spawnItem(stack, world, entry.xCoord, entry.yCoord, entry.zCoord);

		}else{
		    
		    reset();
		    
		}

	    }
	    
	}else{
	    
	    reset();
	    
	}
	
	reset();

    }
    
    private void reset(){
	
	state = "none";
	bottom = null;
	block = null;
	
    }

    private void moveTo() {

	Log.debug("moveTo");

	if (bottom == null) {

	    Log.debug("moveTo : bottom == null");

	    bottom = getBottomLog(block);
	    
	    if(bottom == null){
		
		reset();
		
	    }

	} else {

	    Log.debug("moveTo : bottom != null");

	    this.theAnt.moveEntityTo(bottom.xCoord, bottom.yCoord,
		    bottom.zCoord);

	}

    }

    private ArrayList<BlockPosEntry> getAllLogs(BlockPosEntry block) {
	
	ArrayList<BlockPosEntry> result = new ArrayList<BlockPosEntry>();
	result.add(block);
	BlockPosEntry newLog;
	int k = 0;
	
	while(k < result.size()){
	    	    	    
	    BlockPosEntry current = result.get(k);
	    
	    for(int x = -1; x <= 1; x++){
		
		for(int y = -1; y <= 1; y++){
		    
		    for(int z = -1; z <= 1; z++){
			
			newLog = new BlockPosEntry(current.xCoord + x, current.yCoord + y, current.zCoord + z, world.getBlockId(current.xCoord + x, current.yCoord + y, current.zCoord + z), world.getBlockMetadata(current.xCoord + x, current.yCoord + y, current.zCoord + z));
			
			if(TreeDictionary.contains(newLog.toBlockIDEntry()) && !logIsSet(result, newLog)){
			    
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
		
		if(lowest == null){
		    
		    lowest = entry;
		    
		}

		if (entry.yCoord < lowest.yCoord) {

		    lowest = entry;

		}

	    }

	}

	return lowest;

    }
    
    private boolean logIsSet(ArrayList<BlockPosEntry> list, BlockPosEntry entry){
	
	for(BlockPosEntry log : list){
	    
	    if(log.equals(entry)){
		
		return true;
		
	    }
	    
	}
	
	return false;
	
    }

}