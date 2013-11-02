package vivadaylight3.myrmecology.common.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
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

	EntityItem item = null;

	List list = Environment.getEntityItemsInRadius(world, getPosX(),
		getPosY(), getPosY(), 10);

	Entity entity = Environment.getNearestEntityFrom(list, this.getPosX(),
		this.getPosY(), this.getPosZ(), 10);

	if (entity != null) {

	    if (entity instanceof EntityItem) {

		item = (EntityItem) entity;

	    }
	    
	}

	if (this.theAnt.getObjective() == null) {

		if (item != null) {

		    this.theAnt.newObjective("itemPickup", item.posX,
			    item.posY, item.posZ, 0);

		}

	} else if (this.theAnt.getObjective().isAtObjective(1)) {

	    if (this.theAnt.getObjective().getTargetName().equals("itemPickup") && item != null) {
		
		if(Environment.inventoryCanHold(item.getEntityItem(), this.theAnt.inventory, 64)){
		
		    Environment.addItemStackToInventory(item.getEntityItem(), this.theAnt.inventory, 64, null);
		    
		    this.theAnt.newObjective("itemDropoff", this.theAnt.getHomeX(), this.theAnt.getHomeY(), this.theAnt.getHomeZ(), 0);
		
		}

	    }else if(this.theAnt.getObjective().getTargetName().equals("itemDropoff")){
		
		TileEntity tile = world.getBlockTileEntity((int)this.theAnt.getHomeX(), (int)this.theAnt.getHomeY(), (int)this.theAnt.getHomeZ());
		
		if(tile != null){
		    
		    if(tile instanceof TileEntityAntChest){
			
			if(Environment.inventoryCanHold(item.getEntityItem(), ((TileEntityAntChest) tile).getContents(), ((TileEntityAntChest) tile).getInventoryStackLimit())){
			    
			    Environment.addItemStackToInventory(item.getEntityItem(), ((TileEntityAntChest) tile).getContents(), ((TileEntityAntChest) tile).getInventoryStackLimit(), tile);
			    this.theAnt.clearObjective();
			    
			}
			
		    }
		    
		}
		
	    }

	}

    }

}
