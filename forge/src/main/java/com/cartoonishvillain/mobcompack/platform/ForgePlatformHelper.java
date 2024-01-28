package com.cartoonishvillain.mobcompack.platform;

import com.cartoonishvillain.mobcompack.ForgeMobCompack;
import com.cartoonishvillain.mobcompack.ForgeRegister;
import com.cartoonishvillain.mobcompack.platform.services.IPlatformHelper;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
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
        return ForgeRegister.GIANTTOOTH.get();
    }

    @Override
    public Item getRoseGelBall() {
        return ForgeRegister.ROSEGELBALL.get();
    }

    @Override
    public Item getArrowOfChompingItem() {
        return ForgeRegister.ARROW_OF_CHOMPING_ITEM.get();
    }

    @Override
    public EntityType<? extends AbstractArrow> getArrowOfChompingEntityType() {
        return ForgeRegister.ARROW_OF_CHOMPING_ENTITY.get();
    }

    @Override
    public SoundEvent getSpringEffect() {
        return ForgeRegister.SPRING.get();
    }

    @Override
    public ParticleType<?> getGelParticles() {
        return ForgeRegister.CRYSTALSLIMEPARTICLE.get();
    }

    @Override
    public Boolean CRYSTALSLIMETHORNSTOGGLE() {
        return ForgeMobCompack.commonConfig.CRYSTALSLIMETHORNSTOGGLE.get();
    }

    @Override
    public int CRYSTALSLIMETHORNSPERCENT() {
        return ForgeMobCompack.commonConfig.CRYSTALSLIMETHORNSPERCENT.get();
    }
}