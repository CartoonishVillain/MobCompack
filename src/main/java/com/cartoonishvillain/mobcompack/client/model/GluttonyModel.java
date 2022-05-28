package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.items.SymbolOfGluttony;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GluttonyModel extends AnimatedGeoModel<SymbolOfGluttony> {
    @Override
    public ResourceLocation getModelLocation(SymbolOfGluttony object) {
        return new ResourceLocation(MobCompack.MOD_ID, "geo/jawbhelmet.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SymbolOfGluttony object) {
        return new ResourceLocation(MobCompack.MOD_ID, "textures/item/symbolgluttony.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SymbolOfGluttony animatable) {
        return new ResourceLocation(MobCompack.MOD_ID, "animations/devourer_chomp.json");
    }
}
