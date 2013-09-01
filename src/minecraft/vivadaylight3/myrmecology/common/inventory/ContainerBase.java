package vivadaylight3.myrmecology.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBase extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
	return true;
    }

}
