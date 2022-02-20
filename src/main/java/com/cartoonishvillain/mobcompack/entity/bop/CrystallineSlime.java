package com.cartoonishvillain.mobcompack.entity.bop;

import com.cartoonishvillain.mobcompack.Register;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

public class CrystallineSlime extends Slime {
    public CrystallineSlime(EntityType<? extends Slime> p_33588_, Level p_33589_) {
        super(p_33588_, p_33589_);
    }

    @Override
    public void tick() {
        super.tick();
        if(tickCount == 1) {
            setSize(4, true);
        }
    }

    @Override
    protected void setSize(int p_33594_, boolean p_33595_) {
        int i = Mth.clamp(p_33594_, 1, 127);
        this.entityData.set(ID_SIZE, i);
        this.reapplyPosition();
        this.refreshDimensions();
    }

    @Override
    public void remove(RemovalReason p_149847_) {
        this.setSize(1, true);
        super.remove(p_149847_);
    }

    @Override
    public void die(DamageSource p_21014_) {
        this.setSize(1, true);
        super.die(p_21014_);
    }

    public static AttributeSupplier.Builder customAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4).add(Attributes.MOVEMENT_SPEED, 0.4d).add(Attributes.ATTACK_DAMAGE, 2);
    }

    @Override
    protected ParticleOptions getParticleType() {
        return (SimpleParticleType) Register.CRYSTALSLIMEPARTICLE.get();
    }
}
