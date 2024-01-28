package com.cartoonishvillain.mobcompack;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class Tags {
    public static final TagKey<Block> MINEABLE_WITH_JAWHAMMER = TagKey.create(Registries.BLOCK, new ResourceLocation("minecraft", "mineable/jawhammer"));
}
