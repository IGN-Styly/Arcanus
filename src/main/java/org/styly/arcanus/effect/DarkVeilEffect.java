package org.styly.arcanus.effect;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
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
        livingEntity.getEffect(this);
        if (event.getAmount() > DamageSave) {
            event.setAmount(event.getAmount() - DamageSave);
        } else if (DamageSave > event.getAmount()) {
            DamageSave = DamageSave - event.getAmount();
            event.setCanceled(true);
        } else if (DamageSave == event.getAmount()) {
            DamageSave = DamageSave - event.getAmount();
            event.setCanceled(true);
        }
        // Just in case **wink** ;)
        if (DamageSave >= 0) {
            livingEntity.removeEffect(ModEffects.DarkVeil);
        }
    }

    @Override
    public void onEffectAdded(LivingEntity pLivingEntity, int pAmplifier) {
        super.onEffectAdded(pLivingEntity, pAmplifier);
        DamageSave = pAmplifier * 25 + 50;
        MagicData.getPlayerMagicData(pLivingEntity).getSyncedData().addEffects(DarkVeilEffectLong);
    }

    @Override
    public void onEffectRemoved(LivingEntity pLivingEntity, int pAmplifier) {
        super.onEffectRemoved(pLivingEntity, pAmplifier);
        MagicData.getPlayerMagicData(pLivingEntity).getSyncedData().removeEffects(DarkVeilEffectLong);
    }
}
