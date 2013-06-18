package vivadaylight3.myrmecology.common.container;

import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerAntFarm extends Container
{
	private TileEntityAntFarm tileEntity;
	
	public ContainerAntFarm(InventoryPlayer par1InventoryPlayer, TileEntityAntFarm tile)
	{
		this.tileEntity = tile;
		// 0, horisontal, vertical. 9 pixels between each slot
		// Top row
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 0, 5, 13));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 1, 24, 13));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 2, 42, 13));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 3, 60, 13));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 4, 78, 13));
		
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 5, 6, 31));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 6, 24, 31));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 7, 42, 31));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 8, 60, 31));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 9, 78, 31));

		// Bottom row
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 10, 6, 48));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 11, 24, 48));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 12, 42, 48));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 13, 60, 48));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 14, 78, 48));
		
		// ant and food slots
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 15, 111, 31));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 16, 147, 31));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 17, 129, 10));
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 18, 129, 60));

		int var3;

		tileEntity.playersUsing.add(par1InventoryPlayer.player);
	}
	
	@Override
	public void onCraftGuiClosed(EntityPlayer entityplayer)
	{
		super.onCraftGuiClosed(entityplayer);
		tileEntity.playersUsing.remove(entityplayer);
	}
	

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return false;
	}

}
