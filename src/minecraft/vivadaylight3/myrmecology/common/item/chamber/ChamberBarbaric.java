package vivadaylight3.myrmecology.common.item.chamber;

import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.api.ItemBreedingChamber;
import vivadaylight3.myrmecology.common.Register;

public class ChamberBarbaric extends ItemBreedingChamber {

    public ChamberBarbaric(int par1) {
	super(par1);
    }
    
    @Override
    public ItemAnt getAnt(){
	
	return Register.antBarbaric;
	
    }
    
    @Override
    public String getChamberAntSpeciesName(){
	
	return "Barbaric Ant ";
	
    }

}
