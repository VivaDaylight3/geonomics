package vivadaylight3.myrmecology.api;

import vivadaylight3.myrmecology.common.lib.Environment;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;

public class EntityAIAntGoTo extends EntityAIBase {
    
    protected World world;
    protected IEntityAnt theAnt;
    protected PathNavigate pathFinder;

    public EntityAIAntGoTo(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	
	this.theAnt = parEntityAnt;
	this.world = parWorld;
	this.pathFinder = parPathFinder;
	this.setMutexBits(26);
    }

    @Override
    public boolean shouldExecute() {
	
	System.out.println("aiGoto should");
	
	return this.theAnt.getShouldGoTo();
    }

    @Override
    public void startExecuting() {
	
	System.out.println("aiGoto start");

	if (this.theAnt.getShouldGoTo()) {
	    
	    System.out.println("aiGoto start -: shouldGo == true");

	    this.theAnt.moveEntityTo(this.theAnt.getGoToX(),
		    this.theAnt.getGoToY(), this.theAnt.getGoToZ());

	    if (Environment.coordinateIsCloseTo((int)this.theAnt.getPosX(), (int)this.theAnt.getPosY(),
		    (int) this.theAnt.getPosZ(), this.theAnt.getGoToX(), this.theAnt.getGoToY(),
		    this.theAnt.getGoToZ(), 1)) {
		
		System.out.println("aiGoto start -: isClose == true");

		this.theAnt.setHasGoneTo(true);
		this.theAnt.setShouldGoTo(false);

	    }

	}

    }

    @Override
    public void updateTask() {

	this.startExecuting();

    }

}
