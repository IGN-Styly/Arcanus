package org.styly.arcanus.events;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.styly.arcanus.recipe.RitualRecipeProvider;

import java.util.concurrent.CompletableFuture;


public class RegisterRecipes {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // other providers here
        generator.addProvider(
                event.includeServer(),
                new RitualRecipeProvider(output, lookupProvider)
        );
    }
}
