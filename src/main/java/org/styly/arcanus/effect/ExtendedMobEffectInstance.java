package org.styly.arcanus.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ExtendedMobEffectInstance extends MagicMobEffect {
    public float DamageSave = 0;

    public ExtendedMobEffectInstance(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public float getDamageSave() {
        return this.DamageSave;
    }

    public void setDamageSave(float dmg) {
        this.DamageSave = dmg;
    }
}
