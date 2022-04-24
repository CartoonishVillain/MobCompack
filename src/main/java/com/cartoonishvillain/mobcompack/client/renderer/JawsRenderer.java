package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;

public class JawsRenderer extends HumanoidMobRenderer<Jaws, HumanoidModel<Jaws>> {

    protected final static ResourceLocation TEXTURE = DefaultPlayerSkin.getDefaultSkin();

    public JawsRenderer(EntityRendererProvider.Context p_174169_) {
        super(p_174169_, new HumanoidModel<Jaws>(p_174169_.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Jaws entity) {
        return TEXTURE;
    }
}
