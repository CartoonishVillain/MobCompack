package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.Constants;
import com.cartoonishvillain.mobcompack.items.Hammer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HammerModel extends GeoModel<Hammer> {
    @Override
    public ResourceLocation getModelResource(Hammer object) {
        return new ResourceLocation(Constants.MOD_ID, "geo/sediment_devourer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Hammer object) {
        return new ResourceLocation(Constants.MOD_ID, "textures/item/sediment_devourer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Hammer animatable) {
        return new ResourceLocation(Constants.MOD_ID, "animations/devourer_chomp.json");
    }
}
