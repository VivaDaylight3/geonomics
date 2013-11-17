package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
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

	block = Environment.getNearestTreeEntryFrom(Environment.getBlocksInRadius(world, getPosX(), getPosY(), getPosZ(), radius), (Entity) this.theAnt, getPosX(), getPosY(), getPosZ());

	if(state.equals("none") && block != null){
	    
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
	
	if(state.equals("treeChop")){
	    
	    this.chopTree();
	    
	}

    }
    
    private void chopTree(){
	
	ArrayList<BlockPosEntry> logs = new ArrayList<BlockPosEntry>();
	
	this.block = getBottomLog();
	
	if(this.block != null){
	    
	    this.theAnt.moveEntityTo(this.block.getxCoord(), this.block.getyCoord(), this.block.getzCoord());
	    
	    if(Environment.coordinateIsCloseTo(getPosX(), getPosY(), getPosZ(), this.block.getxCoord(), this.block.getyCoord(), this.block.getzCoord(), 1)){
		
		logs = getAllLogs();
		
		for(int k = 0; k < logs.size(); k++){
		    
		    Environment.spawnItem(new ItemStack(logs.get(k).getID(), 1, logs.get(k).getMetadata()), world, logs.get(k).getxCoord(), logs.get(k).getyCoord(), logs.get(k).getzCoord());
		    world.setBlockToAir(logs.get(k).getxCoord(), logs.get(k).getyCoord(), logs.get(k).getyCoord());
		    
		    state = "none";
		    
		}
		
	    }
	    
	}
	
    }
    
    private ArrayList<BlockPosEntry> getAllLogs(){
	
	ArrayList<BlockPosEntry> result = new ArrayList<BlockPosEntry>();
	
	BlockPosEntry entry = block;
	
	while(TreeDictionary.getTreeEntryFromLog(entry.toBlockIDEntry()) != null){
	    
	    BlockPosEntry log = entry;
	    
	    for(int x = -1 * TreeDictionary.treeWidth; x <= TreeDictionary.treeWidth; x++){
		
		log.setxCoord(log.getxCoord() + x);
		
		for(int z = -1 * TreeDictionary.treeWidth; z <= TreeDictionary.treeWidth; z++){
		    
		    log.setxCoord(log.getzCoord() + z);
		    
		    if(TreeDictionary.getTreeEntryFromLog(log.toBlockIDEntry()) != null){
			
			result.add(log);
			
		    }
		    
		    entry.setyCoord(entry.getyCoord() + 1);
		    
		}
		
	    }
	    
	}
	
	return null;
	
    }
    
    private BlockPosEntry getBottomLog(){
	
	BlockPosEntry entry = block;
	entry.setyCoord(block.getyCoord() - 1);
	
	if(TreeDictionary.getTreeEntryFromLog(entry.toBlockIDEntry()) == null){
	    
	    return block;
	    
	}
	
	while(TreeDictionary.getTreeEntryFromLog(entry.toBlockIDEntry()) != null){
	    
	    entry.setyCoord(entry.getyCoord() - 1);
	    
	}
	
	return entry;
	
    }

}
