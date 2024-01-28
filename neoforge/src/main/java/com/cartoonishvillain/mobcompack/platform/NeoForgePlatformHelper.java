package com.cartoonishvillain.mobcompack.platform;

import com.cartoonishvillain.mobcompack.NeoForgeConfig;
import com.cartoonishvillain.mobcompack.NeoForgeRegister;
import com.cartoonishvillain.mobcompack.platform.services.IPlatformHelper;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public Item getGiantTooth() {
        return NeoForgeRegister.GIANTTOOTH.get();
    }

    @Override
    public Item getRoseGelBall() {
        return NeoForgeRegister.ROSEGELBALL.get();
    }

    @Override
    public Item getArrowOfChompingItem() {
        return NeoForgeRegister.ARROW_OF_CHOMPING_ITEM.get();
    }

    @Override
    public EntityType<? extends AbstractArrow> getArrowOfChompingEntityType() {
        return NeoForgeRegister.ARROW_OF_CHOMPING_ENTITY.get();
    }

    @Override
    public SoundEvent getSpringEffect() {
        return NeoForgeRegister.SPRING.get();
    }

    @Override
    public ParticleType<?> getGelParticles() {
        return NeoForgeRegister.CRYSTALSLIMEPARTICLE.get();
    }

    @Override
    public Boolean CRYSTALSLIMETHORNSTOGGLE() {
        return NeoForgeConfig.CRYSTALSLIMETHORNSTOGGLE;
    }

    @Override
    public int CRYSTALSLIMETHORNSPERCENT() {
        return NeoForgeConfig.CRYSTALSLIMETHORNSPERCENT;
    }
}