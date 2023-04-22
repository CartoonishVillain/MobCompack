package com.cartoonishvillain.mobcompack.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonConfig {
    public static final String CATEGORY_NUMBERS = "Mob Configs";

    public ConfigHelper.ConfigValueListener<Boolean> CRYSTALSLIMETHORNSTOGGLE;
    public ConfigHelper.ConfigValueListener<Integer> CRYSTALSLIMETHORNSPERCENT;

    public CommonConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        builder.comment("Modify mob parameters").push(CATEGORY_NUMBERS);
        this.CRYSTALSLIMETHORNSTOGGLE = subscriber.subscribe(builder.comment("Determines whether or not Crystalline Slimes have thorns or not.").define("crystalSlimeThornToggle", true));
        this.CRYSTALSLIMETHORNSPERCENT = subscriber.subscribe(builder.comment("Changes the odds that Crystalline Slime Thorns will trigger").defineInRange("jawbreakerspawn", 35, 0, 100));
        builder.pop();
    }

}
