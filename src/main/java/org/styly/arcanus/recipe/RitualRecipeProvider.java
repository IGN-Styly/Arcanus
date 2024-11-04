package org.styly.arcanus.recipe;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.registry.ModItems;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class RitualRecipeProvider extends RecipeProvider {
    public RitualRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        //new RitualRecipeBuilder(Items.NETHERITE_INGOT.getDefaultInstance(), Arrays.asList(ModItems.AIR.toStack(), Items.DIAMOND.getDefaultInstance(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(), ModItems.AIR.toStack(),ModItems.AIR.toStack())).save(output,new ResourceLocation(Arcanus.MODID,"test_recipe2"));
        new RitualRecipeBuilder(ModItems.MANA_VIAL.toStack(), Arrays.asList(
                ModItems.AIR.toStack(), // must begin with air
                ItemRegistry.ICE_CRYSTAL.get().getDefaultInstance(),
                ModItems.EMPTY_VIAL.toStack(),
                ItemRegistry.ICE_CRYSTAL.get().getDefaultInstance(),

                ModItems.eldritch_rune.get().getDefaultInstance(),
                ModItems.eldritch_rune.get().getDefaultInstance(),

                ModItems.eldritch_rune.get().getDefaultInstance(),
                ModItems.eldritch_rune.get().getDefaultInstance(),

                ModItems.AIR.toStack(),
                ModItems.AIR.toStack(),

                ModItems.eldritch_rune.get().getDefaultInstance(),
                ModItems.eldritch_rune.get().getDefaultInstance(),

                ModItems.eldritch_rune.get().getDefaultInstance(),
                ModItems.eldritch_rune.get().getDefaultInstance(),

                ItemRegistry.ICE_CRYSTAL.get().getDefaultInstance(),
                ItemRegistry.UPGRADE_ORB.get().getDefaultInstance(),
                ItemRegistry.ICE_CRYSTAL.get().getDefaultInstance()))
                .save(output, new ResourceLocation(Arcanus.MODID, "mana_vial"));
    }
}
