package vivadaylight3.myrmecology.common.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.client.gui.GuiAntChest;
import vivadaylight3.myrmecology.client.gui.GuiAntFarm;
import vivadaylight3.myrmecology.client.gui.GuiIncubator;
import vivadaylight3.myrmecology.client.gui.GuiMyrmopaedia;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.inventory.ContainerAntChest;
import vivadaylight3.myrmecology.common.inventory.ContainerAntFarm;
import vivadaylight3.myrmecology.common.inventory.ContainerIncubator;
import vivadaylight3.myrmecology.common.inventory.ContainerMyrmopaedia;
import vivadaylight3.myrmecology.common.inventory.InventoryItem;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

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

	case Register.GUI_ID_MYRMOPAEDIA:
	    return new ContainerMyrmopaedia(new InventoryItem(
		    player.getHeldItem()), player);

	case Register.GUI_ID_ANTCHEST:
	    return new ContainerAntChest(player.inventory,
		    (TileEntityAntChest) tileEntity);

	}

	return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
	    int x, int y, int z) {
	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

	if (tileEntity == null) {

	}

	switch (ID) {

	case Register.GUI_ID_ANTFARM:
	    return new GuiAntFarm(player, (TileEntityAntFarm) tileEntity,
		    world, x, y, z);

	case Register.GUI_ID_INCUBATOR:
	    return new GuiIncubator(player, player.inventory,
		    (TileEntityIncubator) tileEntity, world, x, y, z);

	case Register.GUI_ID_MYRMOPAEDIA:

	    InventoryItem inventory = new InventoryItem(player.getHeldItem());
	    ContainerMyrmopaedia container = new ContainerMyrmopaedia(
		    inventory, player);
	    return new GuiMyrmopaedia(container, player);

	case Register.GUI_ID_ANTCHEST:
	    return new GuiAntChest(player.inventory,
		    (TileEntityAntChest) tileEntity);

	}

	return null;

    }

}
