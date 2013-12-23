package vivadaylight3.myrmecology.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerIncubator extends Container {

    protected TileEntityIncubator tileEntity;

    private static int numRows = 3;
    private static int numColumns = 5;

    private static int inventorySize = numRows * numColumns + 3;
    public static final int stackLimit = 64;

    public ContainerIncubator(InventoryPlayer inventoryPlayer,
	    TileEntityIncubator tileEntity2) {

	this.tileEntity = tileEntity2;

	int slotID = 0;
	// args: tileEntity, slotID, horisontal, vertical

	// Larva Slot
	addSlotToContainer(new SlotSizeable(this.tileEntity, slotID, 27, 35, 1));
	slotID++;
	addSlotToContainer(new SlotSizeable(this.tileEntity, slotID, 8, 35, 1));

	for (int i = 0; i < numRows; i++) {
	    for (int j = 0; j < numColumns; j++) {

		slotID++;
		addSlotToContainer(new SlotSizeable(this.tileEntity, slotID,
			62 + j * 18, 17 + i * 18, stackLimit));

	    }
	}

	slotID++;
	addSlotToContainer(new SlotSizeable(this.tileEntity, slotID, 8, 17, 1));

	bindPlayerInventory(inventoryPlayer);
    }

    public static int getInventorySize() {

	return inventorySize;

    }

    public static int getLarvaSlot() {

	return 0;

    }

    public static int getFoodSlot() {

	return 1;

    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 9; j++) {
		addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
			8 + j * 18, 84 + i * 18));
	    }
	}

	for (int i = 0; i < 9; i++) {
	    addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
	}
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {

	return this.tileEntity.isUseableByPlayer(player);

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
	ItemStack itemstack = null;
	Slot slot = (Slot) this.inventorySlots.get(par2);

	if (slot != null && slot.getHasStack()) {
	    ItemStack itemstack1 = slot.getStack();
	    itemstack = itemstack1.copy();

	    if (par2 < this.numRows * 9) {
		if (!this.mergeItemStack(itemstack1, this.numRows * 9,
			this.inventorySlots.size(), true)) {
		    return null;
		}
	    } else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9,
		    false)) {
		return null;
	    }

	    if (itemstack1.stackSize == 0) {
		slot.putStack((ItemStack) null);
	    } else {
		slot.onSlotChanged();
	    }
	}

	return itemstack;
    }

}
