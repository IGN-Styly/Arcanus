package org.styly.arcanus.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.entities.RecollectionEntity;
import org.styly.arcanus.entities.saber;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Arcanus.MODID);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final DeferredHolder<EntityType<?>, EntityType<RecollectionEntity>> RecollectionEntity =
            ENTITIES.register("recollection", () -> EntityType.Builder.<RecollectionEntity>of(RecollectionEntity::new, MobCategory.MISC)
                    .sized(5f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(Arcanus.MODID, "recollection").toString()));
    public static final DeferredHolder<EntityType<?>,EntityType<saber>> SABER = ENTITIES.register("saber",()-> EntityType.Builder.of(saber::new,MobCategory.MONSTER)
            .sized(10f,10f)
            .clientTrackingRange(64)
            .build(new ResourceLocation(Arcanus.MODID,"saber").toString())
    );

}