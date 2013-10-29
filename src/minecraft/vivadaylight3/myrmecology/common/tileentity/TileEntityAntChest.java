package vivadaylight3.myrmecology.common.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityAntChest extends TileEntityChest implements IInventory {

    private ItemStack[] chestContents = new ItemStack[36];

    public TileEntityAntChest() {
    }

    public TileEntityAntChest(int par1) {
	super(par1);
    }

    public ItemStack[] getContents() {

	return this.chestContents;

    }

}
