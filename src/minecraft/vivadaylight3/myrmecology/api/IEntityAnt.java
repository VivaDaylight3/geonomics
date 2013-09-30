package vivadaylight3.myrmecology.api;

import net.minecraft.world.World;

public interface IEntityAnt{
    
    /**
     * Gets the ItemAnt that correponds to this entity, return null if it doesn't have one (not reccomended)
     * @return ItemAnt
     */
    public ItemAnt getAnt();
    
    /**
     * Override this and check for conditions required to perform the behaviour e.g if your ant cuts down trees, check for
     * trees around the ant
     */
    public boolean canPerformBehaviour();
    
    /**
     * Used to start the behaviour
     */
    public void startPerformingBehaviour();
    
    /**
     * Used to update the behaviour
     */
    public void updateBehaviour();
    
    /**
     * Used to reset the behaviour
     */
    public void resetBehaviour();
    
}
