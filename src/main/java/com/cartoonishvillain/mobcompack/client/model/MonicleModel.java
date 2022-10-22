package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.items.RoseTintedMonocle;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MonicleModel extends AnimatedGeoModel<RoseTintedMonocle> {

    @Override
    public ResourceLocation getModelResource(RoseTintedMonocle object) {
        return new ResourceLocation(MobCompack.MOD_ID, "geo/rose_tinted_monocle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RoseTintedMonocle object) {
        return new ResourceLocation(MobCompack.MOD_ID, "textures/item/rose_tinted_monocle_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RoseTintedMonocle animatable) {
        return new ResourceLocation(MobCompack.MOD_ID, "animations/devourer_chomp.json");
    }
}
