package com.cartoonishvillain.mobcompack.client.model;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class CrystallineSlimeLayer extends RenderLayer<CrystallineSlime, CrystallineSlimeModel<CrystallineSlime>> {
    protected final static ResourceLocation TEXTURE = new ResourceLocation(MobCompack.MOD_ID, "textures/entity/crystallineslimelayer.png");

    public CrystallineSlimeLayer(RenderLayerParent<CrystallineSlime, CrystallineSlimeModel<CrystallineSlime>> p_117346_) {
        super(p_117346_);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, CrystallineSlime entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        renderColoredCutoutModel(this.getParentModel(), TEXTURE, matrixStackIn, bufferIn, 200, entitylivingbaseIn, 1.0F, 1.0F, 1.0F);
    }
}
