package com.cartoonishvillain.mobcompack.client;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.Register;
import com.cartoonishvillain.mobcompack.client.renderer.*;
import com.cartoonishvillain.mobcompack.items.RoseTintedMonocle;
import com.cartoonishvillain.mobcompack.items.SymbolOfGluttony;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

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

    @SubscribeEvent
    public static void fmlSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Register.ROSE_GEL_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Register.LIGHTENED_GLASS.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(SymbolOfGluttony.class, new GluttonyRenderer());
        GeoArmorRenderer.registerArmorRenderer(RoseTintedMonocle.class, new MonocleRenderer());
    }
}
