package com.cartoonishvillain.mobcompack;

import biomesoplenty.api.block.BOPBlocks;
import com.cartoonishvillain.mobcompack.blocks.GelBlock;
import com.cartoonishvillain.mobcompack.blocks.Tooth;
import com.cartoonishvillain.mobcompack.client.CrystalParticle;
import com.cartoonishvillain.mobcompack.entity.bop.ArrowOfChompingEntity;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import com.cartoonishvillain.mobcompack.items.*;
import com.cartoonishvillain.mobcompack.items.ArmorMaterials;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class FabricRegister {
    public static final EntityType<Jaws> JAWBREAKER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(Constants.MOD_ID, "jawbreaker"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, Jaws::new).dimensions(new EntityDimensions(1.5f, 1.5f, true)).build()
    );

    public static final EntityType<CrystallineSlime> CRYSTALINESLIME = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(Constants.MOD_ID, "crystallineslime"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, CrystallineSlime::new).dimensions(new EntityDimensions(2f, 1.375f, true)).build()
    );

    public static final EntityType<ArrowOfChompingEntity> ARROW_OF_CHOMPING_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(Constants.MOD_ID, "arrow_of_chomping_entity"),
            FabricEntityTypeBuilder.<ArrowOfChompingEntity>create(MobCategory.MISC, ArrowOfChompingEntity::new).dimensions(new EntityDimensions(0.5F, 0.5F, true)).build()
    );

    public static final SpawnEggItem CRYSTALLINESLIMESPAWN = new SpawnEggItem(FabricRegister.CRYSTALINESLIME, 12189768, 16187515, new Item.Properties());
    public static final SpawnEggItem JAWBREAKERSPAWN = new SpawnEggItem(FabricRegister.JAWBREAKER, 9774630, 16777184, new Item.Properties());
    public static final Block BLOCK_OF_TEETH = new Tooth(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.BONE_BLOCK).strength(0.89f));
    public static final Block LIGHTENEDGLASS = new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).mapColor(MapColor.COLOR_PINK).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(FabricRegister::never).isRedstoneConductor(FabricRegister::never).isSuffocating(FabricRegister::never).isViewBlocking(FabricRegister::never).lightLevel(FabricRegister::level));
    public static final Block POLISHEDROSEQUARTZBRICKS = new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.4f).lightLevel(FabricRegister::level));
    public static final Block ROSEQUARTZSTAIRS = new StairBlock(POLISHEDROSEQUARTZBRICKS.defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(FabricRegister::level));
    public static final Block POLISHED_ROSE_QUARTZ_BRICKS_STAIR = new StairBlock(POLISHEDROSEQUARTZBRICKS.defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(FabricRegister::level));
    public static final Block ROSE_QUARTZ_WALL = new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(FabricRegister::level));
    public static final Block POLISHED_ROSE_QUARTZ_BRICKS_WALL = new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(FabricRegister::level));
    public static final Block ROSE_QUARTZ_SLAB = new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(FabricRegister::level));
    public static final Block POLISHED_ROSE_QUARTZ_BRICKS_SLAB = new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(FabricRegister::level));
    public static final Block CHISELED_ROSE_QUARTZ = new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.5f).lightLevel(FabricRegister::level));
    public static final Block CRACKED_CHISELED_ROSE_QUARTZ = new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.2f).lightLevel(FabricRegister::level));
    public static final Block ROSE_QUARTZ = new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_PINK).sound(SoundType.AMETHYST).strength(1.3f).lightLevel(FabricRegister::level));
    public static final Block ROSE_GEL_BLOCK = new GelBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion().strength(0.5f));
    public static final Item ARROW_OF_CHOMPING_ITEM = new ChompArrowItem(new Item.Properties());
    public static final ArmorItem SYMBOLGLUTTONY = new FabricSymbolOfGluttony(ArmorMaterials.GLUTTONY, ArmorItem.Type.HELMET, new Item.Properties());
    public static final ArmorItem TINTEDMONOCLE = new FabricTintedMonocle(ArmorMaterials.RMONICLE, ArmorItem.Type.HELMET, new Item.Properties());
    public static final Item ROSEGELBALL = new Item(new Item.Properties());
    public static final Item GIANTTOOTH = new Item(new Item.Properties());
    public static final BlockItem BLOCK_OF_TEETH_ITEM = new BlockItem(BLOCK_OF_TEETH, new Item.Properties());
    public static final Item HAMMER = new FabricHammer(Materials.TOOTH, 1, 1, new Item.Properties());
    public static final BlockItem ROSE_QUARTZ_STAIRITEM = new BlockItem(ROSEQUARTZSTAIRS, new Item.Properties());
    public static final BlockItem POLISHED_ROSE_QUARTZ_BRICKS_STAIRITEM = new BlockItem(POLISHED_ROSE_QUARTZ_BRICKS_STAIR, new Item.Properties());
    public static final BlockItem ROSE_QUARTZ_WALLITEM = new BlockItem(ROSE_QUARTZ_WALL, new Item.Properties());
    public static final BlockItem POLISHED_ROSE_QUARTZ_BRICKS_WALLITEM = new BlockItem(POLISHED_ROSE_QUARTZ_BRICKS_WALL, new Item.Properties());
    public static final BlockItem ROSE_QUARTZ_SLABITEM = new BlockItem(ROSE_QUARTZ_SLAB, new Item.Properties());
    public static final BlockItem POLISHED_ROSE_QUARTZ_BRICKS_SLABITEM = new BlockItem(POLISHED_ROSE_QUARTZ_BRICKS_SLAB, new Item.Properties());
    public static final BlockItem CHISELED_ROSE_QUARTZITEM = new BlockItem(CHISELED_ROSE_QUARTZ, new Item.Properties());
    public static final BlockItem CRACKED_CHISELED_ROSE_QUARTZITEM = new BlockItem(CRACKED_CHISELED_ROSE_QUARTZ, new Item.Properties());
    public static final BlockItem ROSE_QUARTZITEM = new BlockItem(ROSE_QUARTZ, new Item.Properties());
    public static final BlockItem ROSE_QUARTZ_BRICKSITEM = new BlockItem(POLISHEDROSEQUARTZBRICKS, new Item.Properties());
    public static final BlockItem LIGHTENED_GLASS_ITEM = new BlockItem(LIGHTENEDGLASS, new Item.Properties());
    public static final BlockItem ROSE_GEL_BLOCKITEM = new BlockItem(ROSE_GEL_BLOCK, new Item.Properties());
    public static final SoundEvent SPRING = SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MOD_ID, "spring"));
    public static final ParticleType<SimpleParticleType> CRYSTAL_PARTICLE = FabricParticleTypes.simple();

    public static void init() {
        ParticleFactoryRegistry.getInstance().register(FabricRegister.CRYSTAL_PARTICLE, new CrystalParticle.CrystallineProvider());
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "crystallineslime_egg"), CRYSTALLINESLIMESPAWN);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "jawbreaker_egg"), JAWBREAKERSPAWN);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "arrow_of_chomping"), ARROW_OF_CHOMPING_ITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "symbol_of_gluttony"), SYMBOLGLUTTONY);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "rose_tinted_monocle"), TINTEDMONOCLE);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "rose_gelball"), ROSEGELBALL);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "giant_tooth"), GIANTTOOTH);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "tooth_block"), BLOCK_OF_TEETH_ITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "hammer"), HAMMER);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "rose_quartz_stairs"), ROSE_QUARTZ_STAIRITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz_bricks_stairs"), POLISHED_ROSE_QUARTZ_BRICKS_STAIRITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "rose_quartz_wall"), ROSE_QUARTZ_WALLITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz_bricks_wall"), POLISHED_ROSE_QUARTZ_BRICKS_WALLITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "rose_quartz_slab"), ROSE_QUARTZ_SLABITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz_bricks_slab"), POLISHED_ROSE_QUARTZ_BRICKS_SLABITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "chiseled_polished_rose_quartz"), CHISELED_ROSE_QUARTZITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "cracked_polished_rose_quartz_bricks"), CRACKED_CHISELED_ROSE_QUARTZITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz"), ROSE_QUARTZITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz_bricks"), ROSE_QUARTZ_BRICKSITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "lightened_glass"), LIGHTENED_GLASS_ITEM);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "rose_gel_block"), ROSE_GEL_BLOCKITEM);
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(Constants.MOD_ID, "spring"), SPRING);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "tooth_block"), BLOCK_OF_TEETH);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "lightened_glass"), LIGHTENEDGLASS);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz_bricks"), POLISHEDROSEQUARTZBRICKS);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "rose_quartz_stairs"), ROSEQUARTZSTAIRS);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz_bricks_stairs"), POLISHED_ROSE_QUARTZ_BRICKS_STAIR);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "rose_quartz_wall"), ROSE_QUARTZ_WALL);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz_bricks_wall"), POLISHED_ROSE_QUARTZ_BRICKS_WALL);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "rose_quartz_slab"), ROSE_QUARTZ_SLAB);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz_bricks_slab"), POLISHED_ROSE_QUARTZ_BRICKS_SLAB);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "chiseled_polished_rose_quartz"), CHISELED_ROSE_QUARTZ);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "cracked_polished_rose_quartz_bricks"), CRACKED_CHISELED_ROSE_QUARTZ);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "polished_rose_quartz"), ROSE_QUARTZ);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, "rose_gel_block"), ROSE_GEL_BLOCK);

    }

    private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return false;
    }

    private static int level(BlockState blockState) {
        return 10;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }
}
