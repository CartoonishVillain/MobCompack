package com.cartoonishvillain.mobcompack.entity.bop;

import com.cartoonishvillain.mobcompack.platform.Services;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ArrowOfChompingEntity extends AbstractArrow {

    public ArrowOfChompingEntity(EntityType<? extends AbstractArrow> p_36721_, Level p_36722_, ItemStack itemStack) {
        super(p_36721_, p_36722_, itemStack);
        this.setBaseDamage(this.getBaseDamage() + 1f);
    }

    public ArrowOfChompingEntity(EntityType<? extends AbstractArrow> p_36711_, double p_36712_, double p_36713_, double p_36714_, Level p_36715_, ItemStack itemStack) {
        super(p_36711_, p_36712_, p_36713_, p_36714_, p_36715_, itemStack);
        this.setPos(new Vec3(p_36712_, p_36713_, p_36714_));
        this.setBaseDamage(this.getBaseDamage() + 1f);
    }

    public ArrowOfChompingEntity(EntityType<? extends AbstractArrow> p_36717_, LivingEntity p_36718_, Level p_36719_, ItemStack itemStack) {
        super(p_36717_, p_36718_, p_36719_, itemStack);
        this.setBaseDamage(this.getBaseDamage() + 1f);
    }

    public ArrowOfChompingEntity(EntityType<ArrowOfChompingEntity> arrowOfChompingEntityEntityType, Level level) {
        super(arrowOfChompingEntityEntityType, level, new ItemStack(Services.PLATFORM.getArrowOfChompingItem()));
        this.setBaseDamage(this.getBaseDamage() + 1f);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Services.PLATFORM.getArrowOfChompingItem());
    }
}
