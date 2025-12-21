package com.cartoonishvillain.mobcompack;


import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

import static com.cartoonishvillain.mobcompack.NeoForgeRegister.*;

@Mod(Constants.MOD_ID)
public class NeoforgeMobCompack {

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
        CommonClass.init();
    }

    @EventBusSubscriber(modid = Constants.MOD_ID)
    public static class ModEvents {
        @SubscribeEvent
        public static void spawnPlacements(RegisterSpawnPlacementsEvent event) {
            event.register(CRYSTALLINESLIME.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrystallineSlime::checkCrystalineSlime, RegisterSpawnPlacementsEvent.Operation.AND);
            event.register(JAWS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Jaws::checkJawBreaker, RegisterSpawnPlacementsEvent.Operation.AND);
        }
    }
}