package com.cartoonishvillain.mobcompack.events;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.Register;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

@Mod.EventBusSubscriber(modid = MobCompack.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusEvents {

    @SubscribeEvent
    public static void ArmorEffects (TickEvent.PlayerTickEvent event) {
        if(event.player instanceof ServerPlayer && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(Register.SYMBOLGLUTTONY.get())) {
            event.player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 1, false, false));
        }

        if(event.player instanceof ServerPlayer && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(Register.ROSETINTEDMONOCLE.get())) {
            event.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));
        }
    }
}
