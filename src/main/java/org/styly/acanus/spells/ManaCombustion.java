package org.styly.acanus.spells;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.fireball.MagicFireball;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.particle.ShockwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.styly.acanus.Arcanus;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class ManaCombustion extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(Arcanus.MODID, "mana_combustion");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(6)
            .build();

    public ManaCombustion() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 50;
        this.spellPowerPerLevel = 20;
        this.castTime = 16;
        this.baseManaCost = 100;
    }

    @Override
    public boolean canBeInterrupted(Player player) {
        return false;
    }

    @Override
    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        //due to melee animation timing, we do not want cast time attribute to affect this spell
        return getCastTime(spellLevel);
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundRegistry.DIVINE_SMITE_WINDUP.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.DIVINE_SMITE_CAST.get());
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = 15f;
        Vec3 smiteLocation = Utils.moveToRelativeGroundLevel(level, entity.getEyePosition().add(entity.getForward().multiply(1.35f, 0, 1.35f)), 1);
        MagicManager.spawnParticles(level, new ShockwaveParticleOptions(SchoolRegistry.ELDRITCH.get().getTargetingColor(), radius * 2, true), smiteLocation.x, smiteLocation.y, smiteLocation.z, 1, 0, 0, 0, 0, true);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.ELDRITCH.get().getTargetingColor(),1), smiteLocation.x, smiteLocation.y, smiteLocation.z, 50, 0, 0, 0, 1, false);
        CameraShakeManager.addCameraShake(new CameraShakeData(42, smiteLocation, 10));

        var entities = level.getEntities(entity, AABB.ofSize(smiteLocation, radius * 2, radius * 4, radius * 2));
        for (Entity targetEntity : entities) {
            //double distance = targetEntity.distanceToSqr(smiteLocation);
            if (targetEntity.isAlive() && targetEntity.isPickable()) {
                if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), this.getDamageSource(entity))) {
                    int i = 0;
                    if (i > 0) {
                        //targetEntity.setSecondsOnFire(i * 4);
                    }
                    //EnchantmentHelper.doPostDamageEffects(entity, targetEntity);
                }
            }
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        //Setting mob type to undead means the smite enchantment also adds to the spell's damage. Seems fitting.
        return getSpellPower(spellLevel, entity) + Utils.getWeaponDamage(entity);
    }
    @Override
    public boolean canBeCraftedBy(Player player) {
        return player.getStringUUID().equals("03d1d7ca-657f-45ad-a51b-1f5dc85b2f4c");
    }

    private String getDamageText(int spellLevel, LivingEntity entity) {
        if (entity != null) {
            float weaponDamage = Utils.getWeaponDamage(entity);
            String plus = "";
            if (weaponDamage > 0) {
                plus = String.format(" (+%s)", Utils.stringTruncation(weaponDamage, 1));
            }
            String damage = Utils.stringTruncation(getDamage(spellLevel, entity), 1);
            return damage + plus;
        }
        return "" + getSpellPower(spellLevel, entity);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.OVERHEAD_MELEE_SWING_ANIMATION;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }
}
