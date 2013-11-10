package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
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

	return true;

    }

    @Override
    public void startExecuting() {

    }

    @Override
    public void updateTask() {
	
	

    }

}
