package com.cartoonishvillain.mobcompack.platform;

import com.cartoonishvillain.mobcompack.FabricMobCompack;
import com.cartoonishvillain.mobcompack.FabricRegister;
import com.cartoonishvillain.mobcompack.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Item getGiantTooth() {
        return FabricRegister.GIANTTOOTH;
    }

    @Override
    public Item getRoseGelBall() {
        return FabricRegister.ROSEGELBALL;
    }

    @Override
    public Item getArrowOfChompingItem() {
        return FabricRegister.ARROW_OF_CHOMPING_ITEM;
    }

    @Override
    public EntityType<? extends AbstractArrow> getArrowOfChompingEntityType() {
        return FabricRegister.ARROW_OF_CHOMPING_ENTITY;
    }

    @Override
    public SoundEvent getSpringEffect() {
        return FabricRegister.SPRING;
    }

    @Override
    public ParticleType<?> getGelParticles() {
        return FabricRegister.CRYSTAL_PARTICLE;
    }

    @Override
    public Boolean CRYSTALSLIMETHORNSTOGGLE() {
        return FabricMobCompack.TOGGLESLIMETHORNS;
    }

    @Override
    public int CRYSTALSLIMETHORNSPERCENT() {
        return FabricMobCompack.SLIMETHORNCHANCE;
    }
}
