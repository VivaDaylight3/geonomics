package vivadaylight3.myrmecology.common.item.chamber;

import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.api.ItemBreedingChamber;
import vivadaylight3.myrmecology.common.Register;

public class ChamberDredger extends ItemBreedingChamber {

    public ChamberDredger(int par1) {
	super(par1);
	// TODO Auto-generated constructor stub
    }

    @Override
    public ItemAnt getAnt() {

	return Register.antDredger;

    }

    @Override
    public String getChamberAntSpeciesName() {

	return "Dredger Ant ";

    }

}
