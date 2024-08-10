package org.styly.acanus.curios;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class BasicAttributeCurios extends Item implements ICurioItem {

    private final AttributeModifier attributeModifier;
    Multimap<Holder<Attribute>, AttributeModifier> attributeMap;

    public BasicAttributeCurios(Item.Properties properties, Holder<Attribute> attribute, AttributeModifier attributeModifier) {
        super(properties);
        this.attributeModifier = attributeModifier;
        attributeMap = HashMultimap.create();
        attributeMap.put(attribute, this.attributeModifier);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> attributeBuilder = new ImmutableMultimap.Builder<>();
        for (Holder<Attribute> attribute : attributeMap.keySet()) {
            attributeBuilder.put(attribute, attributeModifier);
        }
        return attributeBuilder.build();
    }
}
