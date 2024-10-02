package org.styly.arcanus.spells;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.network.casting.OnCastStartedPacket;
import io.redspace.ironsspellbooks.network.casting.UpdateCastingStatePacket;
import io.redspace.ironsspellbooks.particle.ShockwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.Log;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.entities.RecollectionEntity;
import org.styly.arcanus.registry.ArcanusComponents;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class Recollection extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(Arcanus.MODID, "recollection");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(0)
            .build();

    public Recollection() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 50;
        this.spellPowerPerLevel = 3;
        this.castTime = 12;
        this.baseManaCost = 300;
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
        float radius = 30.25f;
        float distance = 10.9f;



        Vec3 forward = entity.getForward();
        Vec3 hitLocation = entity.position().add(0, entity.getBbHeight() * .3f, 0).add(forward.scale(distance));
        var entities = level.getEntities(entity, AABB.ofSize(hitLocation, radius * 2, radius, radius * 2));
        var damageSource = this.getDamageSource(entity);
        for (Entity targetEntity : entities) {
            if (targetEntity instanceof LivingEntity && targetEntity.isAlive() && entity.isPickable() && targetEntity.position().subtract(entity.getEyePosition()).dot(forward) >= 0 && entity.distanceToSqr(targetEntity) < radius * radius && Utils.hasLineOfSight(level, entity.getEyePosition(), targetEntity.getBoundingBox().getCenter(), true)) {
                Vec3 offsetVector = targetEntity.getBoundingBox().getCenter().subtract(entity.getEyePosition());
                if (offsetVector.dot(forward) >= 0) {
                    if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource)) {
                        MagicManager.spawnParticles(level, ParticleHelper.WISP, targetEntity.getX(), targetEntity.getY() + targetEntity.getBbHeight() * .5f, targetEntity.getZ(), 30, targetEntity.getBbWidth() * .5f, targetEntity.getBbHeight() * .5f, targetEntity.getBbWidth() * .5f, .03, false);
                        EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                    }
                }
            }
        }
        boolean mirrored = true;
        RecollectionEntity Strike = new RecollectionEntity(level, mirrored);
        Strike.moveTo(hitLocation);
        Strike.setYRot(entity.getYRot());
        Strike.setXRot(entity.getXRot());
        level.addFreshEntity(Strike);
        Vec3 smiteLocation = Utils.moveToRelativeGroundLevel(level, entity.getEyePosition().add(entity.getForward().multiply(1.35f, 0, 1.35f)), 1);
        MagicManager.spawnParticles(level, new ShockwaveParticleOptions(SchoolRegistry.HOLY.get().getTargetingColor(), distance * 3, true), smiteLocation.x, smiteLocation.y, smiteLocation.z, 1, 0, 0, 0, 0, true);
        MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, smiteLocation.x, smiteLocation.y, smiteLocation.z, 250, 0, 0, 0, 6, false);
        CameraShakeManager.addCameraShake(new CameraShakeData(42, smiteLocation, radius));

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        //Setting mob type to undead means the smite enchantment also adds to the spell's damage. Seems fitting.
        return getSpellPower(spellLevel, entity) + Utils.getWeaponDamage(entity);
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
    @Override
    public boolean attemptInitiateCast(ItemStack stack, int spellLevel, Level level, Player player, CastSource castSource, boolean triggerCooldown, String castingEquipmentSlot) {
        if (Log.SPELL_DEBUG) {
            IronsSpellbooks.LOGGER.debug("AbstractSpell.attemptInitiateCast isClient:{}, spell{}({})", level.isClientSide, this.getSpellId(), spellLevel);
        }

        if (level.isClientSide) {
            return false;
        }

        var serverPlayer = (ServerPlayer) player;
        var playerMagicData = MagicData.getPlayerMagicData(serverPlayer);
        if(stack.has(ArcanusComponents.ItemMana)){
            // Looks safe to me ;)
            playerMagicData.setMana(playerMagicData.getMana()+stack.get(ArcanusComponents.ItemMana));
        }


        if (!playerMagicData.isCasting()) {
            CastResult castResult = canBeCastedBy(spellLevel, castSource, playerMagicData, serverPlayer);

            if (castResult.message != null) {
                serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(castResult.message));
            }
            if (castResult.message==null && stack.has(ArcanusComponents.ItemMana)){

            }

            if (!castResult.isSuccess() || !checkPreCastConditions(level, spellLevel, serverPlayer, playerMagicData) || NeoForge.EVENT_BUS.post(new SpellPreCastEvent(player, this.getSpellId(), spellLevel, getSchoolType(), castSource)).isCanceled()) {
                return false;
            }

            if (serverPlayer.isUsingItem()) {
                serverPlayer.stopUsingItem();
            }
            int effectiveCastTime = getEffectiveCastTime(spellLevel, player);

            playerMagicData.initiateCast(this, spellLevel, effectiveCastTime, castSource, castingEquipmentSlot);
            playerMagicData.setPlayerCastingItem(stack);

            onServerPreCast(player.level, spellLevel, player, playerMagicData);

            PacketDistributor.sendToPlayer(serverPlayer, new UpdateCastingStatePacket(getSpellId(), spellLevel, effectiveCastTime, castSource, castingEquipmentSlot));
            PacketDistributor.sendToPlayersTrackingEntityAndSelf(serverPlayer, new OnCastStartedPacket(serverPlayer.getUUID(), getSpellId(), spellLevel));

            return true;
        } else {
            Utils.serverSideCancelCast(serverPlayer);
            return false;
        }
    }
}
