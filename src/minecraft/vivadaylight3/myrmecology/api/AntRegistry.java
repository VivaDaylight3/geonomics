package vivadaylight3.myrmecology.api;

import java.util.ArrayList;

public class AntRegistry {
    
    private static ArrayList<AntEntry> entryList = new ArrayList<AntEntry>();

    public static int newAntEntry(AntEntry entry) {
	
	entryList.add(entry);
	return entryList.indexOf(entry);
	
    }

}
