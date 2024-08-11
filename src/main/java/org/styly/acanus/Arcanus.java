package org.styly.acanus;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.styly.acanus.events.CancelSpell;
import org.styly.acanus.events.RegisterISSslot;
import org.styly.acanus.registry.*;
import org.styly.acanus.util.Curios;



@Mod(Arcanus.MODID)
public class Arcanus {
    public static final Logger LOGGER = LogUtils.getLogger();
    public Arcanus(IEventBus eventBus){
        ModItems.register(eventBus);
        AddonSpellRegistry.register(eventBus);
        ModEffects.register(eventBus);
        CreativeTabRegistry.register(eventBus);
        NeoForge.EVENT_BUS.addListener(RegisterISSslot::SpellSelectionEvent);
        NeoForge.EVENT_BUS.addListener(CancelSpell::SpellPreCastEvent);

    }

    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(Arcanus.MODID, path);
    }
    // Define mod id in a common place for everything to reference
    public static final String MODID = "arcanus";

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

}
