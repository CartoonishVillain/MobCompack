package com.cartoonishvillain.mobcompack;

import com.cartoonishvillain.mobcompack.blocks.GelBlock;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import com.cartoonishvillain.mobcompack.items.CustomSpawnEgg;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
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

    public static void init() {
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<EntityType<CrystallineSlime>> CRYSTALLINESLIME = ENTITY_TYPES.register("crystallineslime", () -> EntityType.Builder.of(CrystallineSlime::new, MobCategory.MONSTER).sized(1f, 1f).build(new ResourceLocation(MobCompack.MOD_ID, "crystallineslime").toString()));
    public static final RegistryObject<EntityType<Jaws>> JAWS = ENTITY_TYPES.register("jawbreaker", () -> EntityType.Builder.of(Jaws::new, MobCategory.MONSTER).sized(1f, 1f).build(new ResourceLocation(MobCompack.MOD_ID, "jawbreaker").toString()));


    public static final RegistryObject<Item> CRYSTALLINESLIMESPAWN = ITEMS.register("crystallineslime_egg", () -> new CustomSpawnEgg(Register.CRYSTALLINESLIME, 12189768, 16187515, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<ParticleType<SimpleParticleType>> CRYSTALSLIMEPARTICLE = PARTICLE_TYPES.register("crystallineparticle", () -> new SimpleParticleType(false));

    public static final RegistryObject<Item> ROSEGELBALL = ITEMS.register("rose_gelball", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Block> ROSE_GEL_BLOCK = BLOCKS.register("rose_gel_block", () -> new GelBlock(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion().strength(0.5f)));
    public static final RegistryObject<BlockItem> ROSE_GEL_BLOCKITEM = ITEMS.register("rose_gel_block", ()-> new BlockItem(ROSE_GEL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


}
