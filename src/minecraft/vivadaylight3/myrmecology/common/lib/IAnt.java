package vivadaylight3.myrmecology.common.lib;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public interface IAnt {
    
    /**
     * Gets the ant species' name to be displayed in-game
     * 
     * @return String
     */
    public String getSpeciesName();
    
    /**
     * Gets the ant species' name to be used internally i.e for texture names
     * should be lowercase
     * 
     * @return String
     */
    public String getSpeciesSubName();
    
    /**
     * Gets the ant species' binomial name (e.g. Atta Colombica)
     * 
     * @param meta
     * @return String
     */
    public String getSpeciesBinomialName();
    
    /**
     * Gets the names of each type of ant in the order of the Queen, Drone,
     * Worker and Larva ants
     * 
     * @return String
     */
    public String[] getTypeNames();
    
    /**
     * Gets the ant species' biomes in which they can breed and perform
     * their behaviour
     * 
     * @return BiomeGenBase[] (null to use in all biomes)
     */
    public BiomeGenBase[] getAntBiomes();
    
    /**
     * Returns true if the ant species can be found in ant hills
     * 
     * @return boolean
     */
    public boolean isHillAnt();
    
    /**
     * Gets the ant species' fertility, one element from the returned array will be chosen
     * 
     * @return int
     */
    public int getFertility();
    
    /**
     * Returns true if the ant is winged (i.e can perform its behaviour and breed in the rain)
     * @return
     */
    public boolean getWinged();
    
    /**
     * Returns true if the ant can peform its behaviour and breed during the night.
     * @return
     */
    public boolean getNocturnal();
    
    /**
     * Gets the chance that the ant species' fertility will be decreased or
     * increased by one, i.e 4 is a 4 out of 10 chance et.c
     * 
     * @return int
     */
    @Deprecated
    public int getFertilityChance();
    
    /**
     * Gets the ant species' lifetime in ticks, use the
     * Properties.getTicksFromMinutes(minutes) method to convert minutes to
     * ticks
     * 
     * @return int
     */
    public int getLifetime();
    
    /**
     * Returns true if the species can eat sweet food, i.e cake and sugar
     * 
     * @return boolean
     */
    public boolean eatsSweet();
    
    /**
     * Returns true if the species can eat savoury food, i.e bread and mushroom
     * soup
     * 
     * @return boolean
     */
    public boolean eatsSavoury();
    
    /**
     * Returns true if the species can eat meat food, i.e porkchops and steak
     * 
     * @return boolean
     */
    public boolean eatsMeat();
    
    /**
     * Returns true if the species can eat ant larvae
     * 
     * @return boolean
     */
    public boolean eatsLarvae();
    
    /**
     * Gets called when the ant can perform its behaviour (e.g harvesting crops)
     * from an ant farm
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     */
    public void performBehaviour(World world, int x, int y, int z);

    /**
     * Gets the time in ticks that it takes to incubate the ant's larvae
     * @return int
     */
    public int getMaturingTime();
    
}
