package vivadaylight3.myrmecology.common.handler;

import vivadaylight3.myrmecology.client.gui.GuiAntFarm;
import vivadaylight3.myrmecology.common.container.ContainerAntFarm;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class MyrmecologyGuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		switch(ID){
			
			case 1:
				return new ContainerAntFarm(player.inventory, (TileEntityAntFarm) tileEntity);
			
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		switch(ID){
			
			case 1:
				return new GuiAntFarm(player.inventory, (TileEntityAntFarm) tileEntity, world, x, y, z);
			
		}
		
		return null;
	
	}

}
