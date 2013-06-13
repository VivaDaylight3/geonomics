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
		// Bottom slot for battery input
		this.addSlotToContainer(new Slot(par1InventoryPlayer, 1, 33, 48));
		int var3;

		for (var3 = 0; var3 < 3; ++var3)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
		}

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
