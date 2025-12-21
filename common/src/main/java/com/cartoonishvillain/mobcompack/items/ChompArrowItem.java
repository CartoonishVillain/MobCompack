package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.entity.bop.ArrowOfChompingEntity;
import com.cartoonishvillain.mobcompack.platform.Services;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.jetbrains.annotations.Nullable;

public class ChompArrowItem extends ArrowItem {
    public ChompArrowItem(Properties p_41383_) {
        super(p_41383_);
        DispenserBlock.registerBehavior(this, new ProjectileDispenseBehavior(this));
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        ammo.shrink(1);
        return new ArrowOfChompingEntity(Services.PLATFORM.getArrowOfChompingEntityType(), shooter, level, ammo, weapon);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        ArrowOfChompingEntity arrowOfChompingEntity = new ArrowOfChompingEntity(Services.PLATFORM.getArrowOfChompingEntityType(), pos.x(), pos.y(), pos.z(), level, stack.copyWithCount(1), new ItemStack(Items.BOW));
        arrowOfChompingEntity.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrowOfChompingEntity;
    }
}
