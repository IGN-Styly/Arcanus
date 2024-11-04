package org.styly.arcanus.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.item.context.UseOnContext;
import org.styly.arcanus.registry.ModItems;

import java.util.Objects;

public class Stella extends Item {
    public Stella(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (Objects.requireNonNull(pContext.getPlayer()).getOffhandItem().is(ItemTags.DURABILITY_ENCHANTABLE) && pContext.getItemInHand().is(ModItems.ETERNAL_STELLA)) {
            pContext.getPlayer().setItemInHand(pContext.getHand(),ItemStack.EMPTY);
            pContext.getPlayer().getOffhandItem().set(DataComponents.UNBREAKABLE, new Unbreakable(true));
            pContext.getPlayer().displayClientMessage(Component.translatable("message.arcanus.umbreakable"),true);
            return InteractionResult.CONSUME;
        } else return InteractionResult.FAIL;
    }
}
