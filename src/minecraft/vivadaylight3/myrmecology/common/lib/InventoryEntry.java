package vivadaylight3.myrmecology.common.lib;

import net.minecraft.inventory.IInventory;

public class InventoryEntry extends BlockEntry{
    
    private IInventory inventory;

    public InventoryEntry(IInventory inventory, int x, int y, int z, int id) {

	super(x, y, z, id);
	this.inventory = inventory;
	
    }

}
