package org.styly.arcanus.item;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.registries.ArmorMaterialRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DarkMageArmourItem extends ImbuableDarkChestplateArmorItem {
    public DarkMageArmourItem(ArmorItem.Type type, Item.Properties settings) {
        super(ArmorMaterialRegistry.NETHERITE_BATTLEMAGE, type, settings, withManaAndSpellPowerAttribute(2500,0.25));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GeoArmorRenderer<>(new DarkMageModel());
    }

}