package com.cartoonishvillain.mobcompack.entity.bop;

import com.cartoonishvillain.mobcompack.entity.goals.ExtendedAttackableGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
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
import net.minecraftforge.fml.loading.FMLLoader;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Objects;

public class Jaws extends Monster implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    public Jaws(EntityType<? extends Monster> p_33588_, Level p_33589_) {
        super(p_33588_, p_33589_);
        this.moveControl = new JawsMovementControl(this);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CHARGE, 0);
    }

    private static final EntityDataAccessor<Integer> CHARGE = SynchedEntityData.defineId(Jaws.class, EntityDataSerializers.INT);

    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        p_21484_.putInt("charge", getCharge());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        setCharge(p_21450_.getInt("charge"));
    }

    public int getCharge() {
        return this.entityData.get(CHARGE);
    }

    public void setCharge(int count) {this.entityData.set(CHARGE, count);}



    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        return (int) (super.calculateFallDamage(p_21237_, p_21238_) * 0.25f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new Jaws.JawsTargetingGoal(this));
        this.goalSelector.addGoal(3, new Jaws.JawsRandomDirectionGoal(this));
        this.goalSelector.addGoal(5, new Jaws.JawsJumpController(this));
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
                .add(Attributes.MAX_HEALTH, 30).add(Attributes.MOVEMENT_SPEED, 0.9d).add(Attributes.ATTACK_DAMAGE, 5).add(Attributes.FOLLOW_RANGE, 32);
    }

    protected float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean doHurtTarget(Entity p_21372_) {
        if (!isOnGround()) {
            return super.doHurtTarget(p_21372_);
        }
        else return false;
    }

    @Override
    public void push(Entity p_21294_) {
        super.push(p_21294_);
        if(p_21294_ instanceof LivingEntity && Objects.equals(this.getTarget(), p_21294_) && !isOnGround() && p_21294_.hurt(DamageSource.mobAttack(this), this.getAttackDamage())) {
            this.doEnchantDamageEffects(this, p_21294_);
        }
    }

    protected int getJumpDelay() {
        return 10 + level.random.nextInt(2);
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.SLIME_JUMP;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(!isOnGround()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("jump", false));
        } else if (getCharge() > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("charge", true));
        } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<Jaws> controller = new AnimationController<Jaws>(this, "controller", 0,
                this::predicate);

        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory() {
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
            this.operation = MoveControl.Operation.MOVE_TO;
        }

        public void tick() {
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
            this.mob.yHeadRot = this.mob.getYRot();
            this.mob.yBodyRot = this.mob.getYRot();
            if (this.operation != MoveControl.Operation.MOVE_TO) {
                this.mob.setZza(0.0F);
            } else {
                this.operation = MoveControl.Operation.WAIT;
                if (this.mob.isOnGround()) {
                    this.mob.setZza((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    if (this.ticksUntilJump-- <= 0) {

                        if(jaws.getTarget() != null) {
                            this.ticksUntilJump = this.jaws.getJumpDelay();
                        } else {
                            this.ticksUntilJump = this.jaws.getJumpDelay() * 3;
                        }

                        if (!jaws.level.isClientSide) {
                            boolean check = false;
                            int chance = jaws.level.random.nextInt(5);
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
                                Vec3 yeetVector = new Vec3(directionVector.x() * 2f * distanceMultiplier, 0.5f, directionVector.z * 2f * distanceMultiplier);
                                this.jaws.setDeltaMovement(yeetVector);
                                this.jaws.playSound(this.jaws.getJumpSound(), this.jaws.getSoundVolume(), 1f);
                                jaws.setOnGround(false);
                                this.jaws.getJumpControl().jump();
                                if(!FMLLoader.isProduction()) {
                                    jaws.getTarget().sendMessage(new TextComponent("Charge jump"), jaws.getUUID());
                                }
                            }
                            else if (jaws.getTarget() != null && jaws.getCharge() <= 0) {
                                if(!FMLLoader.isProduction()) {
                                    jaws.getTarget().sendMessage(new TextComponent("===(Debug)==="), jaws.getUUID());
                                    jaws.getTarget().sendMessage(new TextComponent("Chance: " + chance), jaws.getUUID());
                                    jaws.getTarget().sendMessage(new TextComponent("Distance: " + distance), jaws.getUUID());
                                }
                                if(chance == 0 && distance > 8) {
                                    jaws.setCharge(1);
                                } else {
                                    jaws.moveControl.setWantedPosition(jaws.getTarget().getX(), jaws.getTarget().getY(), jaws.getTarget().getZ(), 1.5f);
                                    Vec3 goalPosition = new Vec3(jaws.moveControl.getWantedX(), jaws.moveControl.getWantedY(), jaws.moveControl.getWantedZ());
                                    Vec3 directionVector = goalPosition.subtract(jaws.getEyePosition()).normalize();
                                    Vec3 yeetVector = new Vec3(directionVector.x * 1.1f * distanceMultiplier, 0.45f, directionVector.z * 1.1f * distanceMultiplier);
                                    this.jaws.setDeltaMovement(yeetVector);
                                    this.jaws.playSound(this.jaws.getJumpSound(), this.jaws.getSoundVolume(), 1f);
                                    jaws.setOnGround(false);
                                    this.jaws.getJumpControl().jump();
                                }
                            } else if (jaws.getCharge() <= 0){
                                double radDirection = Math.toRadians(this.jaws.getYRot() + 90f);
                                Vec3 yeetVector = new Vec3(Math.cos(radDirection), 0.4f, Math.sin(radDirection));
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
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return this.jaws.getTarget() == null && (this.jaws.onGround || this.jaws.isInWater() || this.jaws.isInLava() || this.jaws.hasEffect(MobEffects.LEVITATION)) && this.jaws.getMoveControl() instanceof Jaws.JawsMovementControl;
        }

        public void tick() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = this.adjustedTickDelay(40 + this.jaws.getRandom().nextInt(40));
                this.chosenDegrees = (float)this.jaws.getRandom().nextInt(360);
            }

            if (!jaws.level.isClientSide) {
                this.jaws.setYRot(this.chosenDegrees);
//                jaws.yRotO = this.chosenDegrees;
            }

            ((Jaws.JawsMovementControl)this.jaws.getMoveControl()).setDirection(this.chosenDegrees, false);
        }
    }

    static class JawsTargetingGoal extends Goal {
        private final Jaws jaws;

        public JawsTargetingGoal(Jaws p_33648_) {
            this.jaws = p_33648_;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
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

            ((Jaws.JawsMovementControl)this.jaws.getMoveControl()).setDirection(this.jaws.getYRot(), true);
        }
    }

    static class JawsJumpController extends Goal {
        private final Jaws jaws;

        public JawsJumpController(Jaws p_33660_) {
            this.jaws = p_33660_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return !this.jaws.isPassenger();
        }

        public void tick() {
            ((Jaws.JawsMovementControl)this.jaws.getMoveControl()).move(1.5D);
        }
    }

}
