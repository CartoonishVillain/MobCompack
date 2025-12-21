package com.cartoonishvillain.mobcompack.entity;

import com.cartoonishvillain.mobcompack.FabricRegister;
import com.cartoonishvillain.mobcompack.platform.Services;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class FabricArrowOfChompingEntity extends AbstractArrow {


    public FabricArrowOfChompingEntity(EntityType<? extends AbstractArrow> p_36711_, double p_36712_, double p_36713_, double p_36714_, Level p_36715_, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(p_36711_, p_36712_, p_36713_, p_36714_, p_36715_, new ItemStack(FabricRegister.ARROW_OF_CHOMPING_ITEM), firedFromWeapon);
        this.setPos(new Vec3(p_36712_, p_36713_, p_36714_));
        this.setBaseDamage(this.getBaseDamage() + 1f);
    }

    public FabricArrowOfChompingEntity(EntityType<? extends AbstractArrow> p_36717_, LivingEntity p_36718_, Level p_36719_, ItemStack ammo, @Nullable ItemStack weapon) {
        super(p_36717_, p_36718_, p_36719_, new ItemStack(FabricRegister.ARROW_OF_CHOMPING_ITEM), weapon);
        this.setBaseDamage(this.getBaseDamage() + 1f);
    }

    public FabricArrowOfChompingEntity(EntityType<FabricArrowOfChompingEntity> arrowOfChompingEntityEntityType, Level level) {
        super(arrowOfChompingEntityEntityType, level);
        this.setBaseDamage(this.getBaseDamage() + 1f);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(FabricRegister.ARROW_OF_CHOMPING_ITEM.asItem());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(FabricRegister.ARROW_OF_CHOMPING_ITEM.asItem());
    }
}
