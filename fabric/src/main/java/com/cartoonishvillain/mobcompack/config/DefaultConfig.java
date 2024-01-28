package com.cartoonishvillain.mobcompack.config;

public class DefaultConfig {
    public static String provider( String name ) {
        return """
                # Mob Compack Config
                # Determines whether or not Crystalline Slimes have thorns or not.
                crystalSlimeThornToggle=true
                # Changes the odds that Crystalline Slime Thorns will trigger
                # (Range 0-100)
                crystalSlimeThornChance=35
                """;
    }
}
