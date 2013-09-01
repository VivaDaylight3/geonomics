package vivadaylight3.myrmecology.common.inventory;

import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerIncubator extends Container {
    private TileEntityIncubator tileEntity;

    private static int numRows = 3;
    private static int numColumns = 5;

    private int slotID = 0;

    public ContainerIncubator(InventoryPlayer par1InventoryPlayer,
	    TileEntityIncubator tileEntity2) {
	this.tileEntity = tileEntity2;

	for (int i = 0; i < numRows; i++) {
	    for (int j = 0; j < numColumns; j++) {

		addSlotToContainer(new Slot(this.tileEntity, slotID,
			62 + j * 18, 17 + i * 18));

		slotID++;

	    }
	}

	bindPlayerInventory(par1InventoryPlayer);

	tileEntity.playersUsing.add(par1InventoryPlayer.player);
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

    public static int getQueenSlot() {

	return 15;

    }

    public static int getDroneSlot() {

	return 16;

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
	return false;
    }

    public static int getInventorySize() {

	int result = (numRows * numColumns) + 4;
	return result;

    }

}
