package com.cartoonishvillain.mobcompack;


import com.cartoonishvillain.mobcompack.entity.Spawns;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static com.cartoonishvillain.mobcompack.NeoForgeRegister.*;
import static com.cartoonishvillain.mobcompack.NeoForgeRegister.CREATIVE_MODE_TABS;

@Mod(Constants.MOD_ID)
public class NeoforgeMobCompack {

    static DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister
            .create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Constants.MOD_ID);

    public static DeferredHolder<Codec<? extends BiomeModifier>, Codec<Spawns.SpawnModifiers>> SPAWNCODEC = serializers.register("spawnmodifiers", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    // declare fields
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(Spawns.SpawnModifiers::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(Spawns.SpawnModifiers::spawn)
                    // declare constructor
            ).apply(builder, Spawns.SpawnModifiers::new)));

    public NeoforgeMobCompack(IEventBus modEventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        PARTICLE_TYPES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        SOUND_EVENT.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        serializers.register(modEventBus);
        CommonClass.init();

    }
}