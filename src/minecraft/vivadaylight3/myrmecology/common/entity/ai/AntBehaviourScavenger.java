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
    
    private String state = "none";
    private EntityItem targetItem;
    private TileEntity targetChest;
    private String flag;

    public AntBehaviourScavenger(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	super(parEntityAnt, parWorld, parPathFinder);
    }

    @Override
    public boolean shouldExecute() {
	
	targetChest = this.theAnt.homeBlockTileEntity;
		
	if(state.equalsIgnoreCase("none") && nearestItemExists(searchForNearestItem())){
	    	    
	    targetItem = (EntityItem) searchForNearestItem();
	    
	    flag = "flag:itemPickup";
	    
	    state = "itemPickup";
	    
	    return true;
	    
	}else if(state.equalsIgnoreCase("itemPickup") && targetItem != null){
	    
	    flag = "flag:itemPickup";
	    
	    return true;
	    
	}else if(state.equalsIgnoreCase("itemDropOff") && targetChest != null){
	    
	    flag = "flag:itemDropOff";
	    
	    return true;
	    
	}else{
	    
	    flag = "flag:none";
	    state = "none";
	    
	    return false;
	    
	}

    }

    @Override
    public void updateTask() {
	
	if(flag.equalsIgnoreCase("flag:itemPickup")){
	    
	    pickUpItem();
	    
	}else if(flag.equalsIgnoreCase("flag:itemDropOff") || state.equalsIgnoreCase("itemDropOff")){
	    	    
	    dropOffItem();
	    
	}
	
    }

    @Override
    public void startExecuting() {
	
	this.updateTask();
	
    }
    
    private Entity searchForNearestItem(){
	
	List list = Environment.getEntityItemsInRadius(world, this.getPosX(), this.getPosY(), this.getPosZ(), 20);
	
	return Environment.getNearestEntityFrom(list, this.getPosX(), this.getPosY(), this.getPosZ(), 20);
	
    }
    
    private boolean nearestItemExists(Entity entity){
	
	if(entity != null){
	    
	    if(entity instanceof EntityItem){
		
		if(!entity.isDead){
		
		    return true;
		
		}
		
	    }
	    
	}
	
	return false;
	
    }
    
    private void pickUpItem(){
		
	if(Environment.inventoryCanHold(targetItem.getEntityItem(), this.theAnt.inventory, 64)){
	    	
	    this.theAnt.moveEntityTo(targetItem.posX, targetItem.posY, targetItem.posZ);
	    
	    int itemX = (int) Math.ceil(targetItem.posX), itemY = (int) Math.ceil(targetItem.posY), itemZ  = (int) Math.ceil(targetItem.posZ);
	    
	    if(targetItem.posX > getPosX()){
		
		itemY = (int) Math.floor(targetItem.posX);
		
	    }
	    
	    if(targetItem.posY > getPosY()){
		
		itemY = (int) Math.floor(targetItem.posY);
		
	    }
	    
	    if(targetItem.posY > getPosZ()){
		
		itemZ = (int) Math.floor(targetItem.posZ);
		
	    }
	    
	    if(Environment.coordinateIsCloseTo(itemX, itemY, itemZ, getPosX(), getPosY(), getPosZ(), 2)){
						
		Environment.addItemStackToInventory(targetItem.getEntityItem(), this.theAnt.inventory, 64, null);
				
		targetItem.setDead();
				
		state = "itemDropOff";
		
		flag = "flag:itemDropOff";
		
	    }
	    
	}
	
    }
    
    private void dropOffItem(){
	
	this.
	theAnt
	.moveEntityTo(
		targetChest.
		xCoord, 
		targetChest.
		yCoord, 
		targetChest.
		zCoord);
	
	if(Environment.coordinateIsCloseTo(getPosX(), getPosY(), getPosZ(), targetChest.xCoord, targetChest.yCoord, targetChest.zCoord, 1)){
	    
	    if(Environment.inventoryCanHold(this.theAnt.inventory[0], this.theAnt.inventory, 64)){
		
		Environment.addItemStackToInventory(this.theAnt.inventory[0], this.theAnt.inventory, 64, targetChest);
		
		this.state = "none";
		
		flag = "flag:none";
		
	    }
	    
	}
	
    }
	
}
