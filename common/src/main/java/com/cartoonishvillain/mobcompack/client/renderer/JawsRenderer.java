package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.client.model.JawsModel;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class JawsRenderer extends GeoEntityRenderer<Jaws> {

    public JawsRenderer(EntityRendererProvider.Context p_174169_) {
        super(p_174169_,new JawsModel());
    }
}
