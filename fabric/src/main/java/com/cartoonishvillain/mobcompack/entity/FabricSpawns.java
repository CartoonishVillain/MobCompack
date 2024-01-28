package com.cartoonishvillain.mobcompack.entity;

import biomesoplenty.api.biome.BOPBiomes;
import com.cartoonishvillain.mobcompack.FabricRegister;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class FabricSpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BOPBiomes.CRYSTALLINE_CHASM), MobCategory.MONSTER,
                FabricRegister.CRYSTALINESLIME, 15, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BOPBiomes.VISCERAL_HEAP), MobCategory.MONSTER,
                FabricRegister.JAWBREAKER, 10, 1, 1);

        SpawnPlacements.register(FabricRegister.CRYSTALINESLIME, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        SpawnPlacements.register(FabricRegister.JAWBREAKER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
    }
}
