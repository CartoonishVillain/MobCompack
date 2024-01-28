package com.cartoonishvillain.mobcompack;

import com.cartoonishvillain.mobcompack.client.renderer.ChompArrowRenderer;
import com.cartoonishvillain.mobcompack.client.renderer.CrystallineSlimeRenderer;
import com.cartoonishvillain.mobcompack.client.renderer.JawsRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;

@Environment(EnvType.CLIENT)
public class FabricMobCompackClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(FabricRegister.JAWBREAKER, (JawsRenderer::new));
        EntityRendererRegistry.register(FabricRegister.CRYSTALINESLIME, (CrystallineSlimeRenderer::new));
        EntityRendererRegistry.register(FabricRegister.ARROW_OF_CHOMPING_ENTITY, (ChompArrowRenderer::new));
        BlockRenderLayerMap.INSTANCE.putBlock(FabricRegister.LIGHTENEDGLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(FabricRegister.ROSE_GEL_BLOCK, RenderType.translucent());
    }
}
