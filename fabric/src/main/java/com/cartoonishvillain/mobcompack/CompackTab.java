package com.cartoonishvillain.mobcompack;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import static com.cartoonishvillain.mobcompack.FabricRegister.*;

public class CompackTab {
    public static final CreativeModeTab MOB_COMPACK = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            new ResourceLocation(Constants.MOD_ID, "mobcompacktab"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.mobcompack"))
                    .icon(ROSEGELBALL::getDefaultInstance)
                    .displayItems((parameters, output) -> {
                        output.accept(ROSE_QUARTZ_BRICKSITEM);
                        output.accept(ROSE_QUARTZITEM);
                        output.accept(CRACKED_CHISELED_ROSE_QUARTZITEM);
                        output.accept(CHISELED_ROSE_QUARTZITEM);
                        output.accept(POLISHED_ROSE_QUARTZ_BRICKS_SLABITEM);
                        output.accept(ROSE_QUARTZ_SLABITEM);
                        output.accept(POLISHED_ROSE_QUARTZ_BRICKS_WALLITEM);
                        output.accept(ROSE_QUARTZ_WALLITEM);
                        output.accept(POLISHED_ROSE_QUARTZ_BRICKS_STAIRITEM);
                        output.accept(ROSE_QUARTZ_STAIRITEM);
                        output.accept(LIGHTENED_GLASS_ITEM);
                        output.accept(BLOCK_OF_TEETH_ITEM);
                        output.accept(GIANTTOOTH);
                        output.accept(ROSE_GEL_BLOCKITEM);
                        output.accept(ROSEGELBALL);
                        output.accept(HAMMER);
                        output.accept(TINTEDMONOCLE);
                        output.accept(SYMBOLGLUTTONY);
                        output.accept(ARROW_OF_CHOMPING_ITEM);
                        output.accept(JAWBREAKERSPAWN);
                        output.accept(CRYSTALLINESLIMESPAWN);
                    })
                    .build());
    public static void registerTab() {

    }
}
