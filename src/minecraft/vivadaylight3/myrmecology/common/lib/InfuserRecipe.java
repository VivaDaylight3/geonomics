package vivadaylight3.myrmecology.common.lib;

import net.minecraft.item.ItemStack;

public class InfuserRecipe {

    private ItemStack result;
    private ItemStack[] ingredients = new ItemStack[9];
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
