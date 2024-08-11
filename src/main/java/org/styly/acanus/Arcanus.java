package org.styly.acanus;

import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;
import org.styly.acanus.events.RegisterISSslot;
import org.styly.acanus.registry.*;
import org.styly.acanus.util.Curios;



@Mod(Arcanus.MODID)
public class Arcanus {
    public Arcanus(IEventBus eventBus){
        ModItems.register(eventBus);
        AddonSpellRegistry.register(eventBus);
        ModEffects.register(eventBus);
        CreativeTabRegistry.register(eventBus);
        NeoForge.EVENT_BUS.addListener(RegisterISSslot::SpellSelectionEvent);

    }

    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(Arcanus.MODID, path);
    }
    // Define mod id in a common place for everything to reference
    public static final String MODID = "arcanus";

    private void enqueueIMC(final InterModEnqueueEvent event) {
        Curios.registerCurioSlot(Curios.CHARM_SLOT, 1, false, null);
        Curios.registerCurioSlot(Curios.BRACELET, 2, false, null);
        Curios.registerCurioSlot(Curios.HEAD,1,false,null);
        Curios.registerCurioSlot("card", 1, false, new ResourceLocation("curios","slot/card_slot"));

    }

}
