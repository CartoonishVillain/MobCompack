package com.cartoonishvillain.mobcompack;

import com.cartoonishvillain.mobcompack.blocks.GelBlock;
import com.cartoonishvillain.mobcompack.blocks.Tooth;
import com.cartoonishvillain.mobcompack.entity.bop.ArrowOfChompingEntity;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import com.cartoonishvillain.mobcompack.items.*;
import com.cartoonishvillain.mobcompack.items.ArmorMaterials;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class Register {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MobCompack.MOD_ID);
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

    public static final RegistryObject<Item> ROSEGELBALL = ITEMS.register("rose_gelball", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> GIANTTOOTH = ITEMS.register("giant_tooth", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Block> BLOCK_OF_TEETH = BLOCKS.register("tooth_block", () -> new Tooth(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.RAW_IRON).sound(SoundType.BONE_BLOCK).strength(0.89f)));
    public static final RegistryObject<BlockItem> BLOCK_OF_TEETH_BLOCKITEM = ITEMS.register("tooth_block", ()-> new BlockItem(BLOCK_OF_TEETH.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", () -> new Hammer(Materials.TOOTH, 1, 1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Block> ROSE_GEL_BLOCK = BLOCKS.register("rose_gel_block", () -> new GelBlock(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion().strength(0.5f)));
    public static final RegistryObject<BlockItem> ROSE_GEL_BLOCKITEM = ITEMS.register("rose_gel_block", ()-> new BlockItem(ROSE_GEL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<SoundEvent> SPRING = SOUND_EVENT.register("spring", () -> new SoundEvent(new ResourceLocation(MobCompack.MOD_ID, "spring")));

}
