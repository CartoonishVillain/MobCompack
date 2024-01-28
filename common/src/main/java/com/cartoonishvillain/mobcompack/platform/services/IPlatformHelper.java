package com.cartoonishvillain.mobcompack.platform.services;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    /*
    REGISTRIES
     */

    Item getGiantTooth();

    Item getRoseGelBall();

    Item getArrowOfChompingItem();

    EntityType<? extends AbstractArrow> getArrowOfChompingEntityType();

    SoundEvent getSpringEffect();

    ParticleType<?> getGelParticles();

    /*
    Configs
     */

    Boolean CRYSTALSLIMETHORNSTOGGLE();

    int CRYSTALSLIMETHORNSPERCENT();
}