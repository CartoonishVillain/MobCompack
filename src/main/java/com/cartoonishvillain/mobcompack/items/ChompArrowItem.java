package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.Register;
import com.cartoonishvillain.mobcompack.entity.bop.ArrowOfChompingEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChompArrowItem extends ArrowItem {
    public ChompArrowItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public AbstractArrow createArrow(Level p_40513_, ItemStack p_40514_, LivingEntity p_40515_) {
        return new ArrowOfChompingEntity(Register.ARROW_OF_CHOMPING_ENTITY.get(), p_40515_, p_40513_);
    }
}
