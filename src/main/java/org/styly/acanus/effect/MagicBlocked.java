package org.styly.acanus.effect;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MagicBlocked extends MobEffect{
    public MagicBlocked(MobEffectCategory mobEffectCategory, int color){
        super(mobEffectCategory,color);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amp){
        MagicData magicData = MagicData.getPlayerMagicData(entity);
       if(magicData.isCasting()){
           magicData.getCastingSpell().getSpell().onServerCastComplete(entity.level(),magicData.getCastingSpellLevel(),entity,magicData,true);
       }
    }
    @Override
    public boolean isDurationEffectTick(int duration,int amp){
        return true;
    }
}
