package org.styly.arcanus;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.styly.arcanus.events.CancelSpell;
import org.styly.arcanus.events.RegisterISSslot;
import org.styly.arcanus.events.ServerPlayerEvents;
import org.styly.arcanus.registry.*;


@Mod(Arcanus.MODID)
public class Arcanus {
    public static final Logger LOGGER = LogUtils.getLogger();
    public Arcanus(IEventBus eventBus){
        ModItems.register(eventBus);
        EntityRegistry.register(eventBus);
        AddonSpellRegistry.register(eventBus);
        ModEffects.register(eventBus);
        CreativeTabRegistry.register(eventBus);
        NeoForge.EVENT_BUS.addListener(RegisterISSslot::SpellSelectionEvent);
        NeoForge.EVENT_BUS.addListener(CancelSpell::SpellPreCastEvent);
        NeoForge.EVENT_BUS.addListener(ServerPlayerEvents::onLivingIncomingDamage);
        ArcanusDataAttachments.register(eventBus);

    }

    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(Arcanus.MODID, path);
    }
    // Define mod id in a common place for everything to reference
    public static final String MODID = "arcanus";

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

}
