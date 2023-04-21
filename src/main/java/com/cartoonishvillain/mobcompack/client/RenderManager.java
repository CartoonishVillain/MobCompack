package com.cartoonishvillain.mobcompack.client;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.Register;
import com.cartoonishvillain.mobcompack.client.renderer.ChompArrowRenderer;
import com.cartoonishvillain.mobcompack.client.renderer.CrystallineSlimeRenderer;
import com.cartoonishvillain.mobcompack.client.renderer.JawsRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MobCompack.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
        Minecraft.getInstance().particleEngine.register(Register.CRYSTALSLIMEPARTICLE.get(), new CrystalParticle.CrystallineProvider());
    }
}
