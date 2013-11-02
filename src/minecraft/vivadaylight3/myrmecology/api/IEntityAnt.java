package vivadaylight3.myrmecology.api;

import net.minecraft.item.ItemStack;

public interface IEntityAnt {

    ItemStack[] inventory = new ItemStack[1];
    
    void newDestination(int x, int y, int z);
    
    boolean isAtDestination(int distance);

    double getPosX();

    double getPosY();

    double getPosZ();

    int getGoToX();

    int getGoToY();

    int getGoToZ();

    void setGoToX(int x);

    void setGoToY(int y);

    void setGoToZ(int z);

    double getHomeX();

    double getHomeY();

    double getHomeZ();

    void setHomeX(int x);

    void setHomeY(int y);

    void setHomeZ(int z);

    boolean getShouldGoTo();

    boolean getHasGoneTo();

    void setShouldGoTo(boolean bool);

    void setHasGoneTo(boolean bool);

    void moveEntityTo(int x, int y, int z);

    int getTicks();

    void dropItemStack(ItemStack stack);

}
