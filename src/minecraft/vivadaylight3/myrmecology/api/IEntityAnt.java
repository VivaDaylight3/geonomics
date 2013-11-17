package vivadaylight3.myrmecology.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import vivadaylight3.myrmecology.api.item.ItemAnt;

public interface IEntityAnt {

    ItemAnt getAnt();

    ItemStack[] inventory = new ItemStack[1];

    TileEntity homeBlockTileEntity = null;

    void sendBehaviourErrorMessage(EntityPlayer player);

    double getPosX();

    double getPosY();

    double getPosZ();

    void moveEntityTo(int x, int y, int z);

    int getTicks();

    void dropItemStack(ItemStack stack);

    void moveEntityTo(double posX, double posY, double posZ);
}
