package com.cartoonishvillain.mobcompack.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonConfig {
    public static final String CATEGORY_NUMBERS = "Spawn Weights";

    public ConfigHelper.ConfigValueListener<Integer> CRYSTALSLIME;
    public ConfigHelper.ConfigValueListener<Integer> JAWBREAKER;

    public CommonConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        builder.comment("Modify spawn weights").push(CATEGORY_NUMBERS);
        this.CRYSTALSLIME = subscriber.subscribe(builder.comment("Changes natural spawn weight of Jaw Breakers").defineInRange("crystalslimespawn", 15, 0, 1000));
        this.JAWBREAKER = subscriber.subscribe(builder.comment("Changes natural spawn weight of Jaw Breakers").defineInRange("jawbreakerspawn", 10, 0, 1000));
        builder.pop();
    }

}
