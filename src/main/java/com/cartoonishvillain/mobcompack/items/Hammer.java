package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.Tags;
import com.cartoonishvillain.mobcompack.client.renderer.HammerRenderer;
import com.cartoonishvillain.mobcompack.entity.bop.CrystallineSlime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class Hammer extends DiggerItem implements GeoItem {
    private static final String CONTROLLER_NAME = "hammerController";
    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation CHOMP = RawAnimation.begin().thenPlay("chomp");

    public Hammer(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42963_, p_42962_, p_42961_, Tags.MINEABLE_WITH_JAWHAMMER, p_42964_);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        if(!player.level().isClientSide) {
            BlockHitResult lookingAt = (BlockHitResult) player.pick(16, 1, false);
            if(itemstack.isCorrectToolForDrops(player.level().getBlockState(pos)) && lookingAt.getType().equals(HitResult.Type.BLOCK)) {
                Direction pointTowards = lookingAt.getDirection();
                threeByThree(itemstack, pos, player, pointTowards);
            }
        }
        return super.onBlockStartBreak(itemstack, pos, player);
    }

    public void threeByThree(ItemStack itemStack, BlockPos initialPos, Player player, Direction pointTowards) {
        ServerLevel level = (ServerLevel) player.level();
        switch (pointTowards) {
            case UP:
            case DOWN:
                for (int x = initialPos.getX()-1; x <= initialPos.getX()+1; x++) {
                    for (int z = initialPos.getZ()-1; z <= initialPos.getZ()+1; z++) {
                        if (itemStack.getMaxDamage() > itemStack.getDamageValue()) {
                            BlockPos pos = new BlockPos(x, initialPos.getY(), z);
                            BlockState state = level.getBlockState(pos);
                            if (state.equals(Blocks.AIR.defaultBlockState())) continue;
                            if (itemStack.isCorrectToolForDrops(state)) {
                                LootParams.Builder builder = new LootParams.Builder(level).withParameter(LootContextParams.TOOL, itemStack).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
                                state.getDrops(builder).forEach(value -> {
                                    Block.popResource(level, pos, value);
                                });
                                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                                itemStack.hurtAndBreak(1, player, p_41007_ -> {
                                    p_41007_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                });
                            }
                        }
                    }
                }
                break;
            case EAST:
            case WEST:
                for (int y = initialPos.getY()-1; y <= initialPos.getY()+1; y++) {
                    for (int z = initialPos.getZ()-1; z <= initialPos.getZ()+1; z++) {
                        if (itemStack.getMaxDamage() > itemStack.getDamageValue()) {
                            BlockPos pos = new BlockPos(initialPos.getX(), y, z);
                            BlockState state = level.getBlockState(pos);
                            if (state.equals(Blocks.AIR.defaultBlockState())) continue;
                            if (itemStack.isCorrectToolForDrops(state)) {
                                LootParams.Builder builder = new LootParams.Builder(level).withParameter(LootContextParams.TOOL, itemStack).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
                                state.getDrops(builder).forEach(value -> {
                                    Block.popResource(level, pos, value);
                                });
                                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                                itemStack.hurtAndBreak(1, player, p_41007_ -> {
                                    p_41007_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                });
                            }
                        }
                    }
                }
                break;
            case NORTH:
            case SOUTH:
                for (int x = initialPos.getX()-1; x <= initialPos.getX()+1; x++) {
                    for (int y = initialPos.getY()-1; y <= initialPos.getY()+1; y++) {
                        if (itemStack.getMaxDamage() > itemStack.getDamageValue()) {
                            BlockPos pos = new BlockPos(x, y, initialPos.getZ());
                            BlockState state = level.getBlockState(pos);
                            if (state.equals(Blocks.AIR.defaultBlockState())) continue;
                            if (itemStack.isCorrectToolForDrops(state)) {
                                LootParams.Builder builder = new LootParams.Builder(level).withParameter(LootContextParams.TOOL, itemStack).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
                                state.getDrops(builder).forEach(value -> {
                                    Block.popResource(level, pos, value);
                                });
                                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                                itemStack.hurtAndBreak(1, player, p_41007_ -> {
                                    p_41007_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                });
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
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
