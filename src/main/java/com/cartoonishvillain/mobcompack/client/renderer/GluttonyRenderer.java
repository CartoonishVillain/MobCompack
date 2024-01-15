package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.client.model.GluttonyModel;
import com.cartoonishvillain.mobcompack.items.SymbolOfGluttony;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GluttonyRenderer extends GeoArmorRenderer<SymbolOfGluttony> {

    public GluttonyRenderer() {
        super(new GluttonyModel());
    }
}
