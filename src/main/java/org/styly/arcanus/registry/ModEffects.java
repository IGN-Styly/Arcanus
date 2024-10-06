package org.styly.arcanus.registry;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.effect.*;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Arcanus.MODID);

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
    public static final DeferredHolder<MobEffect, DarkVeilEffect> DarkVeil=MOB_EFFECTS.register("dark_veil",()->new DarkVeilEffect(MobEffectCategory.BENEFICIAL,12));
    public static final Holder<MobEffect> FLIGHT = MOB_EFFECTS.register("flight",()->new flight(MobEffectCategory.BENEFICIAL,4393481).addAttributeModifier(NeoForgeMod.CREATIVE_FLIGHT.getDelegate(),NeoForgeMod.CREATIVE_FLIGHT.getKey().location(),1.0, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder<MobEffect> FULL_MOON = MOB_EFFECTS.register("full_moon",()->new FullMoon(MobEffectCategory.BENEFICIAL,490).addAttributeModifier(AttributeRegistry.SPELL_POWER.getDelegate(),AttributeRegistry.SPELL_POWER.getId(),0.50, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).addAttributeModifier(Attributes.ATTACK_DAMAGE,Attributes.ATTACK_DAMAGE.getKey().location(),7.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(AttributeRegistry.SPELL_RESIST.getDelegate(),AttributeRegistry.SPELL_RESIST.getId(),0.5,AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> MAGIC_BLOCKED = MOB_EFFECTS.register("magic_block",()->new MagicBlocked(MobEffectCategory.BENEFICIAL,4393481));
}
