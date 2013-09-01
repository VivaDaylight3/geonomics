package vivadaylight3.myrmecology.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.api.ItemAnt;

public class SlotInventoryItem extends Slot {

    private int[] validItemIDs;

    public SlotInventoryItem(IInventory par1iInventory, int[] itemIDs,
	    int par2, int par3, int par4) {
	super(par1iInventory, par2, par3, par4);

	this.validItemIDs = itemIDs;

    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {

	for (int k = 0; k < validItemIDs.length; k++) {

	    if (itemstack.getItem().itemID == this.validItemIDs[k]) {

		return true;

	    }

	}

	return false;

    }

}
