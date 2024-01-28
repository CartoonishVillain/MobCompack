package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.client.model.HammerModel;
import com.cartoonishvillain.mobcompack.items.Hammer;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HammerRenderer extends GeoItemRenderer<Hammer> {
    public HammerRenderer() {
        super(new HammerModel());
    }
}
