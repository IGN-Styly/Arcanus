package org.styly.acanus.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.styly.acanus.registry.ModEffects;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class FlightRing extends Item implements ICurioItem {
    public FlightRing() {
        super(new Item.Properties().stacksTo(1).defaultDurability(0));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(ModEffects.FLIGHT.get(),20)); //Adds Flight Effect
    }


}
