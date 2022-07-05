package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import com.cartoonishvillain.mobcompack.items.Hammer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HammerModel extends AnimatedGeoModel<Hammer> {
    @Override
    public ResourceLocation getModelResource(Hammer object) {
        return new ResourceLocation(MobCompack.MOD_ID, "geo/sediment_devourer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Hammer object) {
        return new ResourceLocation(MobCompack.MOD_ID, "textures/item/sediment_devourer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Hammer animatable) {
        return new ResourceLocation(MobCompack.MOD_ID, "animations/devourer_chomp.json");
    }
}
