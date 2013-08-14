package vivadaylight3.myrmecology.common.container;

import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerAntFarm extends Container {
    private TileEntityAntFarm tileEntity;
    
    public ContainerAntFarm(InventoryPlayer par1InventoryPlayer,
	    TileEntityAntFarm tileEntity2) {
	this.tileEntity = tileEntity2;
	// Args: slotID, horisontal, vertical. 18 pixels between each slot
	// Top row
	this.addSlotToContainer(new Slot(tileEntity2, 0, 5, 13));
	this.addSlotToContainer(new Slot(tileEntity2, 1, 24, 13));
	this.addSlotToContainer(new Slot(tileEntity2, 2, 42, 13));
	this.addSlotToContainer(new Slot(tileEntity2, 3, 60, 13));
	this.addSlotToContainer(new Slot(tileEntity2, 4, 78, 13));
	
	this.addSlotToContainer(new Slot(tileEntity2, 5, 6, 31));
	this.addSlotToContainer(new Slot(tileEntity2, 6, 24, 31));
	this.addSlotToContainer(new Slot(tileEntity2, 7, 42, 31));
	this.addSlotToContainer(new Slot(tileEntity2, 8, 60, 31));
	this.addSlotToContainer(new Slot(tileEntity2, 9, 78, 31));
	
	// Bottom row
	this.addSlotToContainer(new Slot(tileEntity2, 10, 6, 48));
	this.addSlotToContainer(new Slot(tileEntity2, 11, 24, 48));
	this.addSlotToContainer(new Slot(tileEntity2, 12, 42, 48));
	this.addSlotToContainer(new Slot(tileEntity2, 13, 60, 48));
	this.addSlotToContainer(new Slot(tileEntity2, 14, 78, 48));
	
	// Ant and food slots
	// Queen slot
	this.addSlotToContainer(new Slot(tileEntity2, 15, 111, 31));
	// Drone slot
	this.addSlotToContainer(new Slot(tileEntity2, 16, 147, 31));
	// Top food slot
	this.addSlotToContainer(new Slot(tileEntity2, 17, 129, 10));
	// Bottom food slot
	this.addSlotToContainer(new Slot(tileEntity2, 18, 129, 60));
		
	int i;
	
	for (i = 0; i < 3; ++i) {
	    for (int j = 0; j < 9; ++j) {
		this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9
			+ 9, 8 + j * 18 - 1, 84 + i * 18));
	    }
	}
	
	for (i = 0; i < 9; ++i) {
	    this.addSlotToContainer(new Slot(par1InventoryPlayer, i,
		    8 + i * 18 - 1, 142));
	}
	
	// this.addSlotToContainer(new SlotAntFarm(par1InventoryPlayer.player,
	// tileEntity2, 19, field_94535_f, field_94535_f));
	
	int var3;
	
	tileEntity.playersUsing.add(par1InventoryPlayer.player);
    }
    
    public static int getQueenSlot() {
	
	return 15;
	
    }
    
    public static int getDroneSlot() {
	
	return 16;
	
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
	return true;
    }
    
}
