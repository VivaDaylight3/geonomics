package vivadaylight3.myrmecology.common.item.chamber;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.item.ItemBreedingChamber;
import vivadaylight3.myrmecology.common.Register;

public class ChamberScavenger extends ItemBreedingChamber {

    public ChamberScavenger(int par1) {
	super(par1);
	// TODO Auto-generated constructor stub
    }

    @Override
    public ItemAnt getAnt() {

	return Register.antScavenger;

    }

    @Override
    public ItemStack getCraftingIngredient() {

	return new ItemStack(Item.bone);

    }

    @Override
    public String getChamberAntSpeciesName() {

	return "Scavanger Ant ";

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
