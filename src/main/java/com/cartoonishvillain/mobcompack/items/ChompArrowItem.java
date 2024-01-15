package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.Register;
import com.cartoonishvillain.mobcompack.entity.bop.ArrowOfChompingEntity;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class ChompArrowItem extends ArrowItem {
    public ChompArrowItem(Properties p_41383_) {
        super(p_41383_);
        DispenserBlock.registerBehavior(this, new AbstractProjectileDispenseBehavior() {

            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                ArrowOfChompingEntity arrowOfChompingEntity = new ArrowOfChompingEntity(Register.ARROW_OF_CHOMPING_ENTITY.get(), pPosition.x(), pPosition.y(), pPosition.z(), pLevel, pStack.copyWithCount(1));
                arrowOfChompingEntity.pickup = AbstractArrow.Pickup.ALLOWED;
                return arrowOfChompingEntity;
            }
        });
    }

    @Override
    public AbstractArrow createArrow(Level p_40513_, ItemStack p_40514_, LivingEntity p_40515_) {
        return new ArrowOfChompingEntity(Register.ARROW_OF_CHOMPING_ENTITY.get(), p_40515_, p_40513_, p_40514_);
    }
}
