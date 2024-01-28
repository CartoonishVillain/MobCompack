package com.cartoonishvillain.mobcompack.entity.bop;

import com.cartoonishvillain.mobcompack.Constants;
import com.cartoonishvillain.mobcompack.platform.Services;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Objects;

public class Jaws extends Monster implements GeoEntity {
    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public Jaws(EntityType<? extends Monster> p_33588_, Level p_33589_) {
        super(p_33588_, p_33589_);
        this.moveControl = new JawsMovementControl(this);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CHARGE, 0);
        this.entityData.define(STUN, 0);
        this.entityData.define(CHARGEJUMP, false);
    }

    private static final EntityDataAccessor<Integer> CHARGE = SynchedEntityData.defineId(Jaws.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> STUN = SynchedEntityData.defineId(Jaws.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CHARGEJUMP = SynchedEntityData.defineId(Jaws.class, EntityDataSerializers.BOOLEAN);


    private static final RawAnimation JUMP = RawAnimation.begin().thenPlay("jump");
    private static final RawAnimation CHARGEANIM = RawAnimation.begin().thenPlay("charge");
    private static final RawAnimation STUNANIM = RawAnimation.begin().thenPlay("stun");
    private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("idle");

    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        p_21484_.putInt("charge", getCharge());
        p_21484_.putInt("stun", getCharge());
        p_21484_.putBoolean("cjump", getChargeJumping());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        setCharge(p_21450_.getInt("charge"));
        setStun(p_21450_.getInt("stun"));
        setChargeJumping(p_21450_.getBoolean("cjump"));
    }

    public int getCharge() {
        return this.entityData.get(CHARGE);
    }

    public int getStun() {
        return this.entityData.get(STUN);
    }

    public boolean getChargeJumping() {
        return this.entityData.get(CHARGEJUMP);
    }

    public void setCharge(int count) {this.entityData.set(CHARGE, count);}
    public void setStun(int count) {this.entityData.set(STUN, count);}
    public void setChargeJumping(boolean chargeJumping) {this.entityData.set(CHARGEJUMP, chargeJumping);}

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        return 0;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new JawsTargetingGoal(this));
        this.goalSelector.addGoal(3, new JawsRandomDirectionGoal(this));
        this.goalSelector.addGoal(5, new JawsJumpController(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::shouldAttack));
    }

    public boolean shouldAttack(@Nullable LivingEntity entity) {
        return entity instanceof Player && entity.distanceTo(this) < 32;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getAttributeValue(Attributes.FOLLOW_RANGE) < 32) {
            double num = 32 - this.getAttributeValue(Attributes.FOLLOW_RANGE);
            this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("rangeincrease", num, AttributeModifier.Operation.ADDITION));
        }

        if (getStun() > 0) setStun(getStun()-1);

        if (onGround() && getChargeJumping()) setChargeJumping(false);
    }

    @Override
    public void remove(RemovalReason p_149847_) {
        super.remove(p_149847_);
    }

    @Override
    public void die(DamageSource p_21014_) {
        super.die(p_21014_);
    }

    public static AttributeSupplier.Builder customAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40).add(Attributes.MOVEMENT_SPEED, 0.9d).add(Attributes.ATTACK_DAMAGE, 5).add(Attributes.FOLLOW_RANGE, 32);
    }

    protected float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean doHurtTarget(Entity p_21372_) {
        if (!onGround()) {
            return super.doHurtTarget(p_21372_);
        }
        else return false;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return SoundEvents.SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SLIME_DEATH;
    }

    @Override
    public void push(Entity p_21294_) {
        super.push(p_21294_);
        if(!level().isClientSide && getStun() <= 0) {
            if (p_21294_ instanceof LivingEntity && Objects.equals(this.getTarget(), p_21294_) && !onGround() && p_21294_.hurt(this.damageSources().mobAttack(this), this.getAttackDamage())) {
                this.doEnchantDamageEffects(this, p_21294_);
            }
        }
    }

    @Override
    protected void blockedByShield(LivingEntity p_21246_) {
        super.blockedByShield(p_21246_);
        if(getChargeJumping()) {
            setStun(50);
        }
    }

    protected int getJumpDelay() {
        return 10 + level().random.nextInt(2);
    }

    protected SoundEvent getJumpSound() {
        return this.random.nextInt(50) == 0 ? Services.PLATFORM.getSpringEffect() : SoundEvents.EVOKER_FANGS_ATTACK;
    }

    private PlayState predicate(AnimationState<Jaws> event) {
        if(!onGround()) {
            return event.setAndContinue(JUMP);
        } else if (getCharge() > 0) {
            return event.setAndContinue(CHARGEANIM);
        } else if (getStun() > 0) {
            return event.setAndContinue(STUNANIM);
        } else {
            return event.setAndContinue(IDLE);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "jawsController", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

    private static class JawsMovementControl extends MoveControl {
        private float yRot;
        private int ticksUntilJump;
        private final Jaws jaws;
        private boolean jumpOften;

        public JawsMovementControl(Jaws jaws) {
            super(jaws);
            this.jaws = jaws;
            this.yRot = 180.0F * jaws.getYRot() / 3.1415927F;
        }

        public void setDirection(float targetYaw, boolean jumpOften) {
            this.yRot = targetYaw;
            this.jumpOften = jumpOften;
        }

        public void move(double speed) {
            this.speedModifier = speed;
            this.operation = Operation.MOVE_TO;
        }

        public void tick() {
            float lavaModifier = 0;
            if(jaws.isInLava()) lavaModifier = 3.5f;
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
            this.mob.yHeadRot = this.mob.getYRot();
            this.mob.yBodyRot = this.mob.getYRot();
            if (this.operation != Operation.MOVE_TO) {
                this.mob.setZza(0.0F);
            } else {
                this.operation = Operation.WAIT;
                if (this.mob.onGround() || this.mob.isInLava()) {
                    this.mob.setZza((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    if (this.ticksUntilJump-- <= 0) {

                        if(jaws.getTarget() != null) {
                            this.ticksUntilJump = this.jaws.getJumpDelay();
                        } else {
                            this.ticksUntilJump = this.jaws.getJumpDelay() * 3;
                        }

                        if (!jaws.level().isClientSide && jaws.getStun() <= 0) {
                            boolean check = false;
                            int chance = jaws.level().random.nextInt(5);
                            float distance = 0;
                            float distanceMultiplier = 1;
                            if(jaws.getTarget() != null) {
                                distance = jaws.distanceTo(jaws.getTarget());
                                if (distance > 27) distanceMultiplier = 2f;
                                else if (distance > 22) distanceMultiplier = 1.8f;
                                else if (distance > 17) distanceMultiplier = 1.6f;
                                else if (distance > 12) distanceMultiplier = 1.4f;
                                else if (distance > 7) distanceMultiplier = 1.2f;
                            }

                            if (jaws.getCharge() > 0) {
                                jaws.setCharge(jaws.getCharge() - 1);
                                check = true;
                            }
                            if (jaws.getCharge() >= 0 && check && jaws.getCharge() == 0 && jaws.getTarget() != null) {
                                jaws.moveControl.setWantedPosition(jaws.getTarget().getX(), jaws.getTarget().getY(), jaws.getTarget().getZ(), 1.5f);
                                Vec3 goalPosition = new Vec3(jaws.moveControl.getWantedX(), jaws.moveControl.getWantedY(), jaws.moveControl.getWantedZ());
                                Vec3 directionVector = goalPosition.subtract(jaws.getEyePosition()).normalize();
                                Vec3 yeetVector = new Vec3(directionVector.x() * 2f * distanceMultiplier, 0.5f+lavaModifier, directionVector.z * 2f * distanceMultiplier);
                                this.jaws.setDeltaMovement(yeetVector);
                                this.jaws.playSound(this.jaws.getJumpSound(), this.jaws.getSoundVolume(), 1f);
                                jaws.setOnGround(false);
                                this.jaws.getJumpControl().jump();
                                jaws.setChargeJumping(true);
                                if(Services.PLATFORM.isDevelopmentEnvironment()) {
                                    jaws.getTarget().sendSystemMessage(Component.literal("Charge jump"));
                                }
                            }
                            else if (jaws.getTarget() != null && jaws.getCharge() <= 0) {
                                if(Services.PLATFORM.isDevelopmentEnvironment()) {
                                    jaws.getTarget().sendSystemMessage(Component.literal("===(Debug)==="));
                                    jaws.getTarget().sendSystemMessage(Component.literal("Chance: " + chance));
                                    jaws.getTarget().sendSystemMessage(Component.literal("Distance: " + distance));
                                }
                                if(chance == 0 && distance > 8) {
                                    jaws.setCharge(1);
                                } else {
                                    jaws.moveControl.setWantedPosition(jaws.getTarget().getX(), jaws.getTarget().getY(), jaws.getTarget().getZ(), 1.5f);
                                    Vec3 goalPosition = new Vec3(jaws.moveControl.getWantedX(), jaws.moveControl.getWantedY(), jaws.moveControl.getWantedZ());
                                    Vec3 directionVector = goalPosition.subtract(jaws.getEyePosition()).normalize();
                                    Vec3 yeetVector = new Vec3(directionVector.x * 1.1f * distanceMultiplier, 0.45f+lavaModifier, directionVector.z * 1.1f * distanceMultiplier);
                                    this.jaws.setDeltaMovement(yeetVector);
                                    this.jaws.playSound(this.jaws.getJumpSound(), this.jaws.getSoundVolume(), 1f);
                                    jaws.setOnGround(false);
                                    this.jaws.getJumpControl().jump();
                                }
                            } else if (jaws.getCharge() <= 0){
                                double radDirection = Math.toRadians(this.jaws.getYRot() + 90f);
                                Vec3 yeetVector = new Vec3(Math.cos(radDirection), 0.4f+lavaModifier, Math.sin(radDirection));
                                this.jaws.setDeltaMovement(yeetVector);
                                this.jaws.playSound(this.jaws.getJumpSound(), this.jaws.getSoundVolume(), 1f);
                                jaws.setOnGround(false);
                                this.jaws.getJumpControl().jump();
                            }
                        }
                    } else {
                        this.jaws.xxa = 0.0F;
                        this.jaws.zza = 0.0F;
                        this.mob.setSpeed(0.0F);
                    }
                } else {
                    this.mob.setZza((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                }
            }
        }
    }

    static class JawsRandomDirectionGoal extends Goal {
        private final Jaws jaws;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public JawsRandomDirectionGoal(Jaws p_33679_) {
            this.jaws = p_33679_;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        public boolean canUse() {
            return this.jaws.getTarget() == null && (this.jaws.onGround() || this.jaws.isInWater() || this.jaws.isInLava() || this.jaws.hasEffect(MobEffects.LEVITATION)) && this.jaws.getMoveControl() instanceof JawsMovementControl;
        }

        public void tick() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = this.adjustedTickDelay(40 + this.jaws.getRandom().nextInt(40));
                this.chosenDegrees = (float)this.jaws.getRandom().nextInt(360);
            }

            if (!jaws.level().isClientSide) {
                this.jaws.setYRot(this.chosenDegrees);
//                jaws.yRotO = this.chosenDegrees;
            }

            ((JawsMovementControl)this.jaws.getMoveControl()).setDirection(this.chosenDegrees, false);
        }
    }

    static class JawsTargetingGoal extends Goal {
        private final Jaws jaws;

        public JawsTargetingGoal(Jaws p_33648_) {
            this.jaws = p_33648_;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = this.jaws.getTarget();
            if (livingentity == null) {
                return false;
            } else {
                return this.jaws.canAttack(livingentity) && this.jaws.getMoveControl() instanceof JawsMovementControl;
            }
        }

        public void start() {
            super.start();
        }

        public boolean canContinueToUse() {
            LivingEntity livingentity = this.jaws.getTarget();
            if (livingentity == null) {
                return false;
            } else return this.jaws.canAttack(livingentity);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.jaws.getTarget();
            if (livingentity != null) {
                this.jaws.lookAt(livingentity, 10.0F, 10.0F);
            }

            ((JawsMovementControl)this.jaws.getMoveControl()).setDirection(this.jaws.getYRot(), true);
        }
    }

    static class JawsJumpController extends Goal {
        private final Jaws jaws;

        public JawsJumpController(Jaws p_33660_) {
            this.jaws = p_33660_;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
        }

        public boolean canUse() {
            return !this.jaws.isPassenger();
        }

        public void tick() {
            ((JawsMovementControl)this.jaws.getMoveControl()).move(1.5D);
        }
    }

}
