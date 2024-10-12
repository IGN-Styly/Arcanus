package org.styly.arcanus.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.styly.arcanus.curios.BasicAttributeCurios;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class TempManaRing extends BasicAttributeCurios implements ICurioItem {
    public TempManaRing(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

    }

}
