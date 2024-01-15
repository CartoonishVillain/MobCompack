package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.client.model.MonicleModel;
import com.cartoonishvillain.mobcompack.items.RoseTintedMonocle;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class MonocleRenderer extends GeoArmorRenderer<RoseTintedMonocle> {

    public MonocleRenderer() {
        super(new MonicleModel());
    }
}
