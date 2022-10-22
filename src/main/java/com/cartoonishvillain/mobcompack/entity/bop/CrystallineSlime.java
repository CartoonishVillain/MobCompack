package com.cartoonishvillain.mobcompack.entity.bop;

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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CrystallineSlime extends Slime implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    boolean lastOnGround = false;
    int squish = 0;

    public CrystallineSlime(EntityType<? extends Slime> p_33588_, Level p_33589_) {
        super(p_33588_, p_33589_);
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        if (!p_21016_.isMagic() && p_21016_.getDirectEntity() instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)p_21016_.getDirectEntity();
            if (!p_21016_.isExplosion()) {
                livingentity.hurt(DamageSource.thorns(this), 2.0F);
            }
        }

        return super.hurt(p_21016_, p_21017_);
    }

    @Override
    public void tick() {
        super.tick();
        if(tickCount == 1) {
            setSize(3, true);
        }

        if(!lastOnGround && this.onGround) {
            squish = 20;
        }

        lastOnGround = this.onGround;
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

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<CrystallineSlime> controller = new AnimationController<>(this, "roseSlimeController", 0,
                this::predicate);

        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(!isOnGround()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("jump", false));
        } else if (squish > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("squish", false));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        }
        return PlayState.CONTINUE;
    }
}
