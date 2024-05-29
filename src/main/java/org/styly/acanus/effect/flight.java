package org.styly.acanus.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;

public class flight extends MobEffect {
    public flight(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity livingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        Player player =(Player) livingEntity;
        player.getAbilities().mayfly=false;
        player.onUpdateAbilities();

    }

    @Override
    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        Player player =(Player) pLivingEntity;
        player.getAbilities().mayfly=true;
        player.onUpdateAbilities();
    }
}
