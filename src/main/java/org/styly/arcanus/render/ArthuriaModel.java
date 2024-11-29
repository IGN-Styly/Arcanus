package org.styly.arcanus.render;

import net.minecraft.resources.ResourceLocation;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.entities.saber;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ArthuriaModel extends DefaultedEntityGeoModel<saber> {
    public ArthuriaModel() {
        super(new ResourceLocation(Arcanus.MODID,"arthuria"));
    }
}
