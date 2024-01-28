package com.cartoonishvillain.mobcompack;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeoForgeConfig
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue CRYSTALSLIMETHORNSTOGGLECONFIG = BUILDER
            .comment("Determines whether or not Crystalline Slimes have thorns or not.")
            .define("crystalSlimeThornToggle", true);

    private static final ModConfigSpec.IntValue CRYSTALSLIMETHORNSPERCENTCONFIG = BUILDER
            .comment("Changes the odds that Crystalline Slime Thorns will trigger")
            .defineInRange("crystalSlimeThornPercent", 35, 0, 100);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean CRYSTALSLIMETHORNSTOGGLE;
    public static int CRYSTALSLIMETHORNSPERCENT;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        CRYSTALSLIMETHORNSTOGGLE = CRYSTALSLIMETHORNSTOGGLECONFIG.get();
        CRYSTALSLIMETHORNSPERCENT = CRYSTALSLIMETHORNSPERCENTCONFIG.get();
    }
}
