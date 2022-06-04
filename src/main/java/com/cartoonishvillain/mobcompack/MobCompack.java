package com.cartoonishvillain.mobcompack;

import com.cartoonishvillain.mobcompack.configs.CommonConfig;
import com.cartoonishvillain.mobcompack.configs.ConfigHelper;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import terrablender.core.TerraBlender;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("mobcompack")
public class MobCompack
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mobcompack";
    public static CommonConfig commonConfig;


    public MobCompack() {
        // Register ourselves for server and other game events we are interested in
        commonConfig = ConfigHelper.register(ModConfig.Type.COMMON, CommonConfig::new);
        Register.init();
        MinecraftForge.EVENT_BUS.register(this);

    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void giveAdvancement(ServerPlayer player, MinecraftServer server, ResourceLocation advancementResource) {
        if (player != null) {
            Advancement advancement = server.getAdvancements().getAdvancement(advancementResource);
            if (advancement != null) {
                AdvancementProgress advancementprogress = player.getAdvancements().getOrStartProgress(advancement);
                if (!advancementprogress.isDone()) {
                    for (String s : advancementprogress.getRemainingCriteria()) {
                        player.getAdvancements().award(advancement, s);
                    }
                }
            }
        }
    }
}
