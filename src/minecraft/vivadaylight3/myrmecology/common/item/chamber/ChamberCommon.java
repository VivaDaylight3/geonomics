package vivadaylight3.myrmecology.common.item.chamber;

import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.api.ItemBreedingChamber;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.item.ant.AntCommon;

public class ChamberCommon extends ItemBreedingChamber{

    public ChamberCommon(int par) {
	
	super(par);
	
    }
    
    @Override
    public ItemAnt getAnt(){
	
	return Register.antCommon;
	
    }
    
    @Override
    public String getChamberAntSpeciesName(){
	
	return "Common Ant ";
	
    }

}
