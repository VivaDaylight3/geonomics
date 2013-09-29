package vivadaylight3.myrmecology.api;

import net.minecraft.world.World;

public interface IEntityAnt{
    
    public ItemAnt getAnt();
    
    /**
     * Override this and check for conditions required to perform the behaviour e.g if your ant cuts down trees, check for
     * trees around the ant
     */
    public boolean canPerformBehaviour();
    
    public void startPerformingBehaviour();
    
    public void updateBehaviour();
    
    public void resetBehaviour();
    
}
