package org.styly.acanus.effect;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import static org.styly.acanus.Arcanus.LOGGER;

public class MagicBlocked extends MobEffect{
    public MagicBlocked(MobEffectCategory mobEffectCategory, int color){
        super(mobEffectCategory,color);
    }
}
