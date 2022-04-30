package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.client.model.JawsModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class JawsRenderer extends GeoEntityRenderer {

    public JawsRenderer(EntityRendererProvider.Context p_174169_) {
        super(p_174169_,new JawsModel());
    }
}
