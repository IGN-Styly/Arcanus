package org.styly.arcanus.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class RitualRecipe implements Recipe<RitualRecipeInput> {
    private final ItemStack[] inputItems;
    private final ItemStack result;

    public RitualRecipe(ItemStack[] inputItems,ItemStack result){
        this.inputItems=inputItems;
        this.result=result;
    }
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 16;
    }
    @Override
    public boolean matches(RitualRecipeInput input, Level level) {
        return  input.matches(inputItems);
    }
}
