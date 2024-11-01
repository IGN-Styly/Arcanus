package org.styly.arcanus.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.styly.arcanus.effect.ManaEffect;
import org.styly.arcanus.registry.ModEffects;

import java.util.Objects;

public class EffectEnded {
    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        // Check if the entity is a LivingEntity
        LivingEntity entity = event.getEntity();

        // Check if the expired effect is the one we want (e.g., Speed)
        MobEffectInstance expiredEffect = event.getEffectInstance();
        if (expiredEffect.getEffect().is(Objects.requireNonNull(ModEffects.MANA.getKey()))) {
            // Apply a new effect, e.g., Slowness for 100 ticks (5 seconds)
            entity.addEffect(new MobEffectInstance(ModEffects.MANA_OVERDRAWN, 12000, 0));
        }
    }
}
