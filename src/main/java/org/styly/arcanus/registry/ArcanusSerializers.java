package org.styly.arcanus.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.recipe.RitualRecipe;
import org.styly.arcanus.recipe.RitualRecipeSerializer;

import java.util.function.Supplier;

public class ArcanusSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Arcanus.MODID);

    public static final Supplier<RecipeSerializer<RitualRecipe>> RIGHT_CLICK_BLOCK =
            RECIPE_SERIALIZERS.register("ritual", RitualRecipeSerializer::new);

    public void register(IEventBus eventBus){
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
