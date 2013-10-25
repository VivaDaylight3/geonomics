package vivadaylight3.myrmecology.api;

import net.minecraft.world.World;

public interface IEntityAnt {

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

}
