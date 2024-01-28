package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.client.renderer.HammerRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Tier;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class ForgeHammer extends Hammer {

    private static final String CONTROLLER_NAME = "hammerController";
    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation CHOMP = RawAnimation.begin().thenPlay("chomp");

    public ForgeHammer(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42961_, p_42962_, p_42963_, p_42964_);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    private PlayState predicate(AnimationState<Hammer> event) {
        if (!event.isMoving() && Minecraft.useFancyGraphics()) {
            return event.setAndContinue(CHOMP);
        }
        return PlayState.STOP;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new HammerRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, CONTROLLER_NAME, 20, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
}
