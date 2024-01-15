package com.cartoonishvillain.mobcompack.events;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.Register;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = MobCompack.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    public static CreativeModeTab COMPACKTAB;

    @SubscribeEvent
    public static void attributeAssigner(EntityAttributeCreationEvent event){
        event.put(Register.CRYSTALLINESLIME.get(), CrystallineSlime.customAttributes().build());
        event.put(Register.JAWS.get(), Jaws.customAttributes().build());
    }

    @SubscribeEvent
    public static void entityRegister(final RegisterEvent event){
        event.register(Registries.ENTITY_TYPE, helper -> {
            SpawnPlacements.register(Register.CRYSTALLINESLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
            SpawnPlacements.register(Register.JAWS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        });
    }
}
