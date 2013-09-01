package vivadaylight3.myrmecology.common.item.ant;

import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;

public class AntCultivator extends ItemAnt {

    public AntCultivator(int par1) {
	super(par1);
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getSpeciesName() {

	return "Cultivator Ant";

    }

    @Override
    public String getSpeciesSubName() {

	return Reference.ANT_CULTIVATOR_NAME;

    }

}
