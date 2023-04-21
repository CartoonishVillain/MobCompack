package com.cartoonishvillain.mobcompack.events;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.Register;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import static com.cartoonishvillain.mobcompack.Register.*;

@Mod.EventBusSubscriber(modid = MobCompack.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    public static CreativeModeTab COMPACKTAB;

    @SubscribeEvent
    public static void attributeAssigner(EntityAttributeCreationEvent event){
        event.put(Register.CRYSTALLINESLIME.get(), CrystallineSlime.customAttributes().build());
        event.put(Register.JAWS.get(), Jaws.customAttributes().build());
    }

    @SubscribeEvent
    public static void entityRegister(final RegisterEvent event){
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            SpawnPlacements.register(Register.CRYSTALLINESLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
            SpawnPlacements.register(Register.JAWS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        });
    }

    @SubscribeEvent
    public static void registerTab(CreativeModeTabEvent.Register event) {
        COMPACKTAB = event.registerCreativeModeTab(new ResourceLocation(MobCompack.MOD_ID, "mobcompacktab"), builder -> builder
                .icon(() -> new ItemStack(Register.ROSEGELBALL.get()))
                .title(Component.translatable("itemGroup.mobcompack"))
                .displayItems((featureFlags, output) -> {
                    output.accept(ROSE_QUARTZ_BRICKSITEM.get());
                    output.accept(ROSE_QUARTZITEM.get());
                    output.accept(CRACKED_CHISELED_ROSE_QUARTZITEM.get());
                    output.accept(CHISELED_ROSE_QUARTZITEM.get());
                    output.accept(POLISHED_ROSE_QUARTZ_BRICKS_SLABITEM.get());
                    output.accept(ROSE_QUARTZ_SLABITEM.get());
                    output.accept(POLISHED_ROSE_QUARTZ_BRICKS_WALLITEM.get());
                    output.accept(ROSE_QUARTZ_WALLITEM.get());
                    output.accept(POLISHED_ROSE_QUARTZ_BRICKS_STAIRITEM.get());
                    output.accept(ROSE_QUARTZ_STAIRITEM.get());
                    output.accept(LIGHTENED_GLASS_ITEM.get());
                    output.accept(BLOCK_OF_TEETH_BLOCKITEM.get());
                    output.accept(GIANTTOOTH.get());
                    output.accept(ROSE_GEL_BLOCKITEM.get());
                    output.accept(ROSEGELBALL.get());
                    output.accept(HAMMER.get());
                    output.accept(ROSETINTEDMONOCLE.get());
                    output.accept(SYMBOLGLUTTONY.get());
                    output.accept(ARROW_OF_CHOMPING_ITEM.get());
                    output.accept(JAWBREAKERSPAWN.get());
                    output.accept(CRYSTALLINESLIMESPAWN.get());
                }));

    }
}
