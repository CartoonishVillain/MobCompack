package com.cartoonishvillain.mobcompack.entity.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ExtendedAttackableGoal<T extends LivingEntity> extends NearestAttackableTargetGoal {

    public ExtendedAttackableGoal(Mob p_26053_, Class<T> p_26054_, int p_26055_, boolean p_26056_, boolean p_26057_, @Nullable Predicate<LivingEntity> p_26058_) {
        super(p_26053_, p_26054_, p_26055_, p_26056_, p_26057_, p_26058_);
        this.targetConditions = this.targetConditions.forCombat().range(32);
    }
}
