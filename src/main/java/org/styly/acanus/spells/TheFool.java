package org.styly.acanus.spells;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.network.SyncManaPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.PacketDistributor;
import org.styly.acanus.Arcanus;
import org.styly.acanus.registry.ModEffects;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class TheFool extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(Arcanus.MODID, "the_fool");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.arcanus.thefool")
                );

    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(300)
            .build();

    public TheFool() {
        this.baseSpellPower = 1;
        this.castTime = 15;
        this.baseManaCost = 1500;
    }
    @Override
    public boolean canBeCraftedBy(Player player) {
         return player.getStringUUID().equals("03d1d7ca-657f-45ad-a51b-1f5dc85b2f4c");
    }


    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
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
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundEvents.NETHERITE_BLOCK_BREAK);
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        if(entity.getTags().contains("fool")&&entity instanceof Player){
            //Lore feature doesn't matter unless you are @Amadhe :D
            ServerPlayer serverPlayer = (ServerPlayer) entity;
            playerMagicData.addMana(250);
            PacketDistributor.sendToPlayer(serverPlayer,new SyncManaPacket(playerMagicData));
            AABB box = new AABB(entity.blockPosition()).expandTowards(50,50,50).expandTowards(-50,-50,-50);
            level.getEntitiesOfClass(LivingEntity.class, box, target -> target.distanceTo(entity)<=20&&target!=entity).forEach(target->target.addEffect(new MobEffectInstance(ModEffects.MAGIC_BLOCKED,2400,0)));
        } else {
            AABB box = new AABB(entity.blockPosition()).expandTowards(50,50,50).expandTowards(-50,-50,-50);
            level.getEntitiesOfClass(LivingEntity.class, box, target -> target.distanceTo(entity)<=10&&target!=entity&& !target.getStringUUID().equals("03d1d7ca-657f-45ad-a51b-1f5dc85b2f4c")).forEach(target->target.addEffect(new MobEffectInstance(ModEffects.MAGIC_BLOCKED,600,0)));

        }
         super.onCast(level, spellLevel, entity, castSource, playerMagicData);

    }
}
