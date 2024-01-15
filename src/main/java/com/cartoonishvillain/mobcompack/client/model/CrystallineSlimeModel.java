
package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CrystallineSlimeModel extends GeoModel<CrystallineSlime> {

	@Override
	public ResourceLocation getModelResource(CrystallineSlime object) {
		return new ResourceLocation(MobCompack.MODID, "geo/crystallineslimenew.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CrystallineSlime object) {
		return new ResourceLocation(MobCompack.MODID, "textures/entity/crystallineslime.png");
	}

	@Override
	public ResourceLocation getAnimationResource(CrystallineSlime animatable) {
		return new ResourceLocation(MobCompack.MODID, "animations/cslime.json");
	}


}