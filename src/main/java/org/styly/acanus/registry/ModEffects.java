package org.styly.acanus.registry;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.styly.acanus.Arcanus;
import org.styly.acanus.effect.FullMoon;
import org.styly.acanus.effect.MagicBlocked;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Arcanus.MODID);



    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

    public static final RegistryObject<MobEffect> FULL_MOON = MOB_EFFECTS.register("full_moon",()->new FullMoon(MobEffectCategory.BENEFICIAL,3311322).addAttributeModifier(AttributeRegistry.SPELL_POWER.get(),"640667b9-5154-4547-84f7-a02a44f87782",0.50, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributeModifier(Attributes.ATTACK_DAMAGE,"340783c6-7234-481d-8ea5-68f39d82c15e",7, AttributeModifier.Operation.ADDITION).addAttributeModifier(AttributeRegistry.SPELL_RESIST.get(),"a0687470-3c16-45cf-8749-7966545f521c",0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> MAGIC_BLOCKED = MOB_EFFECTS.register("magic_block",()->new MagicBlocked(MobEffectCategory.BENEFICIAL,4393481));
}
