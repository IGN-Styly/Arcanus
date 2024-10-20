package org.styly.arcanus.events;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.recipe.RitualRecipeProvider;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Arcanus.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegisterRecipes {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        Arcanus.LOGGER.warn("Styly: I exist");
        // other providers here
        generator.addProvider(
                event.includeServer(),
                new RitualRecipeProvider(output, lookupProvider)
        );
    }
}
