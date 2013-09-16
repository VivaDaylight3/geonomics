package vivadaylight3.myrmecology.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotSizeable extends Slot {
    
    private int maxStackSize;

    public SlotSizeable(IInventory par1iInventory, int par2, int par3, int par4, int stackSize) {
	super(par1iInventory, par2, par3, par4);
	
	this.maxStackSize = stackSize;
	
    }
    
    @Override
    public int getSlotStackLimit() {
        return this.maxStackSize;
    }

}
