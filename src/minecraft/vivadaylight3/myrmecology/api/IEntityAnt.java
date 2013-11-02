package vivadaylight3.myrmecology.api;

import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.entity.ai.AntObjective;

public interface IEntityAnt {

    ItemStack[] inventory = new ItemStack[1];
    
    void newObjective(String name, int x, int y, int z, int distance);
    void newObjective(String name, double posX, double posY, double posZ, int distance);
    AntObjective getObjective();
    
    boolean isAtDestination(int distance);

    double getPosX();

    double getPosY();

    double getPosZ();

    double getHomeX();

    double getHomeY();

    double getHomeZ();

    void setHomeX(int x);

    void setHomeY(int y);

    void setHomeZ(int z);

    void moveEntityTo(int x, int y, int z);

    int getTicks();

    void dropItemStack(ItemStack stack);
    void clearObjective();

}
