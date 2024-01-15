package com.cartoonishvillain.mobcompack.entity.bop;

import com.cartoonishvillain.mobcompack.Config;
import com.cartoonishvillain.mobcompack.Register;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CrystallineSlime extends Slime implements GeoEntity {
    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    boolean lastOnGround = false;
    int squish = 0;

    private static final RawAnimation JUMP = RawAnimation.begin().thenPlay("jump");
    private static final RawAnimation SQUISH = RawAnimation.begin().thenPlay("squish");
    private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("idle");

    public CrystallineSlime(EntityType<? extends Slime> p_33588_, Level p_33589_) {
        super(p_33588_, p_33589_);
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        if (!p_21016_.isIndirect() && p_21016_.getDirectEntity() instanceof LivingEntity livingentity) {
            if (Config.CRYSTALSLIMETHORNSTOGGLE && (Config.CRYSTALSLIMETHORNSPERCENT > this.random.nextInt(100)))
                livingentity.hurt(this.damageSources().thorns(this), 2.0F);
        }

        return super.hurt(p_21016_, p_21017_);
    }

    @Override
    public void tick() {
        super.tick();
        if(tickCount == 1) {
            setSize(3, true);
        }

        if(!lastOnGround && this.onGround()) {
            squish = 20;
        }

        lastOnGround = this.onGround();
        if (squish > 0) squish--;
    }

    @Override
    public void setSize(int p_33594_, boolean p_33595_) {
        int i = Mth.clamp(p_33594_, 1, 127);
        this.entityData.set(ID_SIZE, i);
        this.reapplyPosition();
        this.refreshDimensions();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public void remove(RemovalReason p_149847_) {
        this.setSize(1, true);
        super.remove(p_149847_);
    }

    @Override
    protected void jumpFromGround() {
        super.jumpFromGround();

    }

    @Override
    public void die(DamageSource p_21014_) {
        this.setSize(1, true);
        super.die(p_21014_);
    }

    public static AttributeSupplier.Builder customAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4).add(Attributes.MOVEMENT_SPEED, 0.4d).add(Attributes.ATTACK_DAMAGE, 3);
    }

    @Override
    protected ParticleOptions getParticleType() {
        return (SimpleParticleType) Register.CRYSTALSLIMEPARTICLE.get();
    }

    private PlayState predicate(AnimationState<CrystallineSlime> event) {
        if(!onGround()) {
            return event.setAndContinue(JUMP);
        } else if (squish > 0) {
            return event.setAndContinue(SQUISH);
        } else {
            return event.setAndContinue(IDLE);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "roseSlimeController", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
}
