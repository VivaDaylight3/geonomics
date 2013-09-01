package vivadaylight3.myrmecology.api;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.common.Register;

public class Breeding {

    private static ArrayList<BreedingRecipe> breedingList = new ArrayList<BreedingRecipe>();

    /**
     * Adds a new ant breeding recipe
     * 
     * @param ant1
     * @param ant2
     * @param antOutput
     */
    public void addBreeding(ItemAnt ant1, ItemAnt ant2, ItemAnt antOutput) {

	BreedingRecipe recipe = new BreedingRecipe(ant1, ant2, antOutput);
	BreedingRecipe recipe2 = new BreedingRecipe(ant2, ant1, antOutput);

	getBreedingList().add(recipe);
	getBreedingList().add(recipe2);

    }

    public static ArrayList<BreedingRecipe> getBreedingList() {

	return breedingList;

    }

    /**
     * Returns the breeding result from two ants or null if there is no result
     * 
     * @param ant1
     * @param ant2
     * @return ItemStack / null
     */
    public static ItemStack getBreedingResult(ItemAnt ant1, ItemAnt ant2) {

	Object[] breedingArray = getBreedingList().toArray();

	ItemStack item1 = new ItemStack(ant1, 1);
	ItemStack item2 = new ItemStack(ant2, 1);

	if (ant1.isHillAnt() && ant2.isHillAnt()) {

	    return new ItemStack(Register.antCommon, 1, 3);

	}

	for (int k = 0; k < breedingArray.length; k++) {

	    if (((BreedingRecipe) breedingArray[k]).getAnt1() == ant1
		    && ((BreedingRecipe) breedingArray[k]).getAnt2() == ant2
		    && ((BreedingRecipe) breedingArray[k]).getAntOutput() != null) {

		return new ItemStack(
			((BreedingRecipe) breedingArray[k]).getAntOutput(), 1,
			3);

	    }

	}

	return null;

    }

}
