package org.styly.arcanus.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.styly.arcanus.registry.ArcanusRecipes;
import org.styly.arcanus.registry.ArcanusSerializers;

import java.util.List;

public class RitualRecipe implements Recipe<RitualRecipeInput> {
    private final List<ItemStack> inputItems;
    private final ItemStack result;

    public RitualRecipe(List<ItemStack> inputItems,ItemStack result){
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
    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ArcanusSerializers.RITUAL.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ArcanusRecipes.RITUAL.get();
    }

    @Override
    public ItemStack assemble(RitualRecipeInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    public List<ItemStack> getInputItems() {
        return this.inputItems;
    }

    public ItemStack getResult() {
        return this.result;
    }
}
