package vivadaylight3.myrmecology.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;

public class EntityAIAntBehaviour extends EntityAIBase {

    private World world;
    private IEntityAnt theAnt;
    private PathNavigate pathFinder;

    /**
     * The AI used for ant behaviours
     * 
     * @param IEntityAnt
     * @param World
     * @param PathNavigate
     */
    public EntityAIAntBehaviour(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {

	this.theAnt = parEntityAnt;
	this.world = parWorld;
	this.pathFinder = parPathFinder;

    }

    @Override
    public boolean shouldExecute() {
	
	System.out.println("AI shouldExecute");
	
	return true;
    }

    @Override
    public void startExecuting() {
	
	System.out.println("AI startExecuting");

	//this.theAnt.startPerformingBehaviour();

    }

    @Override
    public void updateTask() {
	
	System.out.println("AI updateTask");

	this.theAnt.updateBehaviour();

    }

    @Override
    public void resetTask() {
	
	System.out.println("AI resetTask");

	//this.theAnt.resetBehaviour();

    }

}
