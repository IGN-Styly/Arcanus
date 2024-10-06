package org.styly.arcanus.effect;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class DarkVeilEffect extends MagicMobEffect {
    public static final long DarkVeilEffectLong = 1024;
    public static final float ATTACK_DAMAGE_PER_LEVEL = .1f;
    public static final float SPEED_PER_LEVEL = .2f;
    public static final float SPELL_POWER_PER_LEVEL = .05f;

    public DarkVeilEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void onEffectAdded(LivingEntity pLivingEntity, int pAmplifier) {
        super.onEffectAdded(pLivingEntity, pAmplifier);
        MagicData.getPlayerMagicData(pLivingEntity).getSyncedData().addEffects(DarkVeilEffectLong);
    }

    @Override
    public void onEffectRemoved(LivingEntity pLivingEntity, int pAmplifier) {
        super.onEffectRemoved(pLivingEntity, pAmplifier);
        MagicData.getPlayerMagicData(pLivingEntity).getSyncedData().removeEffects(DarkVeilEffectLong);
    }
}
