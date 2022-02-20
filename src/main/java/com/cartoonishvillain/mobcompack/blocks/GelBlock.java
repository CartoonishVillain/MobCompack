package com.cartoonishvillain.mobcompack.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GelBlock extends SlimeBlock {

    public GelBlock(Properties p_56402_) {
        super(p_56402_);
    }


    @Override
    public void stepOn(Level p_154573_, BlockPos p_154574_, BlockState p_154575_, Entity p_154576_) {
        if(p_154576_ instanceof LivingEntity) {
            p_154576_.hurt(DamageSource.CACTUS, 1.0F);
        }

        super.stepOn(p_154573_, p_154574_, p_154575_, p_154576_);
    }
}
