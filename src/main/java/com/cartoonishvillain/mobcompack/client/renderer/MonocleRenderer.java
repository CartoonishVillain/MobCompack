package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.client.model.MonicleModel;
import com.cartoonishvillain.mobcompack.items.RoseTintedMonocle;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class MonocleRenderer extends GeoArmorRenderer<RoseTintedMonocle> {

    public MonocleRenderer() {
        super(new MonicleModel());
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
