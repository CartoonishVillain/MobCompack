package com.cartoonishvillain.mobcompack;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class Tags {
    public static final TagKey<Block> MINEABLE_WITH_JAWHAMMER = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "mineable/jawhammer"));
    public static final TagKey<Biome> SPAWNS_CRYSTALLINE_SLIMES = TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("mobcompack", "worldgen/biome/crystal_slime_spawnable"));
    public static final TagKey<Biome> SPAWNS_JAWBREAKER = TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("mobcompack", "worldgen/biome/jawbreaker_spawnable"));
}
