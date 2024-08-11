package org.styly.acanus.curios;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.styly.acanus.item.AttributeContainer;
import org.styly.acanus.util.Curios;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class BasicAttributeCurios extends Item implements ICurioItem {

    String attributeSlot = "";
    Multimap<Holder<Attribute>, AttributeModifier> attributes = null;

    public BasicAttributeCurios(Item.Properties properties) {
        super(properties);
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        return slotContext.identifier().equals(this.attributeSlot) ? attributes : ICurioItem.super.getAttributeModifiers(slotContext, id, stack);
    }

    public boolean isEquippedBy(@Nullable LivingEntity entity) {
        return entity != null && CuriosApi.getCuriosHelper().findFirstCurio(entity, this).isPresent();
    }
    public BasicAttributeCurios withAttributes(String slot, AttributeContainer... attributes) {
        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        for (AttributeContainer holder : attributes) {
            builder.put(holder.attribute(), holder.createModifier(slot));
        }
        this.attributes = builder.build();
        this.attributeSlot = slot;
        return this;
    }

    public BasicAttributeCurios withCardAttributes(AttributeContainer... attributes) {
        return withAttributes(Curios.CARD, attributes);
    }
}
