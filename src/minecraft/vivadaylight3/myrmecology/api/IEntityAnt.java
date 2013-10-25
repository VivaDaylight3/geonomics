package vivadaylight3.myrmecology.api;

import net.minecraft.world.World;

public interface IEntityAnt {

    double getPosX();

    double getPosY();

    double getPosZ();
    
    void moveEntityTo(int x, int y, int z);


}
