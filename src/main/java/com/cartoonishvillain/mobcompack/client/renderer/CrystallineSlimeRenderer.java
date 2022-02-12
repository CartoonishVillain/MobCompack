package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.client.model.CrystallineSlimeModel;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrystallineSlimeRenderer extends MobRenderer<CrystallineSlime, CrystallineSlimeModel<CrystallineSlime>> {
    protected final static ResourceLocation TEXTURE = new ResourceLocation(MobCompack.MOD_ID, "textures/entity/crystallineslime.png");

    public CrystallineSlimeRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_, new CrystallineSlimeModel<>(p_174008_.bakeLayer(CrystallineSlimeModel.LAYER_LOCATION)), 0.5f);
    }


    @Override
    public ResourceLocation getTextureLocation(CrystallineSlime p_114482_) {
        return TEXTURE;
    }
}
