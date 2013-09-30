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
     * @param IEntityAnt
     * @param World
     * @param PathNavigate
     */
    public EntityAIAntBehaviour(IEntityAnt parEntityAnt, World parWorld, PathNavigate parPathFinder){
	
	this.theAnt = parEntityAnt;
	this.world = parWorld;
	this.pathFinder = parPathFinder;
	
    }
    
    @Override
    public boolean shouldExecute() {
	return this.theAnt.canPerformBehaviour();
    }
    
    @Override
    public void startExecuting(){
	
	this.theAnt.startPerformingBehaviour();
	
    }
    
    @Override
    public void updateTask(){
	
	this.theAnt.updateBehaviour();
	
    }
    
    @Override
    public void resetTask(){
	
	this.theAnt.resetBehaviour();
	
    }

}
