package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class JawsModel extends GeoModel<Jaws> {

    @Override
    public ResourceLocation getModelResource(Jaws object) {
        return new ResourceLocation(MobCompack.MODID, "geo/jawbreaker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Jaws object) {
        return new ResourceLocation(MobCompack.MODID, "textures/entity/jawbreaker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Jaws animatable) {
        return new ResourceLocation(MobCompack.MODID, "animations/jawbreaker_animation.json");
    }
}
