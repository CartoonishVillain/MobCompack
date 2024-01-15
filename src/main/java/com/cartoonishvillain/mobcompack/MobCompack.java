package com.cartoonishvillain.mobcompack;

import com.cartoonishvillain.mobcompack.entity.Spawns;
import com.mojang.datafixers.util.Either;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.Function;

import static com.cartoonishvillain.mobcompack.Register.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MobCompack.MODID)
public class MobCompack
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "mobcompack";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    static DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister
            .create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);

    public static DeferredHolder<Codec<? extends BiomeModifier>, Codec<Spawns.SpawnModifiers>> SPAWNCODEC = serializers.register("spawnmodifiers", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    // declare fields
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(Spawns.SpawnModifiers::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(Spawns.SpawnModifiers::spawn)
                    // declare constructor
            ).apply(builder, Spawns.SpawnModifiers::new)));


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public MobCompack(IEventBus modEventBus)
    {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        PARTICLE_TYPES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        SOUND_EVENT.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        serializers.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
