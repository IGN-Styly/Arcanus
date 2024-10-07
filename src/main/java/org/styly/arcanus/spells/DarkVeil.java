package org.styly.arcanus.spells;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.registry.ModEffects;

import java.util.List;

@AutoSpellConfig
public class DarkVeil extends AbstractSpell {
    public DarkVeil() {
        this.manaCostPerLevel = 125;
        this.baseSpellPower = 50;
        this.spellPowerPerLevel = 25;
        this.castTime = 16;
        this.baseManaCost = 350;
    }

    private final ResourceLocation spellId = new ResourceLocation(Arcanus.MODID, "dark_veil");

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.arcanus.defense", spellLevel * 25 + 25), Component.translatable("ui.arcanus.spell.time", (int) ((3 * 60) + (getSpellPower(spellLevel, caster) * spellLevel)) / 60));
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        //Setting mob type to undead means the smite enchantment also adds to the spell's damage. Seems fitting.
        return getSpellPower(spellLevel, entity);
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(ModEffects.DarkVeil, (int) ((3 * 60) + (getSpellPower(spellLevel, entity) * spellLevel)) * 20, spellLevel - 1, false, false), entity);
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(600)
            .build();

}
