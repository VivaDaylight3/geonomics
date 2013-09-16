package vivadaylight3.myrmecology.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSizeable extends Slot {
    
    private int maxStackSize;
    private int slotID;

    public SlotSizeable(IInventory par1iInventory, int par2, int par3, int par4, int stackSize) {
	super(par1iInventory, par2, par3, par4);
	
	this.maxStackSize = stackSize;
	this.slotID = par2;
	
    }
    
    @Override
    public int getSlotStackLimit() {
        return this.maxStackSize;
    }
    
    @Override
    public ItemStack getStack()
    {
	
	if(this.inventory != null){
	
	    return this.inventory.getStackInSlot(this.slotID);
	    
	}
	
	return null;
	
    }
    
    @Override
    public void putStack(ItemStack par1ItemStack)
    {
	
	if(this.inventory != null){
	
	    this.inventory.setInventorySlotContents(this.slotID, par1ItemStack);
	    this.onSlotChanged();
        
	}
    }

}
