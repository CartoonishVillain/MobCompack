package com.cartoonishvillain.mobcompack.entity.bop;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
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

import java.util.EnumSet;
import java.util.Objects;

public class Jaws extends Monster {
    public Jaws(EntityType<? extends Monster> p_33588_, Level p_33589_) {
        super(p_33588_, p_33589_);
        this.moveControl = new JawsMovementControl(this);
    }

    int charge = 0;

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
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_33641_) -> Math.abs(p_33641_.getY() - this.getY()) <= 4.0D));
    }

    @Override
    public void tick() {
        super.tick();
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
                .add(Attributes.MAX_HEALTH, 20).add(Attributes.MOVEMENT_SPEED, 0.9d).add(Attributes.ATTACK_DAMAGE, 3);
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
        return 15 + level.random.nextInt(2);
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.SLIME_JUMP;
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
                            if(jaws.charge >= 0 && --jaws.charge == 0 && jaws.getTarget() != null) {
                                jaws.moveControl.setWantedPosition(jaws.getTarget().getX(), jaws.getTarget().getY(), jaws.getTarget().getZ(), 1.5f);
                                Vec3 goalPosition = new Vec3(jaws.moveControl.getWantedX(), jaws.moveControl.getWantedY(), jaws.moveControl.getWantedZ());
                                Vec3 directionVector = goalPosition.subtract(jaws.getEyePosition()).normalize();
                                Vec3 yeetVector = new Vec3(directionVector.x() * 1.5f, 0.4f, directionVector.z * 1.5f);
                                this.jaws.setDeltaMovement(yeetVector);
                                this.jaws.playSound(this.jaws.getJumpSound(), this.jaws.getSoundVolume(), 1f);
                                jaws.setOnGround(false);
                                this.jaws.getJumpControl().jump();
                                if(!FMLLoader.isProduction()) {
                                    jaws.getTarget().sendMessage(new TextComponent("Charge jump"), jaws.getUUID());
                                }
                            }
                            else if (jaws.getTarget() != null && jaws.charge <= 0) {
                                int chance = jaws.level.random.nextInt(5);
                                if(!FMLLoader.isProduction()) {
                                    jaws.getTarget().sendMessage(new TextComponent("===(Debug)==="), jaws.getUUID());
                                    jaws.getTarget().sendMessage(new TextComponent("Chance: " + chance), jaws.getUUID());
                                    jaws.getTarget().sendMessage(new TextComponent("Distance: " + jaws.distanceTo(jaws.getTarget())), jaws.getUUID());
                                }
                                if(chance == 0 && jaws.distanceTo(jaws.getTarget()) > 5) {
                                    jaws.charge = 1;
                                } else {
                                    jaws.moveControl.setWantedPosition(jaws.getTarget().getX(), jaws.getTarget().getY(), jaws.getTarget().getZ(), 1.5f);
                                    Vec3 goalPosition = new Vec3(jaws.moveControl.getWantedX(), jaws.moveControl.getWantedY(), jaws.moveControl.getWantedZ());
                                    Vec3 directionVector = goalPosition.subtract(jaws.getEyePosition()).normalize();
                                    Vec3 yeetVector = new Vec3(directionVector.x() * 0.9f, 0.4f, directionVector.z * 0.9f);
                                    this.jaws.setDeltaMovement(yeetVector);
                                    this.jaws.playSound(this.jaws.getJumpSound(), this.jaws.getSoundVolume(), 1f);
                                    jaws.setOnGround(false);
                                    this.jaws.getJumpControl().jump();
                                }
                            } else if (jaws.charge <= 0){
                                double radDirection = Math.toRadians(this.jaws.getYRot() + 90f);
                                Vec3 yeetVector = new Vec3(Math.cos(radDirection), 0.2f, Math.sin(radDirection));
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
