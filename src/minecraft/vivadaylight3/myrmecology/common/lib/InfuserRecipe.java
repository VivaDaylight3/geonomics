package vivadaylight3.myrmecology.common.lib;

import vivadaylight3.myrmecology.common.inventory.ContainerInfuser;
import net.minecraft.item.ItemStack;

public class InfuserRecipe {

    private ItemStack result;
    private ItemStack[] ingredients = new ItemStack[ContainerInfuser.INVENTORY_SIZE
	    - (ContainerInfuser.numCols * ContainerInfuser.numCols)];
    private boolean shapeless;

    public InfuserRecipe(ItemStack output, boolean shapeless,
	    ItemStack... ingredients) {

	if (ingredients.length > this.ingredients.length) {

	    for (int k = 0; k < this.ingredients.length; k++) {

		this.ingredients[k] = ingredients[k];

	    }

	} else {

	    this.ingredients = ingredients.clone();

	}

	this.result = output;
	this.shapeless = shapeless;

    }

    /**
     * @return the result
     */
    public ItemStack getOutput() {
	return result;
    }

    /**
     * @return the ingredients
     */
    public ItemStack[] getIngredients() {
	return ingredients;
    }

    /**
     * @return the shapeless
     */
    public boolean isShapeless() {
	return shapeless;
    }

}
