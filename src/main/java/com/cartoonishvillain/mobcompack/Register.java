package com.cartoonishvillain.mobcompack;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModTags;
import com.cartoonishvillain.mobcompack.blocks.GelBlock;
import com.cartoonishvillain.mobcompack.blocks.Tooth;
import com.cartoonishvillain.mobcompack.entity.bop.ArrowOfChompingEntity;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import com.cartoonishvillain.mobcompack.items.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class Register {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MobCompack.MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MobCompack.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MobCompack.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MobCompack.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MobCompack.MOD_ID);


    public static void init() {
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        SOUND_EVENT.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    public static final RegistryObject<EntityType<CrystallineSlime>> CRYSTALLINESLIME = ENTITY_TYPES.register("crystallineslime", () -> EntityType.Builder.of(CrystallineSlime::new, MobCategory.MONSTER).sized(2f, 1.375f).build(new ResourceLocation(MobCompack.MOD_ID, "crystallineslime").toString()));
    public static final RegistryObject<EntityType<Jaws>> JAWS = ENTITY_TYPES.register("jawbreaker", () -> EntityType.Builder.of(Jaws::new, MobCategory.MONSTER).sized(1.5f, 1.5f).build(new ResourceLocation(MobCompack.MOD_ID, "jawbreaker").toString()));

    public static final RegistryObject<Item> CRYSTALLINESLIMESPAWN = ITEMS.register("crystallineslime_egg", () -> new CustomSpawnEgg(Register.CRYSTALLINESLIME, 12189768, 16187515, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> JAWBREAKERSPAWN = ITEMS.register("jawbreaker_egg", () -> new CustomSpawnEgg(Register.JAWS, 9774630, 16777184, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<ParticleType<SimpleParticleType>> CRYSTALSLIMEPARTICLE = PARTICLE_TYPES.register("crystallineparticle", () -> new SimpleParticleType(false));

    public static final RegistryObject<EntityType<ArrowOfChompingEntity>> ARROW_OF_CHOMPING_ENTITY = ENTITY_TYPES.register("arrow_of_chomping_entity", () -> EntityType.Builder.<ArrowOfChompingEntity>of(ArrowOfChompingEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(MobCompack.MOD_ID, "arrow_of_chomping_entity").toString()));
    public static final RegistryObject<Item> ARROW_OF_CHOMPING_ITEM = ITEMS.register("arrow_of_chomping", () -> new ChompArrowItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<ArmorItem> SYMBOLGLUTTONY = ITEMS.register("symbol_of_gluttony", () -> new SymbolOfGluttony(ArmorMaterials.GLUTTONY, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<ArmorItem> ROSETINTEDMONOCLE = ITEMS.register("rose_tinted_monocle", () -> new RoseTintedMonocle(ArmorMaterials.RMONICLE, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> ROSEGELBALL = ITEMS.register("rose_gelball", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> GIANTTOOTH = ITEMS.register("giant_tooth", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Block> BLOCK_OF_TEETH = BLOCKS.register("tooth_block", () -> new Tooth(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.RAW_IRON).sound(SoundType.BONE_BLOCK).strength(0.89f)));
    public static final RegistryObject<Block> LIGHTENED_GLASS = BLOCKS.register("lightened_glass", () -> new Block(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.COLOR_PINK).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(Register::never).isRedstoneConductor(Register::never).isSuffocating(Register::never).isViewBlocking(Register::never).lightLevel(Register::level)));

    public static final RegistryObject<BlockItem> BLOCK_OF_TEETH_BLOCKITEM = ITEMS.register("tooth_block", ()-> new BlockItem(BLOCK_OF_TEETH.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", () -> new Hammer(Materials.TOOTH, 1, 1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Block> ROSE_QUARTZ_BRICKS = BLOCKS.register("polished_rose_quartz_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.4f)));

    public static final RegistryObject<Block> ROSE_QUARTZ_STAIR = BLOCKS.register("rose_quartz_stairs", () -> new StairBlock(() -> BOPBlocks.ROSE_QUARTZ_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.5f)));
    public static final RegistryObject<BlockItem> ROSE_QUARTZ_STAIRITEM = ITEMS.register("rose_quartz_stairs", ()-> new BlockItem(ROSE_QUARTZ_STAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICKS_STAIR = BLOCKS.register("polished_rose_quartz_bricks_stairs", () -> new StairBlock(() -> ROSE_QUARTZ_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.5f)));
    public static final RegistryObject<BlockItem> POLISHED_ROSE_QUARTZ_BRICKS_STAIRITEM = ITEMS.register("polished_rose_quartz_bricks_stairs", ()-> new BlockItem(POLISHED_ROSE_QUARTZ_BRICKS_STAIR.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Block> ROSE_QUARTZ_WALL = BLOCKS.register("rose_quartz_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.5f)));
    public static final RegistryObject<BlockItem> ROSE_QUARTZ_WALLITEM = ITEMS.register("rose_quartz_wall", ()-> new BlockItem(ROSE_QUARTZ_WALL.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICKS_WALL = BLOCKS.register("polished_rose_quartz_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.5f)));
    public static final RegistryObject<BlockItem> POLISHED_ROSE_QUARTZ_BRICKS_WALLITEM = ITEMS.register("polished_rose_quartz_bricks_wall", ()-> new BlockItem(POLISHED_ROSE_QUARTZ_BRICKS_WALL.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Block> ROSE_QUARTZ_SLAB = BLOCKS.register("rose_quartz_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.5f)));
    public static final RegistryObject<BlockItem> ROSE_QUARTZ_SLABITEM = ITEMS.register("rose_quartz_slab", ()-> new BlockItem(ROSE_QUARTZ_SLAB.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICKS_SLAB = BLOCKS.register("polished_rose_quartz_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.5f)));
    public static final RegistryObject<BlockItem> POLISHED_ROSE_QUARTZ_BRICKS_SLABITEM = ITEMS.register("polished_rose_quartz_bricks_slab", ()-> new BlockItem(POLISHED_ROSE_QUARTZ_BRICKS_SLAB.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static final RegistryObject<Block> CHISELED_ROSE_QUARTZ = BLOCKS.register("chiseled_polished_rose_quartz", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CRACKED_CHISELED_ROSE_QUARTZ = BLOCKS.register("cracked_polished_rose_quartz_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.2f)));
    public static final RegistryObject<Block> ROSE_QUARTZ = BLOCKS.register("polished_rose_quartz", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).sound(SoundType.STONE).strength(1.3f)));
    public static final RegistryObject<BlockItem> CHISELED_ROSE_QUARTZITEM = ITEMS.register("chiseled_polished_rose_quartz", ()-> new BlockItem(CHISELED_ROSE_QUARTZ.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> CRACKED_CHISELED_ROSE_QUARTZITEM = ITEMS.register("cracked_polished_rose_quartz_bricks", ()-> new BlockItem(CRACKED_CHISELED_ROSE_QUARTZ.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> ROSE_QUARTZITEM = ITEMS.register("polished_rose_quartz", ()-> new BlockItem(ROSE_QUARTZ.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> ROSE_QUARTZ_BRICKSITEM = ITEMS.register("polished_rose_quartz_bricks", ()-> new BlockItem(ROSE_QUARTZ_BRICKS.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> LIGHTENED_GLASS_ITEM = ITEMS.register("lightened_glass", ()-> new BlockItem(LIGHTENED_GLASS.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static final RegistryObject<Block> ROSE_GEL_BLOCK = BLOCKS.register("rose_gel_block", () -> new GelBlock(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion().strength(0.5f)));
    public static final RegistryObject<BlockItem> ROSE_GEL_BLOCKITEM = ITEMS.register("rose_gel_block", ()-> new BlockItem(ROSE_GEL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<SoundEvent> SPRING = SOUND_EVENT.register("spring", () -> new SoundEvent(new ResourceLocation(MobCompack.MOD_ID, "spring")));

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
