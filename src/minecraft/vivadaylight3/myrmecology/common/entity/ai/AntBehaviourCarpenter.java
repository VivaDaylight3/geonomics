package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.common.lib.BlockEntry;
import vivadaylight3.myrmecology.common.lib.Environment;

public class AntBehaviourCarpenter extends EntityAIAntBehaviour {

    public AntBehaviourCarpenter(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	super(parEntityAnt, parWorld, parPathFinder);

    }

    @Override
    public void startExecuting() {
	
	

    }

    @Override
    public void updateTask() {

	this.startExecuting();

    }

}
