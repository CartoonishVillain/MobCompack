package com.cartoonishvillain.mobcompack;

import com.cartoonishvillain.mobcompack.configs.CommonConfig;
import com.cartoonishvillain.mobcompack.configs.ConfigHelper;
import com.cartoonishvillain.mobcompack.entity.ForgeSpawns;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.cartoonishvillain.mobcompack.Constants.MOD_ID;

@Mod(MOD_ID)
public class ForgeMobCompack {

    public static CommonConfig commonConfig;

    static DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister
            .create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MOD_ID);

    public static RegistryObject<Codec<ForgeSpawns.SpawnModifiers>> SPAWNCODEC = serializers.register("spawnmodifiers", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    // declare fields
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(ForgeSpawns.SpawnModifiers::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(ForgeSpawns.SpawnModifiers::spawn)
                    // declare constructor
            ).apply(builder, ForgeSpawns.SpawnModifiers::new)));
    
    public ForgeMobCompack() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        serializers.register(modEventBus);
        commonConfig = ConfigHelper.register(ModConfig.Type.COMMON, CommonConfig::new);
        ForgeRegister.init();
        CommonClass.init();
    }
}