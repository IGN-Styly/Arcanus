package org.styly.arcanus.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.jetbrains.annotations.NotNull;
import org.styly.arcanus.effect.ManaEffect;
import org.styly.arcanus.registry.ModEffects;

import java.util.Objects;

public class EffectEnded {
    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.@NotNull Expired event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance expiredEffect = event.getEffectInstance();
        if (expiredEffect.getEffect().is(Objects.requireNonNull(ModEffects.MANA.getKey()))) {

            entity.addEffect(new MobEffectInstance(ModEffects.MAGIC_BLOCKED, 12000, 0));
        }
    }
}
