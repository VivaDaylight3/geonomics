package vivadaylight3.myrmecology.common.lib;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.common.item.ItemAnt;

public interface IAntHill {
    
    /**
     * Gets the ant that belongs to this ant hill
     * 
     * @return extends ItemAnt
     */
    public ItemAnt getAnt();
    
    /**
     * Gets the biomes in which this hill can generate.
     * 
     * @return BiomeGenBase[]
     */
    public BiomeGenBase[] getHillBiomes();
    
    /**
     * Gets the in-game name used for the ant hill
     * 
     * @return String
     */
    public String getHillName();
    
    /**
     * Gets the internal name used for the ant hill
     * 
     * @return String
     */
    public String getHillSubName();
    
    /**
     * Returns true if the hill uses Myrmecology's own world generator
     * 
     * @param meta
     * @return boolean
     */
    public boolean usesNativeGeneration();
    
    /**
     * Returns the block IDs that need to be touching the hill in order for it
     * to be generated (only use if usesNativeGenerartion returns true)
     * 
     * @return int[] (null if none are required)
     */
    public int[] getRequiredTouchingBlocks();
    
    /**
     * Gets the amount of larvae to drop when extracting from the ant hill
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @return int
     */
    public int getDropQuantity();
    
}
