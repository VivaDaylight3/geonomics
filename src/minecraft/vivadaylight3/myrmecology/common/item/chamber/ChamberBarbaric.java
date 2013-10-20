package vivadaylight3.myrmecology.common.item.chamber;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.api.ItemBreedingChamber;
import vivadaylight3.myrmecology.common.Register;

public class ChamberBarbaric extends ItemBreedingChamber {

    public ChamberBarbaric(int par1) {
	super(par1);
    }

    @Override
    public ItemAnt getAnt() {

	return Register.antBarbaric;

    }

    @Override
    public String getChamberAntSpeciesName() {

	return "Barbaric Ant ";

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
