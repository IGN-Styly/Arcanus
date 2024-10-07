package org.styly.arcanus.events;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import io.redspace.ironsspellbooks.effect.EvasionEffect;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.effect.DarkVeilEffect;
import org.styly.arcanus.effect.ExtendedMobEffectInstance;
import org.styly.arcanus.registry.ModEffects;


public class ServerPlayerEvents {
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        var livingEntity = event.getEntity();
        if ((livingEntity instanceof ServerPlayer) || (livingEntity instanceof IMagicEntity)) {
            var playerMagicData = MagicData.getPlayerMagicData(livingEntity);
            if (livingEntity.hasEffect(ModEffects.DarkVeil)) {
                DarkVeilEffect.doEffect(livingEntity,event);
            }
        }
    }
}
