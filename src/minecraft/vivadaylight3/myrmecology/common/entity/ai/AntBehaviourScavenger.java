package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;

public class AntBehaviourScavenger extends EntityAIAntBehaviour {

    public AntBehaviourScavenger(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	super(parEntityAnt, parWorld, parPathFinder);
    }

    @Override
    public void updateTask() {

	this.startExecuting();

    }

    @Override
    public void startExecuting() {

	ArrayList<EntityItem> list = Environment.getEntityItemsInRadius(world,
		getPosX(), getPosY(), getPosY(), 10);
	
	


    }

}
