package com.cartoonishvillain.mobcompack.client;

import com.cartoonishvillain.mobcompack.Constants;
import com.cartoonishvillain.mobcompack.NeoForgeRegister;
import com.cartoonishvillain.mobcompack.client.renderer.ChompArrowRenderer;
import com.cartoonishvillain.mobcompack.client.renderer.CrystallineSlimeRenderer;
import com.cartoonishvillain.mobcompack.client.renderer.JawsRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderManager {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(NeoForgeRegister.CRYSTALLINESLIME.get(), CrystallineSlimeRenderer::new);
        event.registerEntityRenderer(NeoForgeRegister.JAWS.get(), JawsRenderer::new);
        event.registerEntityRenderer(NeoForgeRegister.ARROW_OF_CHOMPING_ENTITY.get(), ChompArrowRenderer::new);
    }


    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpecial(NeoForgeRegister.CRYSTALSLIMEPARTICLE.get(), new CrystalParticle.CrystallineProvider());
    }
}
