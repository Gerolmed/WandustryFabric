package de.gerolmed.wandustry.enchanting;

import net.minecraft.item.ItemStack;

import java.util.List;

public class EnchantingRecipe {
    private final ItemStack result;
    private final ItemStack[] inputs;
    private final int powerLevel;
    private int enchantDurationTick;

    public EnchantingRecipe(int powerLevel, int enchantDurationTick, ItemStack result, ItemStack... inputs) {
        if(inputs == null || inputs.length == 0 ||inputs.length > 5)
            throw new RuntimeException("Inputs must be between 0 (exclusive) and 5 (inclusive)");
        this.enchantDurationTick = enchantDurationTick;
        this.powerLevel = powerLevel;
        this.result = result;
        this.inputs = inputs;
    }

    public boolean matchesIngredients(List<ItemStack> ingredients) {
        for(ItemStack itemStack : inputs) {
            if(!containsIngredient(itemStack, ingredients))
                return false;
        }
        return true;
    }

    private boolean containsIngredient(ItemStack ingredient, List<ItemStack> ingredients) {
        for(ItemStack itemStack : ingredients) {
            if(ingredient.getItem() == itemStack.getItem())
                return true;
        }
        return false;
    }

    public ItemStack getResult() {
        return result;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public int getEnchantDurationTick() {
        return enchantDurationTick;
    }
}
