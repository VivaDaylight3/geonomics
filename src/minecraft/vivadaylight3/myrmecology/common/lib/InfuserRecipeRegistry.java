package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class InfuserRecipeRegistry {

    private static ArrayList<InfuserRecipe> recipes = new ArrayList<InfuserRecipe>();

    public static void addRecipe(InfuserRecipe recipe) {

	recipes.add(recipe);

    }

    public static boolean isShapeless(InfuserRecipe recipe) {

	if (recipe != null) {

	    return recipe.isShapeless();

	}

	return true;

    }

    public static ItemStack[] getIngredients(InfuserRecipe recipe) {

	if (recipe != null) {

	    return recipe.getIngredients();

	}

	return null;

    }

    public static ItemStack getOutput(InfuserRecipe recipe) {

	if (recipe != null) {

	    return recipe.getOutput();

	}

	return null;

    }

    public static ArrayList<InfuserRecipe> getRecipes() {

	return recipes;

    }

}
