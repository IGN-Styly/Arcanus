package org.styly.arcanus.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

public record RitualRecipeInput(List<ItemStack> stacks) implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int slot) {
        if (slot > 16 || slot < 0) throw new IllegalArgumentException("No item for index " + slot);
        return stacks.get(slot);
    }

    @Override
    public int size() {
        return 16;
    }

    public boolean matches(List<ItemStack> input) {
        if (input.size() != 17) {
            return false;
        }
        boolean res = true;
        for (int i = 0; i < 17 && res; i++) {
            if (!input.get(i).is(stacks.get(i).getItem())) {
                res = false;
            }
            ;
        }
        return res;
    }
}
