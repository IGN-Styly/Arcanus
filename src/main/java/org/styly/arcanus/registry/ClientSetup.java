package org.styly.arcanus.registry;


import io.redspace.ironsspellbooks.render.EnergySwirlLayer;
import io.redspace.ironsspellbooks.render.SpellTargetingLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.block.RitualRenderer;
import org.styly.arcanus.effect.DarkVeilEffect;
import org.styly.arcanus.entities.RecollectionRenderer;
import org.styly.arcanus.entities.saberRender;
import org.styly.arcanus.render.DarkVeilLayer;

import static io.redspace.ironsspellbooks.render.EnergySwirlLayer.CHARGE_TEXTURE;

@EventBusSubscriber(modid = Arcanus.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.RecollectionEntity.get(), RecollectionRenderer::new);
        event.registerBlockEntityRenderer(ArcanusBlockEntityRegistry.RITUAL_TABLE.get(), RitualRenderer::new);
        event.registerEntityRenderer(EntityRegistry.SABER.get(), saberRender::new);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        addLayerToPlayerSkin(event, PlayerSkin.Model.SLIM);
        addLayerToPlayerSkin(event, PlayerSkin.Model.WIDE);
        for (EntityType type : event.getEntityTypes()) {
            var renderer = event.getRenderer(type);
            if (renderer instanceof LivingEntityRenderer livingRenderer) {
                livingRenderer.addLayer(new SpellTargetingLayer.Vanilla<>(livingRenderer));
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        //LayerDefinition basicHumanLayer = LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64);

        //See LayerDefinitions.createRoots
        LayerDefinition energyOverlayLayer = LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.5F), 0.0F), 64, 64);
        LayerDefinition outerLayer = LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F), 64, 32);
        LayerDefinition innerLayer = LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F), 64, 32);

        event.registerLayerDefinition(DarkVeilLayer.Vanilla.ENERGY_LAYER, () -> energyOverlayLayer);
    }

    private static void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, PlayerSkin.Model skinName) {
        EntityRenderer<? extends Player> render = event.getSkin(skinName);
        if (render instanceof LivingEntityRenderer livingRenderer) {
            livingRenderer.addLayer(new EnergySwirlLayer.Vanilla(livingRenderer, CHARGE_TEXTURE, DarkVeilEffect.DarkVeilEffectLong));
        }


    /*.valu((entityType)->{
            if(entityType. instanceof EntityType<LivingEntity> livingType)
            if(event.getRenderer(entityType) instanceof EntityRenderer<? extends LivingEntity> livingRenderer)
                livingRenderer.addlayer
        });*/
//        EntityRenderer<? extends LivingEntity> genericRender = event.getRenderer()
        //EntityRenderer<? extends AbstractSpellCastingMob> renderer = event.getRenderer(EntityRegistry.PYROMANCER.get());
    }
}
