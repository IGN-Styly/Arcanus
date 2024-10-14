package org.styly.arcanus.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public record RitualRecipeInput(ItemStack[] stacks) implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int slot) {
        if (slot>16||slot<0) throw new IllegalArgumentException("No item for index " + slot);
        return stacks[slot];
    }
    @Override
    public int size() {
        return 16;
    }
    public boolean matches(ItemStack[] input){
        return Arrays.equals(input, stacks);
    }
}
