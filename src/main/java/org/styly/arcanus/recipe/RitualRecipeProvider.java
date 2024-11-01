package org.styly.arcanus.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class RitualRecipeProvider extends RecipeProvider {
    public RitualRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        //new RitualRecipeBuilder(Items.NETHERITE_INGOT.getDefaultInstance(), Arrays.asList(ModItems.AIR.toStack(), Items.DIAMOND.getDefaultInstance(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(),ModItems.AIR.toStack())).save(output,new ResourceLocation(Arcanus.MODID,"test_recipe2"));
    }
}
