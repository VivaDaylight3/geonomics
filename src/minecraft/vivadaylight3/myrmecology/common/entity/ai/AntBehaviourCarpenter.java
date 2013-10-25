package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.common.lib.BlockEntry;
import vivadaylight3.myrmecology.common.lib.Environment;

public class AntBehaviourCarpenter extends EntityAIAntBehaviour {

    public AntBehaviourCarpenter(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	super(parEntityAnt, parWorld, parPathFinder);
		
    }
    
    @Override
    public void startExecuting() {
	
	this.theAnt.moveEntityTo((int)this.theAnt.getPosX() + 1, (int)this.theAnt.getPosY(), (int)this.theAnt.getPosZ());
	
	ArrayList<BlockEntry> list = Environment.getBlocksInRadius(world, (int)this.theAnt.getPosX(), (int)this.theAnt.getPosY(), (int)this.theAnt.getPosZ(), 10, Block.wood.blockID);
	
	for(int k = 0; k < list.size(); k++){
	    
	    this.theAnt.moveEntityTo(list.get(k).xCoord, list.get(k).yCoord, list.get(k).zCoord);
	    
	    this.world.setBlock(list.get(k).xCoord, list.get(k).yCoord, list.get(k).zCoord, Block.bedrock.blockID);
	    
	}

    }
    
    @Override
    public void updateTask() {
	
	this.startExecuting();

    }

}
