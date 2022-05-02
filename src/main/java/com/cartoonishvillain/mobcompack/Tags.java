package com.cartoonishvillain.mobcompack;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class Tags {
    public static final TagKey<Block> MINEABLE_WITH_JAWHAMMER = BlockTags.create(new ResourceLocation("forge", "mineable/jawhammer"));
}
