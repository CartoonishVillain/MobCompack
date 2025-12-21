package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.FabricRegister;
import com.cartoonishvillain.mobcompack.entity.FabricArrowOfChompingEntity;
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

public class FabricChompArrowItem extends ArrowItem {
    public FabricChompArrowItem(Properties p_41383_) {
        super(p_41383_);
        DispenserBlock.registerBehavior(this, new ProjectileDispenseBehavior(this));
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        ammo.shrink(1);
        return new FabricArrowOfChompingEntity(FabricRegister.ARROW_OF_CHOMPING_ENTITY, shooter, level, ammo, weapon);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        FabricArrowOfChompingEntity arrowOfChompingEntity = new FabricArrowOfChompingEntity(FabricRegister.ARROW_OF_CHOMPING_ENTITY, pos.x(), pos.y(), pos.z(), level, stack.copyWithCount(1), new ItemStack(Items.BOW));
        arrowOfChompingEntity.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrowOfChompingEntity;
    }
}
