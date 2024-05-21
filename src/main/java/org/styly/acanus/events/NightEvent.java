package org.styly.acanus.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.styly.acanus.Arcanus;
import org.styly.acanus.registry.ModEffects;

@Mod.EventBusSubscriber(modid = Arcanus.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE,value = Dist.DEDICATED_SERVER)
public class NightEvent {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.player.level().isClientSide()){return;}
        if(event.player.getStringUUID().equals("2980a99e-8582-4f63-9b82-f7117bc8be2c")||event.player.getStringUUID().equals("03d1d7ca-657f-45ad-a51b-1f5dc85b2f4c")){
            if(event.player.level().isNight()){
                event.player.addEffect(new MobEffectInstance(ModEffects.FULL_MOON.get(),60));

            }
        };
    }
}
