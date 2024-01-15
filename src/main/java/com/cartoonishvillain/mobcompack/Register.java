package com.cartoonishvillain.mobcompack;

import biomesoplenty.api.block.BOPBlocks;
import com.cartoonishvillain.mobcompack.blocks.GelBlock;
import com.cartoonishvillain.mobcompack.blocks.Tooth;
import com.cartoonishvillain.mobcompack.entity.bop.ArrowOfChompingEntity;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import com.cartoonishvillain.mobcompack.items.*;
import com.cartoonishvillain.mobcompack.items.ArmorMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.cartoonishvillain.mobcompack.MobCompack.MODID;

public class Register {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, MODID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(Registries.SOUND_EVENT, MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    //-=-=-=-=-=-=-
    public static final DeferredHolder<EntityType<?>, EntityType<CrystallineSlime>> CRYSTALLINESLIME = ENTITY_TYPES.register("crystallineslime", () -> EntityType.Builder.of(CrystallineSlime::new, MobCategory.MONSTER).sized(2f, 1.375f).build(new ResourceLocation(MODID, "crystallineslime").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<Jaws>> JAWS = ENTITY_TYPES.register("jawbreaker", () -> EntityType.Builder.of(Jaws::new, MobCategory.MONSTER).sized(1.5f, 1.5f).build(new ResourceLocation(MODID, "jawbreaker").toString()));

    public static final DeferredItem<Item> CRYSTALLINESLIMESPAWN = ITEMS.register("crystallineslime_egg", () -> new DeferredSpawnEggItem(Register.CRYSTALLINESLIME, 12189768, 16187515, new Item.Properties()));
    public static final DeferredItem<Item> JAWBREAKERSPAWN = ITEMS.register("jawbreaker_egg", () -> new DeferredSpawnEggItem(Register.JAWS, 9774630, 16777184, new Item.Properties()));

    public static final DeferredHolder<ParticleType<?>, ParticleType<SimpleParticleType>> CRYSTALSLIMEPARTICLE = PARTICLE_TYPES.register("crystallineparticle", () -> new SimpleParticleType(false));

    public static final DeferredHolder<EntityType<?>, EntityType<ArrowOfChompingEntity>> ARROW_OF_CHOMPING_ENTITY = ENTITY_TYPES.register("arrow_of_chomping_entity", () -> EntityType.Builder.<ArrowOfChompingEntity>of(ArrowOfChompingEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(MODID, "arrow_of_chomping_entity").toString()));
    public static final DeferredItem<Item> ARROW_OF_CHOMPING_ITEM = ITEMS.register("arrow_of_chomping", () -> new ChompArrowItem(new Item.Properties()));

    public static final DeferredItem<ArmorItem> SYMBOLGLUTTONY = ITEMS.register("symbol_of_gluttony", () -> new SymbolOfGluttony(ArmorMaterials.GLUTTONY, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<ArmorItem> ROSETINTEDMONOCLE = ITEMS.register("rose_tinted_monocle", () -> new RoseTintedMonocle(ArmorMaterials.RMONICLE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final DeferredItem<Item> ROSEGELBALL = ITEMS.register("rose_gelball", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GIANTTOOTH = ITEMS.register("giant_tooth", () -> new Item(new Item.Properties()));
    public static final DeferredBlock<Block> BLOCK_OF_TEETH = BLOCKS.register("tooth_block", () -> new Tooth(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.BONE_BLOCK).strength(0.89f)));
    public static final DeferredBlock<Block> LIGHTENED_GLASS = BLOCKS.register("lightened_glass", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).mapColor(MapColor.COLOR_PINK).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(Register::never).isRedstoneConductor(Register::never).isSuffocating(Register::never).isViewBlocking(Register::never).lightLevel(Register::level)));
    public static final DeferredItem<BlockItem> BLOCK_OF_TEETH_BLOCKITEM = ITEMS.register("tooth_block", ()-> new BlockItem(BLOCK_OF_TEETH.get(), new Item.Properties()));
    public static final DeferredItem<Item> HAMMER = ITEMS.register("hammer", () -> new Hammer(Materials.TOOTH, 1, 1, new Item.Properties()));

    public static final DeferredBlock<Block> ROSE_QUARTZ_BRICKS = BLOCKS.register("polished_rose_quartz_bricks", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.4f).lightLevel(Register::level)));

    public static final DeferredBlock<Block> ROSE_QUARTZ_STAIR = BLOCKS.register("rose_quartz_stairs", () -> new StairBlock(BOPBlocks.ROSE_QUARTZ_BLOCK.defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(Register::level)));
    public static final DeferredItem<BlockItem> ROSE_QUARTZ_STAIRITEM = ITEMS.register("rose_quartz_stairs", ()-> new BlockItem(ROSE_QUARTZ_STAIR.get(), new Item.Properties()));

    public static final DeferredBlock<Block> POLISHED_ROSE_QUARTZ_BRICKS_STAIR = BLOCKS.register("polished_rose_quartz_bricks_stairs", () -> new StairBlock(ROSE_QUARTZ_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(Register::level)));
    public static final DeferredItem<BlockItem> POLISHED_ROSE_QUARTZ_BRICKS_STAIRITEM = ITEMS.register("polished_rose_quartz_bricks_stairs", ()-> new BlockItem(POLISHED_ROSE_QUARTZ_BRICKS_STAIR.get(), new Item.Properties()));

    public static final DeferredBlock<Block> ROSE_QUARTZ_WALL = BLOCKS.register("rose_quartz_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(Register::level)));
    public static final DeferredItem<BlockItem> ROSE_QUARTZ_WALLITEM = ITEMS.register("rose_quartz_wall", ()-> new BlockItem(ROSE_QUARTZ_WALL.get(), new Item.Properties()));
    public static final DeferredBlock<Block> POLISHED_ROSE_QUARTZ_BRICKS_WALL = BLOCKS.register("polished_rose_quartz_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(Register::level)));
    public static final DeferredItem<BlockItem> POLISHED_ROSE_QUARTZ_BRICKS_WALLITEM = ITEMS.register("polished_rose_quartz_bricks_wall", ()-> new BlockItem(POLISHED_ROSE_QUARTZ_BRICKS_WALL.get(), new Item.Properties()));

    public static final DeferredBlock<Block> ROSE_QUARTZ_SLAB = BLOCKS.register("rose_quartz_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(Register::level)));
    public static final DeferredItem<BlockItem> ROSE_QUARTZ_SLABITEM = ITEMS.register("rose_quartz_slab", ()-> new BlockItem(ROSE_QUARTZ_SLAB.get(), new Item.Properties()));
    public static final DeferredBlock<Block> POLISHED_ROSE_QUARTZ_BRICKS_SLAB = BLOCKS.register("polished_rose_quartz_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(Register::level)));
    public static final DeferredItem<BlockItem> POLISHED_ROSE_QUARTZ_BRICKS_SLABITEM = ITEMS.register("polished_rose_quartz_bricks_slab", ()-> new BlockItem(POLISHED_ROSE_QUARTZ_BRICKS_SLAB.get(), new Item.Properties()));


    public static final DeferredBlock<Block> CHISELED_ROSE_QUARTZ = BLOCKS.register("chiseled_polished_rose_quartz", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(Register::level)));
    public static final DeferredBlock<Block> CRACKED_CHISELED_ROSE_QUARTZ = BLOCKS.register("cracked_polished_rose_quartz_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.2f).lightLevel(Register::level)));
    public static final DeferredBlock<Block> ROSE_QUARTZ = BLOCKS.register("polished_rose_quartz", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.3f).lightLevel(Register::level)));
    public static final DeferredItem<BlockItem> CHISELED_ROSE_QUARTZITEM = ITEMS.register("chiseled_polished_rose_quartz", ()-> new BlockItem(CHISELED_ROSE_QUARTZ.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> CRACKED_CHISELED_ROSE_QUARTZITEM = ITEMS.register("cracked_polished_rose_quartz_bricks", ()-> new BlockItem(CRACKED_CHISELED_ROSE_QUARTZ.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> ROSE_QUARTZITEM = ITEMS.register("polished_rose_quartz", ()-> new BlockItem(ROSE_QUARTZ.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> ROSE_QUARTZ_BRICKSITEM = ITEMS.register("polished_rose_quartz_bricks", ()-> new BlockItem(ROSE_QUARTZ_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> LIGHTENED_GLASS_ITEM = ITEMS.register("lightened_glass", ()-> new BlockItem(LIGHTENED_GLASS.get(), new Item.Properties()));
    public static final DeferredBlock<Block> ROSE_GEL_BLOCK = BLOCKS.register("rose_gel_block", () -> new GelBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion().strength(0.5f)));
    public static final DeferredItem<BlockItem> ROSE_GEL_BLOCKITEM = ITEMS.register("rose_gel_block", ()-> new BlockItem(ROSE_GEL_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("mobcompacktab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ROSEGELBALL.get().getDefaultInstance())
            .title(Component.translatable("itemGroup.mobcompack"))
            .displayItems((parameters, output) -> {
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
            }).build());

    public static final DeferredHolder<SoundEvent, SoundEvent> SPRING = SOUND_EVENT.register("spring", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "spring")));
    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    private static int level(BlockState blockState) {
        return 10;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }

    //    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    //    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    //    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    //    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);
    //
    //    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    //    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
    //            .alwaysEat().nutrition(1).saturationMod(2f).build()));
}
