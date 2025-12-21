package com.cartoonishvillain.mobcompack.events;

import com.cartoonishvillain.mobcompack.Constants;
import com.cartoonishvillain.mobcompack.NeoForgeRegister;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class ModBusEvents {

    public static CreativeModeTab COMPACKTAB;

    @SubscribeEvent
    public static void attributeAssigner(EntityAttributeCreationEvent event){
        event.put(NeoForgeRegister.CRYSTALLINESLIME.get(), CrystallineSlime.customAttributes().build());
        event.put(NeoForgeRegister.JAWS.get(), Jaws.customAttributes().build());
    }
}
