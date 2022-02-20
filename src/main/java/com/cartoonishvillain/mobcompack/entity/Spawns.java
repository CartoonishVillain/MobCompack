package com.cartoonishvillain.mobcompack.entity;

import biomesoplenty.api.biome.BOPBiomes;
import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.Register;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MobCompack.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Spawns {

    @SubscribeEvent
    public static void SnowmanSpawner(BiomeLoadingEvent event) {
        if(event.getName() != null) {
            MobSpawnSettings.SpawnerData crystallinespawner = new MobSpawnSettings.SpawnerData(Register.CRYSTALLINESLIME.get(), 15, 1, 1);
            if (event.getName().toString().equals(BOPBiomes.CRYSTALLINE_CHASM.location().toString())) {
                event.getSpawns().addSpawn(MobCategory.MONSTER, crystallinespawner);
            }
        }
    }


    public static void PlacementManager() {
        SpawnPlacements.register(Register.CRYSTALLINESLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
    }
}
