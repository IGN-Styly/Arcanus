package org.styly.acanus;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.styly.acanus.events.NightEvent;
import org.styly.acanus.events.RegisterISSslot;
import org.styly.acanus.item.FlightRing;
import org.styly.acanus.registry.*;
import org.styly.acanus.util.Curios;
import top.theillusivec4.curios.api.CuriosApi;


@Mod(Arcanus.MODID)
public class Arcanus {
    public Arcanus(){
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(eventBus);
        AddonSpellRegistry.register(eventBus);
        ModEffects.register(eventBus);
        CreativeTabRegistry.register(eventBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        //MinecraftForge.EVENT_BUS.register(NightEvent.class); Retired
        MinecraftForge.EVENT_BUS.register(RegisterISSslot.class);

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
        Curios.registerCurioSlot("card", 1, false, new ResourceLocation("curios:slot/card_slot"));

    }

}
