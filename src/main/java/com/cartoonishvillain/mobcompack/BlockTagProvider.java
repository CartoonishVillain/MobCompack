package com.cartoonishvillain.mobcompack;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagProvider extends BlockTagsProvider {
    public BlockTagProvider(DataGenerator p_126511_, ExistingFileHelper existingFileHelper) {
        super(p_126511_, MobCompack.MOD_ID, existingFileHelper);
        addTags();
    }

    @Override
    protected void addTags() {
        tag(Tags.MINEABLE_WITH_JAWHAMMER).addTag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(BlockTags.MINEABLE_WITH_SHOVEL);
    }
}
