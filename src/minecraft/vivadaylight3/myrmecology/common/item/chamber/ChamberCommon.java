package vivadaylight3.myrmecology.common.item.chamber;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.item.ItemBreedingChamber;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.item.ant.AntCommon;

public class ChamberCommon extends ItemBreedingChamber {

    public ChamberCommon(int par) {

	super(par);

    }

    @Override
    public ItemAnt getAnt() {

	return Register.antCommon;

    }

    @Override
    public String getChamberAntSpeciesName() {

	return "Common Ant ";

    }

    public boolean usesColourRendering() {

	return true;

    }

    protected int[] getColours() {

	return this.getAnt().getColours();

    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
	return this.usesColourRendering();
    }

}
