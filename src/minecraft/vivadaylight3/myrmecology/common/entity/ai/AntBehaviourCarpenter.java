package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
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
    public boolean shouldExecute(){
	
	BlockPosEntry entry = Environment.getNearestBlockFrom(Environment.getBlocksInRadius(world, getPosX(), getPosY(), getPosY(), radius), (Entity) this.theAnt, getPosX(), getPosY(), getPosY());
	
	if(state.equals("none") &&  entry != null){
	    
	    block = getBottomLogFrom(entry);
	    
	    state = "moveToTree";
	    
	    return true;
	    
	}else if(state.equals("moveToTree") && Environment.coordinateIsCloseTo(getPosX(), getPosX(), getPosX(), block.xCoord, block.yCoord, block.zCoord, radius)){
	
	    state = "chopTree";
	    
	    return true;
	
    	}
	
	return false;
	
    }

    @Override
    public void startExecuting() {
	
	

    }

    @Override
    public void updateTask() {

	if(state.equals("moveToTree")){
	    
	    this.theAnt.moveEntityTo(block.xCoord, block.yCoord, block.zCoord);
	    
	}else if(state.equals("chopTree")){
	    
	    
	    
	}

    }
    
    public void breakTree(){
	
	ArrayList<BlockPosEntry> logList = new ArrayList<BlockPosEntry>();
	
	BlockPosEntry start = block;
	logList.add(block);
	
	int yModifier = 1;
	BlockPosEntry newBlock = block.clone();
	newBlock.setyCoord(newBlock.getyCoord() + yModifier);
	
	while(TreeDictionary.getTreeEntryFromLog(newBlock.toBlockIDEntry()) != null){
	    
	    yModifier++;
	    newBlock.setyCoord(newBlock.getyCoord() + yModifier);
	    newBlock.setID(world.getBlockId(newBlock.getyCoord(), newBlock.getxCoord(), newBlock.getzCoord()));
	    newBlock.setMetadata(world.getBlockMetadata(newBlock.getyCoord(), newBlock.getxCoord(), newBlock.getzCoord()));
	    logList.add(newBlock);
	}
	
	for(BlockPosEntry entry : logList){
	    
	    world.setBlockToAir(entry.xCoord, entry.yCoord, entry.zCoord);
	    
	}
	
    }
    
    public BlockPosEntry getBottomLogFrom(BlockPosEntry entry){
	
	int modifierY = 0;
	BlockPosEntry result = entry.clone();
	
	while(TreeDictionary.getTreeEntryFromLog(new BlockIDEntry(world.getBlockId(result.xCoord, result.yCoord, result.zCoord))) != null){
	    
	    modifierY--;
	    result.setyCoord(result.getyCoord() - modifierY);
	    
	}
	
	return result;
	
    }

}
