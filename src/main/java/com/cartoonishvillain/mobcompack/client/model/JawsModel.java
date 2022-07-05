package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JawsModel extends AnimatedGeoModel<Jaws> {

    @Override
    public ResourceLocation getModelResource(Jaws object) {
        return new ResourceLocation(MobCompack.MOD_ID, "geo/jawbreaker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Jaws object) {
        return new ResourceLocation(MobCompack.MOD_ID, "textures/entity/jawbreaker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Jaws animatable) {
        return new ResourceLocation(MobCompack.MOD_ID, "animations/jawbreaker_animation.json");
    }
}
