package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.client.model.GluttonyModel;
import com.cartoonishvillain.mobcompack.items.SymbolOfGluttony;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GluttonyRenderer extends GeoArmorRenderer<SymbolOfGluttony> {

    public GluttonyRenderer() {
        super(new GluttonyModel());
        this.headBone = "Helmet";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}
