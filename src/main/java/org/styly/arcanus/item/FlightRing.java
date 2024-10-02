package org.styly.arcanus.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.styly.arcanus.registry.ModEffects;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class FlightRing extends Item implements ICurioItem {
    public FlightRing() {
        super(new Item.Properties().stacksTo(1).durability(0));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(ModEffects.FLIGHT,20)); //Adds Flight Effect
    }


}
