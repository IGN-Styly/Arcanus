package org.styly.acanus.item;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.item.armor.NetheriteMageArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.styly.acanus.Arcanus;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class DarkMageModel extends DefaultedItemGeoModel<DarkMageArmourItem> {

    public DarkMageModel(){
        super(new ResourceLocation(Arcanus.MODID, ""));
    }

    @Override
    public ResourceLocation getModelResource(DarkMageArmourItem object) {
        return new ResourceLocation(Arcanus.MODID, "geo/netherite_armor_dark.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DarkMageArmourItem object) {
        return new ResourceLocation(Arcanus.MODID, "textures/models/armor/netherite_dark.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DarkMageArmourItem animatable) {
        return new ResourceLocation(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}