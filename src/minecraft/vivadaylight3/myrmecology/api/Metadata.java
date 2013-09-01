package vivadaylight3.myrmecology.api;

public class Metadata {

    public static final String[] antNames = { "Black", "Mangrove", "Desert",
	    "Argentine", "Field", "Red", "Hibernus", "Amber", "Common",
	    "Harvester", "Carpenter", "Mound", "Barbaric", "Odorous",
	    "Hostile", "Plentiful", "Dredger", "Scavenger", "Cultivator",
	    "Sprouter", "Fungal" };

    /**
     * Holds the metadata values for each type of ant
     */
    public static final int[] typeMeta = new int[] { 0, 1, 2, 3 };

    /**
     * Gets the metadata value for queen ants
     * 
     * @return int
     */
    public static int getMetaQueen() {

	return typeMeta[0];

    }

    /**
     * Gets the metadata value for drone ants
     * 
     * @return int
     */
    public static int getMetaDrone() {

	return typeMeta[1];

    }

    /**
     * Gets the metadata value for worker ants
     * 
     * @return int
     */
    public static int getMetaWorker() {

	return typeMeta[2];

    }

    /**
     * Gets the metadata value for larvae ants
     * 
     * @return int
     */
    public static int getMetaLarva() {

	return typeMeta[3];

    }

}