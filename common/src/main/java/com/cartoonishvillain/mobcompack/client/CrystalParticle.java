package com.cartoonishvillain.mobcompack.client;

import com.cartoonishvillain.mobcompack.platform.Services;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;

public class CrystalParticle extends BreakingItemParticle {

    protected CrystalParticle(ClientLevel p_105665_, double p_105666_, double p_105667_, double p_105668_, ItemStack p_105669_) {
        super(p_105665_, p_105666_, p_105667_, p_105668_, p_105669_);
    }

    public static class CrystallineProvider implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType p_105724_, ClientLevel p_105725_, double p_105726_, double p_105727_, double p_105728_, double p_105729_, double p_105730_, double p_105731_) {
            return new CrystalParticle(p_105725_, p_105726_, p_105727_, p_105728_, new ItemStack(Services.PLATFORM.getRoseGelBall()));
        }
    }
}
