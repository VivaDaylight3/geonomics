package vivadaylight3.myrmecology.api;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;

public class EntityAIAntBehaviour extends EntityAIBase {

    protected World world;
    protected IEntityAnt theAnt;
    protected PathNavigate pathFinder;

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
	this.setMutexBits(26);

    }

    @Override
    public boolean shouldExecute() {

	return true;
    }

    public int getPosX() {

	return (int) this.theAnt.getPosX();

    }

    public int getPosY() {

	return (int) this.theAnt.getPosY();

    }

    public int getPosZ() {

	return (int) this.theAnt.getPosZ();

    }

}
