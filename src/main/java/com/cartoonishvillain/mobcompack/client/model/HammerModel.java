package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.items.Hammer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HammerModel extends GeoModel<Hammer> {
    @Override
    public ResourceLocation getModelResource(Hammer object) {
        return new ResourceLocation(MobCompack.MODID, "geo/sediment_devourer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Hammer object) {
        return new ResourceLocation(MobCompack.MODID, "textures/item/sediment_devourer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Hammer animatable) {
        return new ResourceLocation(MobCompack.MODID, "animations/devourer_chomp.json");
    }
}
