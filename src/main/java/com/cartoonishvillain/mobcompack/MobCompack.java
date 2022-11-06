package com.cartoonishvillain.mobcompack;

import com.cartoonishvillain.mobcompack.configs.CommonConfig;
import com.cartoonishvillain.mobcompack.configs.ConfigHelper;
import com.cartoonishvillain.mobcompack.entity.Spawns;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("mobcompack")
public class MobCompack {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mobcompack";
    public static CommonConfig commonConfig;

    static DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister
            .create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MOD_ID);

    public static RegistryObject<Codec<Spawns.SpawnModifiers>> SPAWNCODEC = serializers.register("spawnmodifiers", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    // declare fields
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(Spawns.SpawnModifiers::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(Spawns.SpawnModifiers::spawn)
                    // declare constructor
            ).apply(builder, Spawns.SpawnModifiers::new)));

    public MobCompack() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        serializers.register(modEventBus);
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

    public static final CreativeModeTab TAB = new CreativeModeTab("mobcompack") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Register.SYMBOLGLUTTONY.get());
        }
    };
}
