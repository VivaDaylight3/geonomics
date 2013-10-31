package vivadaylight3.myrmecology.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.api.item.ItemAnt;

public class SlotInventoryItem extends Slot {

    public SlotInventoryItem(IInventory par1iInventory, int par2, int par3,
	    int par4) {
	super(par1iInventory, par2, par3, par4);

    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {

	return true;

    }

}
