package vivadaylight3.myrmecology.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import vivadaylight3.myrmecology.common.lib.InfuserRecipe;
import vivadaylight3.myrmecology.common.tileentity.TileEntityInfuser;

public class ContainerInfuser extends Container {

    public static int numRows = 3;
    public static int numCols = 5;

    private InventoryPlayer inventory;
    private TileEntityInfuser tileEntity;
    
    public static final int INVENTORY_SIZE = numRows * numCols + 9;

    public ContainerInfuser(InventoryPlayer inventory, TileEntityInfuser tile) {

	this.inventory = inventory;
	this.tileEntity = tile;
	
	int slotID = 0;
	
	// The recipe slots must start on 0
	// TODO
	for (int i = 0; i < (INVENTORY_SIZE - (numRows * numCols) / 3); i++) {
	    for (int j = 0; j < (INVENTORY_SIZE - (numRows * numCols) / 3); j++) {

		addSlotToContainer(new SlotSizeable(this.tileEntity, slotID,
			62 + j * 18, 5 + i * 18, 64));
		slotID++;

	    }
	}

	for (int i = 0; i < numRows; i++) {
	    for (int j = 0; j < numCols; j++) {

		addSlotToContainer(new SlotSizeable(this.tileEntity, slotID,
			62 + j * 18, 17 + i * 18, 64));
		slotID++;

	    }
	}

	addSlotToContainer(new SlotSizeable(this.tileEntity, slotID, 8, 17, 1));

	bindPlayerInventory(inventory);

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
    public boolean canInteractWith(EntityPlayer entityplayer) {
	// TODO Auto-generated method stub
	return false;
    }

}
