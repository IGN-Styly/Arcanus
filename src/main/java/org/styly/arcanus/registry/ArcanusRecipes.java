package org.styly.arcanus.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.recipe.RitualRecipe;

import java.util.function.Supplier;

public class ArcanusRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Arcanus.MODID);

    public static final Supplier<RecipeType<RitualRecipe>> RITUAL =
            RECIPE_TYPES.register(
                    "ritual",
                    // We need the qualifying generic here due to generics being generics.
                    () -> RecipeType.<RitualRecipe>simple(ResourceLocation.fromNamespaceAndPath(Arcanus.MODID, "ritual"))
            );
    public static void register(IEventBus eventBus){
        RECIPE_TYPES.register(eventBus);
    }
}
