package com.cartoonishvillain.mobcompack.events;

import com.cartoonishvillain.mobcompack.Constants;
import com.cartoonishvillain.mobcompack.ForgeRegister;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    public static CreativeModeTab COMPACKTAB;

    @SubscribeEvent
    public static void attributeAssigner(EntityAttributeCreationEvent event){
        event.put(ForgeRegister.CRYSTALLINESLIME.get(), CrystallineSlime.customAttributes().build());
        event.put(ForgeRegister.JAWS.get(), Jaws.customAttributes().build());
    }

    @SubscribeEvent
    public static void entityRegister(final RegisterEvent event){
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            SpawnPlacements.register(ForgeRegister.CRYSTALLINESLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
            SpawnPlacements.register(ForgeRegister.JAWS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        });
    }
}
