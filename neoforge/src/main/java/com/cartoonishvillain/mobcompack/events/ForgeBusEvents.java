package com.cartoonishvillain.mobcompack.events;

import com.cartoonishvillain.mobcompack.Constants;
import com.cartoonishvillain.mobcompack.NeoForgeRegister;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class ForgeBusEvents {

    @SubscribeEvent
    public static void ArmorEffects (PlayerTickEvent.Post event) {
        if(event.getEntity() instanceof ServerPlayer && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem().equals(NeoForgeRegister.SYMBOLGLUTTONY.get())) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 1, false, false));
        }

        if(event.getEntity() instanceof ServerPlayer && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem().equals(NeoForgeRegister.ROSETINTEDMONOCLE.get())) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));
        }
    }
}
