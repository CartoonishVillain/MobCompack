
package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrystallineSlimeModel extends AnimatedGeoModel<CrystallineSlime> {

	@Override
	public ResourceLocation getModelResource(CrystallineSlime object) {
		return new ResourceLocation(MobCompack.MOD_ID, "geo/crystallineslimenew.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CrystallineSlime object) {
		return new ResourceLocation(MobCompack.MOD_ID, "textures/entity/crystallineslime.png");
	}

	@Override
	public ResourceLocation getAnimationResource(CrystallineSlime animatable) {
		return new ResourceLocation(MobCompack.MOD_ID, "animations/cslime.json");
	}


}