package vivadaylight3.myrmecology.common.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.entity.ai.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.BlockEntry;
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
	
	System.out.println("shouldExecute");
	
	targetChest = Environment.getNearestTileEntityFrom(Environment.getTileEntitiesInRadius(world, getPosX(), getPosY(), getPosZ(), 10), (Entity) this.theAnt, getPosX(), getPosY(), getPosZ());
		
	if(state.equalsIgnoreCase("none") && nearestItemExists(searchForNearestItem())){
	    
	    System.out.println("shouldExecute : none");
	    	    
	    targetItem = (EntityItem) searchForNearestItem();
	    
	    flag = "flag:itemPickup";
	    
	    state = "itemPickup";
	    
	    return true;
	    
	}else if(state.equalsIgnoreCase("itemPickup") && targetItem != null){
	    
	    System.out.println("shouldExecute : itemPickup");
	    
	    flag = "flag:itemPickup";
	    
	    return true;
	    
	}else if(state.equalsIgnoreCase("itemDropOff") && antChestExists(targetChest)){
	    
	    System.out.println("shouldExecute : itemDropOff");
	    
	    flag = "flag:itemDropOff";
	    
	    return true;
	    
	}else{
	    
	    return false;
	    
	}

    }

    @Override
    public void updateTask() {
	
	if(flag.equalsIgnoreCase("flag:itemPickup")){
	    
	    System.out.println("updateTask : itemPickup");
	    
	    pickUpItem();
	    
	}else if(flag.equalsIgnoreCase("flag:itemDropOff") || state.equalsIgnoreCase("itemDropOff")){
	    
	    System.out.println("updateTask : itemDropOff");
	    	    
	    dropOffItem();
	    
	}
	
    }

    @Override
    public void startExecuting() {
	
	this.updateTask();
	
    }
    
    private Entity searchForNearestItem(){
	
	System.out.println("searchForNearestItem");
	
	List list = Environment.getEntityItemsInRadius(world, this.getPosX(), this.getPosY(), this.getPosZ(), 20);
	
	return Environment.getNearestEntityFrom(list, this.getPosX(), this.getPosY(), this.getPosZ(), 20);
	
    }
    
    private boolean nearestItemExists(Entity entity){
	
	System.out.println("nearestItemExists");
	
	if(entity != null){
	    
	    System.out.println("nearestItemExists : entity != null");
	    
	    if(entity instanceof EntityItem){
		
		System.out.println("nearestItemExists : entity != null : instanceof : ");
		
		if(!entity.isDead){
		    
		    System.out.println("nearestItemExists : entity != null : instanceof : isDead");
		
		    return true;
		
		}
		
	    }
	    
	}
	
	return false;
	
    }
    
    private void pickUpItem(){
	
	System.out.println("pickUpItem");
		
	if(Environment.inventoryCanHold(targetItem.getEntityItem(), this.theAnt.inventory, 64)){
	    
	    System.out.println("pickUpItem : canHold");
	    	
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
		
		System.out.println("pickUpItem : canHold : isClose");
						
		Environment.addItemStackToInventory(targetItem.getEntityItem(), this.theAnt.inventory, 64, null);
				
		targetItem.setDead();
				
		state = "itemDropOff";
		
		flag = "flag:itemDropOff";
		
	    }
	    
	}
	
    }
    
    private void dropOffItem(){
	
	System.out.println("dropOffItem");
			
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
	    
	    System.out.println("dropOffItem : isClose");
	    
	    if(Environment.inventoryCanHold(this.theAnt.inventory[0], this.theAnt.inventory, 64)){
		
		System.out.println("dropOffItem : isClose : canHold");
		
		((TileEntityAntChest) this.targetChest).getContents()[0] = this.theAnt.inventory[0];
		
		//Environment.addItemStackToInventory(this.theAnt.inventory[0], ((TileEntityAntChest) this.targetChest).getContents(), 64, targetChest);
		
		System.out.println("dropOffItem : isClose : canHold : addTo");
		
		//Environment.spawnItem(this.theAnt.inventory[0], world, getPosX(), getPosY(), getPosZ());
		
		this.state = "none";
		
		flag = "flag:none";
		
	    }
	    
	}
	
    }
    
    public boolean antChestExists(TileEntity te){
	
	if(te != null){
	    
	    if(te instanceof TileEntityAntChest){
		
		return true;
		
	    }
	    
	}
	
	return false;
	
    }
	
}
