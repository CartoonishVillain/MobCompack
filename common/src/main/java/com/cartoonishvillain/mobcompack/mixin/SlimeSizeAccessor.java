package com.cartoonishvillain.mobcompack.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.monster.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Slime.class)
public interface SlimeSizeAccessor {
    @Accessor("ID_SIZE")
    public static EntityDataAccessor<Integer> MobCompackGetIDSIZE() {
        throw new AssertionError();
    }
}
