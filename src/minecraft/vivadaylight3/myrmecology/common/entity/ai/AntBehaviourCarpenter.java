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

    public AntBehaviourCarpenter(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	
	super(parEntityAnt, parWorld, parPathFinder);

    }

    @Override
    public boolean shouldExecute() {
	
	Log.debug("");
	
	Log.debug("should");

	block = Environment.getNearestTreeEntryFrom(Environment.getBlocksInRadius(world, getPosX(), getPosY(), getPosZ(), radius), (Entity) this.theAnt, getPosX(), getPosY(), getPosZ());

	if(state.equals("none") && block != null){
	    
	    Log.debug("should : state == none");
	    
	    state = "moveTo";
	    
	    return true;
	    
	}else if(state.equals("treeChop")){
	    
	    return true;
	    
	}else if(state.endsWith("moveTo") && (Environment.coordinateIsCloseTo(getPosX(), getPosY(), getPosZ(), this.block.getxCoord(), this.block.getyCoord(), this.block.getzCoord(), 1))){
	    
	    state = "treeChop";
	    
	    return true;
	    
	}
	
	return false;
	
    }

    @Override
    public void startExecuting() {
	
	this.updateTask();

    }

    @Override
    public void updateTask() {
	
	Log.debug("");
	
	Log.debug("update");
	
	if(state.equals("treeChop")){
	    
	    Log.debug("update : state == treeChop");
	    
	    this.chopTree();
	    
	}else if(state.equals("moveTo")){
	    
	    Log.debug("update : state ==  moveTo");
	    
	    this.moveTo();
	    
	}

    }
    
    private void moveTo(){
	
	this.block = getBottomLog();
	
	Log.debug("moveTo : getBottom");
	
	if(block != null){
	    
	    this.theAnt.moveEntityTo(this.block.getxCoord(), this.block.getyCoord(), this.block.getzCoord());
	    
	    Log.debug("moveTo : block != null");
	    
	}
	
    }
    
    private void chopTree(){
	
	Log.debug("");
	
	this.block = getBottomLog();
	
	if(block != null){
	    
	    Log.debug("chopTree : block != null");
	    
	    ArrayList<BlockPosEntry> logs = getAllLogs();
	    
	    if(logs.size() > 0){
		
		Log.debug("chopTree : size > 0");
		
		for(BlockPosEntry entry : logs){
		    
		    Log.debug("log");
		    		    
		    world.setBlock(entry.xCoord, entry.yCoord, entry.yCoord, 1);
		    
		    Environment.spawnItem(new ItemStack(
			    Block.blocksList[entry.ID].idDropped(entry.metadata, new Random(), 0), 
			    Block.blocksList[entry.ID].quantityDropped(entry.metadata, 0, new Random()), 
			    Block.blocksList[entry.ID].damageDropped(entry.metadata)), world, getPosX(), 
			    getPosY(), getPosZ());
		    
		}
		
	    }
	    
	}
	
    }
    
    private ArrayList<BlockPosEntry> getAllLogs(){
	
	BlockPosEntry entry = block;
	ArrayList<BlockPosEntry> list = new ArrayList<BlockPosEntry>();
	
	int mod = 0;
	
	do{
	    
	    list.add(entry);
	    
	    for(int x = -1; x <= 1; x++){
				    
		for(int z = -1; z <= 1; z++){
			
		    if(TreeDictionary.contains(new BlockIDEntry(world.getBlockId(entry.xCoord + x, entry.yCoord, entry.zCoord + z),
			    world.getBlockMetadata(entry.xCoord + x, entry.yCoord, entry.xCoord + z)))){
			    
			    list.add(entry);
			    			    
		    }
					    
		}
		
	    }
	    
	    entry.setyCoord(entry.getyCoord() + 1);
	    
	    mod++;
	    
	}while(mod <= TreeDictionary.treeHeight);
	
	return list;
	
    }
    
    private BlockPosEntry getBottomLog(){
	
	BlockPosEntry entry = block;
	entry.setyCoord(block.getyCoord() - 1);
	
	if(TreeDictionary.getTreeEntryFromLog(entry.toBlockIDEntry()) == null){
	    
	    return block;
	    
	}
	
	for(int k = 0; k < TreeDictionary.treeHeight; k++){
	    
	    if(TreeDictionary.contains(entry.toBlockIDEntry())){
		
		entry.setyCoord(entry.getyCoord() - 1);
		
	    }else{
		
		return entry;
		
	    }
	    
	}
	
	return block;
	
    } 

}
