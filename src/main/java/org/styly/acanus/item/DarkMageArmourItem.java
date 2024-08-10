package org.styly.acanus.item;

import io.redspace.ironsspellbooks.entity.armor.netherite.NetheriteMageArmorModel;
import io.redspace.ironsspellbooks.registries.ArmorMaterialRegistry;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DarkMageArmourItem extends ImbuableDarkChestplateArmorItem {
    public DarkMageArmourItem(ArmorItem.Type type, Item.Properties settings) {
        super(ArmorMaterialRegistry.NETHERITE_BATTLEMAGE.get(), type, settings);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GeoArmorRenderer<>(new DarkMageModel());
    }

}