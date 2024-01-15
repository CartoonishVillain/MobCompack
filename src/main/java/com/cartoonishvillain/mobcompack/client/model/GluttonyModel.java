package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.items.SymbolOfGluttony;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GluttonyModel extends GeoModel<SymbolOfGluttony> {

    @Override
    public ResourceLocation getModelResource(SymbolOfGluttony object) {
        return new ResourceLocation(MobCompack.MODID, "geo/jawbhelmet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SymbolOfGluttony object) {
        return new ResourceLocation(MobCompack.MODID, "textures/item/symbolgluttony.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SymbolOfGluttony animatable) {
        return new ResourceLocation(MobCompack.MODID, "animations/devourer_chomp.json");
    }
}
