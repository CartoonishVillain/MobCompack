package com.cartoonishvillain.mobcompack.entity;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MobCompack.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Spawns {

    public record SpawnModifiers(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawn) implements BiomeModifier {
        @Override
        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            if (phase == Phase.ADD && this.biomes.contains(biome)) {
                builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, this.spawn);
            }
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {
            return MobCompack.SPAWNCODEC.get();
        }
    }

//    @SubscribeEvent
//    public static void SnowmanSpawner(BiomeLoadingEvent event) {
//        if(event.getName() != null) {
//            MobSpawnSettings.SpawnerData crystallinespawner = new MobSpawnSettings.SpawnerData(Register.CRYSTALLINESLIME.get(), MobCompack.commonConfig.CRYSTALSLIME.get(), 1, 1);
//            if (event.getName().toString().equals(BOPBiomes.CRYSTALLINE_CHASM.location().toString())) {
//                event.getSpawns().addSpawn(MobCategory.MONSTER, crystallinespawner);
//            }
//
//            MobSpawnSettings.SpawnerData jawSpawner = new MobSpawnSettings.SpawnerData(Register.JAWS.get(), MobCompack.commonConfig.JAWBREAKER.get(), 1, 1);
//            if (event.getName().toString().equals(BOPBiomes.VISCERAL_HEAP.location().toString())) {
//                event.getSpawns().addSpawn(MobCategory.MONSTER, jawSpawner);
//            }
//
//        }
//    }

}
