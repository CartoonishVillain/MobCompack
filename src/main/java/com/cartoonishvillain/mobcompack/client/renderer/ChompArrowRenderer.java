package com.cartoonishvillain.mobcompack.client.renderer;

import com.cartoonishvillain.mobcompack.MobCompack;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ChompArrowRenderer extends ArrowRenderer {
    protected final static ResourceLocation TEXTURE = new ResourceLocation(MobCompack.MODID, "textures/entity/arrow_of_chomping.png");


    public ChompArrowRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity p_114482_) {
        return TEXTURE;
    }
}
