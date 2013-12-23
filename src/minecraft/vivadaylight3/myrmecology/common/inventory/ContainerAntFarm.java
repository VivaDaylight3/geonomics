package vivadaylight3.myrmecology.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerAntFarm extends Container {

    protected TileEntityAntFarm tileEntity;

    private static int numRows = 3;
    private static int numColumns = 5;

    public ContainerAntFarm(InventoryPlayer inventoryPlayer,
	    TileEntityAntFarm te) {

	tileEntity = te;

	int slotID = 0;
	// args: tileEntity, slotID, horisontal, vertical

	// Drone Slot
	addSlotToContainer(new Slot(tileEntity, slotID, 26, 17 + 2 * 18));

	// Queen Slot
	slotID++;
	addSlotToContainer(new Slot(tileEntity, slotID, 26, 17));

	// Breeding Chamber slot
	slotID++;
	addSlotToContainer(new Slot(tileEntity, slotID, 44, 17 + 18));

	/*
	 * Food Slot slotID++; addSlotToContainer(new Slot(tileEntity, slotID,
	 * 8, 17 + 18));
	 * 
	 * //Food Slot slotID++; addSlotToContainer(new Slot(tileEntity, slotID,
	 * 26 + 18, 17 + 18));
	 */

	for (int i = 0; i < numRows; i++) {
	    for (int j = 0; j < numColumns; j++) {

		slotID++;
		addSlotToContainer(new Slot(tileEntity, slotID, 62 + j * 18,
			17 + i * 18));

	    }
	}

	slotID++;
	addSlotToContainer(new Slot(tileEntity, slotID, 6, 17 + 18));

	bindPlayerInventory(inventoryPlayer);
    }

    public static int getInventorySize() {

	return numRows * numColumns + 4;

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
	return tileEntity.isUseableByPlayer(player);
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
