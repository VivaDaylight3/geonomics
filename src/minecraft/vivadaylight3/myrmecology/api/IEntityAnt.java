package vivadaylight3.myrmecology.api;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface IEntityAnt {

    ItemStack[] inventory = new ItemStack[1];

    TileEntity homeBlockTileEntity = null;

    double getPosX();

    double getPosY();

    double getPosZ();

    void moveEntityTo(int x, int y, int z);

    int getTicks();

    void dropItemStack(ItemStack stack);

    void moveEntityTo(double posX, double posY, double posZ);
}
