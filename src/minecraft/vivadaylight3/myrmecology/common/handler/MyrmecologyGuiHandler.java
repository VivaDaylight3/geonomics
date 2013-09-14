package vivadaylight3.myrmecology.common.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.client.gui.GuiAntFarm;
import vivadaylight3.myrmecology.client.gui.GuiAntopedia;
import vivadaylight3.myrmecology.client.gui.GuiIncubator;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.inventory.ContainerAntFarm;
import vivadaylight3.myrmecology.common.inventory.ContainerAntopedia;
import vivadaylight3.myrmecology.common.inventory.ContainerIncubator;
import vivadaylight3.myrmecology.common.inventory.InventoryItem;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.common.network.IGuiHandler;

public class MyrmecologyGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
	    int x, int y, int z) {
	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

	switch (ID) {

	case Register.GUI_ID_ANTFARM:
	    return new ContainerAntFarm(player.inventory,
		    (TileEntityAntFarm) tileEntity);

	case Register.GUI_ID_INCUBATOR:
	    return new ContainerIncubator(player.inventory,
		    (TileEntityIncubator) tileEntity);

	case Register.GUI_ID_ANTOPEDIA:
	    return new ContainerAntopedia(new InventoryItem(
		    player.getHeldItem()), player);

	}

	return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
	    int x, int y, int z) {
	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

	switch (ID) {

	case Register.GUI_ID_ANTFARM:
	    return new GuiAntFarm(player.inventory,
		    (TileEntityAntFarm) tileEntity, world, x, y, z);

	case Register.GUI_ID_INCUBATOR:
	    return new GuiIncubator(player.inventory,
		    (TileEntityIncubator) tileEntity, world, x, y, z);

	case Register.GUI_ID_ANTOPEDIA:

	    InventoryItem inventory = new InventoryItem(player.getHeldItem());
	    ContainerAntopedia container = new ContainerAntopedia(inventory,
		    player);
	    return new GuiAntopedia(container, player);

	}

	return null;

    }

}
