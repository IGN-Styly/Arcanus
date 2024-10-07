package org.styly.arcanus.effect;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.registry.ArcanusDataAttachments;
import org.styly.arcanus.registry.ModEffects;

public class DarkVeilEffect extends MagicMobEffect {
    public static final long DarkVeilEffectLong = 1024;
    public static final float ATTACK_DAMAGE_PER_LEVEL = .1f;
    public static final float SPEED_PER_LEVEL = .2f;
    public static final float SPELL_POWER_PER_LEVEL = .05f;

    public DarkVeilEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public static void doEffect(LivingEntity livingEntity, LivingIncomingDamageEvent event) {
        float DamageSave=livingEntity.getData(ArcanusDataAttachments.DAMAGE_ABSORB);
        Arcanus.LOGGER.warn(String.valueOf(DamageSave));
        if (event.getAmount() > DamageSave) {
            event.setAmount(event.getAmount() - DamageSave);
            livingEntity.setData(ArcanusDataAttachments.DAMAGE_ABSORB,0f);
        } else if (DamageSave > event.getAmount()) {
            livingEntity.setData(ArcanusDataAttachments.DAMAGE_ABSORB, DamageSave - event.getAmount());
            event.setCanceled(true);
        } else if (DamageSave == event.getAmount()) {
            livingEntity.setData(ArcanusDataAttachments.DAMAGE_ABSORB,0f);
            event.setCanceled(true);
        }
        // Just in case **wink** ;)
        if (DamageSave <= 0f) {
            livingEntity.removeEffect(ModEffects.DarkVeil);
        }
    }

    @Override
    public void onEffectAdded(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setData(ArcanusDataAttachments.DAMAGE_ABSORB,(float)((pAmplifier+1) * 25 + 25));
        MagicData.getPlayerMagicData(pLivingEntity).getSyncedData().addEffects(DarkVeilEffectLong);
        super.onEffectAdded(pLivingEntity, pAmplifier);
    }

    @Override
    public void onEffectRemoved(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setData(ArcanusDataAttachments.DAMAGE_ABSORB,0f);
        MagicData.getPlayerMagicData(pLivingEntity).getSyncedData().removeEffects(DarkVeilEffectLong);
        super.onEffectRemoved(pLivingEntity, pAmplifier);
    }
}
