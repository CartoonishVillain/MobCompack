package com.cartoonishvillain.mobcompack.client;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.Register;
import com.cartoonishvillain.mobcompack.client.renderer.ChompArrowRenderer;
import com.cartoonishvillain.mobcompack.client.renderer.CrystallineSlimeRenderer;
import com.cartoonishvillain.mobcompack.client.renderer.JawsRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(modid = MobCompack.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderManager {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(Register.CRYSTALLINESLIME.get(), CrystallineSlimeRenderer::new);
        event.registerEntityRenderer(Register.JAWS.get(), JawsRenderer::new);
        event.registerEntityRenderer(Register.ARROW_OF_CHOMPING_ENTITY.get(), ChompArrowRenderer::new);
    }


    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpecial(Register.CRYSTALSLIMEPARTICLE.get(), new CrystalParticle.CrystallineProvider());
    }
}
