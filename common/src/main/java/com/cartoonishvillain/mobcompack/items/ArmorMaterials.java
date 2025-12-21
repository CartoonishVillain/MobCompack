package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.platform.Services;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;

public class ArmorMaterials {
    public static final ArmorMaterial GLUTTONY = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 0);
        map.put(ArmorItem.Type.CHESTPLATE, 0);
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.BODY, 0);
    }),
            8,
    SoundEvents.ARMOR_EQUIP_GENERIC,
            ()-> Ingredient.of(Services.PLATFORM.getGiantTooth()),
            List.of(),
            0f,
            0f
            );

    public static final ArmorMaterial RMONICLE = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 8);
            }),
            8,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            ()-> Ingredient.of(Items.NETHERITE_INGOT),
            List.of(),
            0f,
            0f
    );
}
