package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.client.model.CrystallineSlimeModel;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrystallineSlimeRenderer extends GeoEntityRenderer<CrystallineSlime> {

    public CrystallineSlimeRenderer(EntityRendererProvider.Context p_174169_) {
        super(p_174169_,new CrystallineSlimeModel());
    }

    @Override
    public RenderType getRenderType(CrystallineSlime animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}

