package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.MobCompack;
import com.cartoonishvillain.mobcompack.Register;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ArmorMaterials implements ArmorMaterial {
    GLUTTONY(MobCompack.MODID + ":gluttony", 2048, new int[] {2, 0, 0, 1}, 8, SoundEvents.ARMOR_EQUIP_GENERIC, 0, ()->{return Ingredient.of(Register.GIANTTOOTH.get());}, 0.0f),
    RMONICLE(MobCompack.MODID + ":monicle", 33, new int[]{3, 6, 8, 3}, 8, SoundEvents.ARMOR_EQUIP_GENERIC, 0, ()->{return Ingredient.of(Items.NETHERITE_INGOT);}, 0.0f);

    private static final int[] MAX_DAMAGE_ARRAY = new int[] {11, 16, 15, 13};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;
    private final float knockbackres;

    ArmorMaterials(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial, float knockbackres){
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
        this.knockbackres = knockbackres;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type p_266807_) {
        return MAX_DAMAGE_ARRAY[p_266807_.ordinal()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type p_267168_) {
        return this.damageReductionAmountArray[p_267168_.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackres;
    }
}
