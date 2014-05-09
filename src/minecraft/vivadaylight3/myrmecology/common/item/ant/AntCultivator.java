package vivadaylight3.myrmecology.common.item.ant;

import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;

public class AntCultivator extends ItemAnt {

    public AntCultivator() {
	super();
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

    @Override
    public int[] getColours() {

	return new int[] { 0x603a60, 0xa864a8 };

    }

}
