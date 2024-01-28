package com.cartoonishvillain.mobcompack.mixin;

import com.cartoonishvillain.mobcompack.FabricRegister;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerTickMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    private void MobCompackServerPlayerTick(CallbackInfo info) {
        ServerPlayer player = ((ServerPlayer) (Object) this);
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(FabricRegister.SYMBOLGLUTTONY)) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 1, false, false));
        }

        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(FabricRegister.TINTEDMONOCLE)) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));
        }
    }
}
