package com.cartoonishvillain.mobcompack;

import com.cartoonishvillain.mobcompack.config.DefaultConfig;
import com.cartoonishvillain.mobcompack.config.SimpleConfig;
import com.cartoonishvillain.mobcompack.entity.FabricSpawns;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import com.cartoonishvillain.mobcompack.entity.bop.Jaws;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class FabricMobCompack implements ModInitializer {

    private static final SimpleConfig CONFIG = SimpleConfig.of("mobcompack").provider(DefaultConfig::provider).request();
    public static final boolean TOGGLESLIMETHORNS = CONFIG.getOrDefault("crystalSlimeThornToggle", true);
    public static final int SLIMETHORNCHANCE = CONFIG.getOrDefault("crystalSlimeThornChance", 35);
    
    @Override
    public void onInitialize() {
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        CommonClass.init();


        FabricRegister.init();
        CompackTab.registerTab();
        FabricDefaultAttributeRegistry.register(FabricRegister.JAWBREAKER, Jaws.customAttributes());
        FabricDefaultAttributeRegistry.register(FabricRegister.CRYSTALINESLIME,  CrystallineSlime.customAttributes());
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(Constants.MOD_ID, "crystallineparticle"), FabricRegister.CRYSTAL_PARTICLE);
        FabricSpawns.addSpawns();
    }
}
