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
	
	System.out.println("aiCarpenter star");

	if (this.theAnt.getHasGoneTo()) {
	    
	    System.out.println("aiCarpenter star -: hasgone == true");

	    ArrayList<BlockEntry> list = Environment.getBlocksInRadius(world,
		    (int) this.theAnt.getPosX(), (int) this.theAnt.getPosY(),
		    (int) this.theAnt.getPosZ(), 10, Block.wood.blockID);

	    world.setBlockToAir(list.get(0).xCoord, list.get(0).yCoord,
		    list.get(0).zCoord);
	    this.theAnt.setShouldGoTo(true);
	    this.theAnt.setHasGoneTo(false);
	    this.theAnt.setGoToX((int) this.theAnt.getHomeX());
	    this.theAnt.setGoToY((int) this.theAnt.getHomeY());
	    this.theAnt.setGoToZ((int) this.theAnt.getHomeZ());

	} else {
	    
	    System.out.println("aiCarpenter star -: hasgone != true");

	    ArrayList<BlockEntry> list = Environment.getBlocksInRadius(world,
		    (int) this.theAnt.getPosX(), (int) this.theAnt.getPosY(),
		    (int) this.theAnt.getPosZ(), 10, Block.wood.blockID);

	    if (list != null) {

		if (list.get(0) != null) {
		    
		    System.out.println("aiCarpenter star -: list[0] != null");

		    this.theAnt.setShouldGoTo(true);
		    this.theAnt.setHasGoneTo(false);
		    this.theAnt.setGoToX(list.get(0).xCoord);
		    this.theAnt.setGoToY(list.get(0).yCoord);
		    this.theAnt.setGoToZ(list.get(0).zCoord);

		}

	    }

	}

    }

    @Override
    public void updateTask() {

	this.startExecuting();

    }

}
