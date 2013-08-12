package vivadaylight3.myrmecology.common.lib;

import vivadaylight3.myrmecology.common.item.ItemAnt;
import vivadaylight3.myrmecology.common.item.ants.AntForest;

public class Ants {

    public static final String[] antNames = { "Black", "Mangrove", "Desert",
	    "Argentine", "Field", "Red", "Hibernus", "Amber", "Common",
	    "Harvester", "Carpenter", "Mound", "Barbaric", "Odorous",
	    "Hostile", "Plentiful", "Dredger", "Scavenger", "Cultivator",
	    "Sprouter", "Fungal" };
    
    public static final int[] typeMeta = new int[] {0, 1, 2, 3};
    
    public static int getMetaQueen(){
	
	return typeMeta[0];
	
    }
    
    public static int getMetaDrone(){
	
	return typeMeta[1];
	
    }
    
    public static int getMetaWorker(){
	
	return typeMeta[2];
	
    }
    
    public static int getMetaLarva(){
	
	return typeMeta[3];
	
    }
        
}