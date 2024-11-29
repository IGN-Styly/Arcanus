package org.styly.arcanus.entities;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.styly.arcanus.render.ArthuriaModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class saberRender extends GeoEntityRenderer<saber> {
    public saberRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ArthuriaModel());
    }
}
