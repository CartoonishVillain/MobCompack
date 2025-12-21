package com.cartoonishvillain.mobcompack.items;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

public abstract class RoseTintedMonocle extends ArmorItem implements GeoItem {
    public RoseTintedMonocle(ArmorMaterial materialIn, Type slot, Properties builder) {
        super(BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(materialIn), slot, builder);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }
}
