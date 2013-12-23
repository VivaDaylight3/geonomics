package vivadaylight3.myrmecology.api.entity.ai;

import vivadaylight3.myrmecology.api.IEntityAnt;
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

    /**
     * Returns the type of ant behaviour
     * 
     * @return EnumAntAIType
     */
    public EnumAntAIType getAIType() {

	return null;

    }

    @Override
    public boolean shouldExecute() {

	return true;
    }

    public double getPosX() {

	return this.theAnt.getPosX();

    }

    public double getPosY() {

	return this.theAnt.getPosY();

    }

    public double getPosZ() {

	return this.theAnt.getPosZ();

    }

}
