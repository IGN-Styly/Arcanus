package org.styly.arcanus.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class FullMoon extends MobEffect {
    public FullMoon(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }


    public void removeAttributeModifiers(LivingEntity livingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers( pAttributeMap);

    }


    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.addAttributeModifiers( pAttributeMap, pAmplifier);

    }
}
