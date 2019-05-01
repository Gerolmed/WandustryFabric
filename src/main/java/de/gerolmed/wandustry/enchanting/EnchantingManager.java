package de.gerolmed.wandustry.enchanting;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnchantingManager {
    private static ArrayList<EnchantingRecipe> recipes = new ArrayList<>();

    public static void register(EnchantingRecipe enchantingRecipe) {
        recipes.add(enchantingRecipe);
    }

    public static EnchantingRecipe getRecipe(List<ItemStack> ingredients) {

        for(EnchantingRecipe enchantingRecipe : recipes)
            if(enchantingRecipe.matchesIngredients(ingredients))
                return enchantingRecipe;

        return null;
    }

}
