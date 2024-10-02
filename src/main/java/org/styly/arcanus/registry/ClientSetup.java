package org.styly.arcanus.registry;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.entities.RecollectionRenderer;

@EventBusSubscriber(modid = Arcanus.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.RecollectionEntity.get(), RecollectionRenderer::new);
    }
}
