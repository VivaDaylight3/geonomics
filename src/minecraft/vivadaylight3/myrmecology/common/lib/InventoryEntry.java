package vivadaylight3.myrmecology.common.lib;

import net.minecraft.inventory.IInventory;

public class InventoryEntry extends BlockPosEntry {

    private IInventory inventory;

    public InventoryEntry(IInventory inventory, int x, int y, int z, int id,
	    int meta) {

	super(x, y, z, id, meta);
	this.inventory = inventory;

    }

}
