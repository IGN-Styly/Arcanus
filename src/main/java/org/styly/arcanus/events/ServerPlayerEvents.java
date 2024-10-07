package org.styly.arcanus.events;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import io.redspace.ironsspellbooks.effect.EvasionEffect;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.styly.arcanus.effect.DarkVeilEffect;
import org.styly.arcanus.effect.ExtendedMobEffectInstance;
import org.styly.arcanus.registry.ModEffects;

@EventBusSubscriber
public class ServerPlayerEvents {
    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        var livingEntity = event.getEntity();
        if ((livingEntity instanceof ServerPlayer) || (livingEntity instanceof IMagicEntity)) {
            var playerMagicData = MagicData.getPlayerMagicData(livingEntity);
            if (playerMagicData.getSyncedData().hasEffect(DarkVeilEffect.DarkVeilEffectLong)) {
                MobEffectInstance effect = livingEntity.getEffect(ModEffects.DarkVeil);


            }
        }
    }
}
