package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.Tags;
import com.cartoonishvillain.mobcompack.client.renderer.HammerRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.IItemRenderProperties;
import software.bernie.example.client.renderer.item.JackInTheBoxRenderer;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class Hammer extends DiggerItem implements IAnimatable, ISyncable {
    private static final int ANIM_OPEN = 0;
    public AnimationFactory factory = new AnimationFactory(this);
    public Hammer(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42963_, p_42962_, p_42961_, Tags.MINEABLE_WITH_JAWHAMMER, p_42964_);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        if(!player.level.isClientSide) {
            BlockHitResult lookingAt = (BlockHitResult) player.pick(16, 1, false);
            if(itemstack.isCorrectToolForDrops(player.level.getBlockState(pos)) && lookingAt.getType().equals(HitResult.Type.BLOCK)) {
                Direction pointTowards = lookingAt.getDirection();
                threeByThree(itemstack, pos, player, pointTowards);
            }
        }
        return super.onBlockStartBreak(itemstack, pos, player);
    }

    public void threeByThree(ItemStack itemStack, BlockPos initialPos, Player player, Direction pointTowards) {
        ServerLevel level = (ServerLevel) player.level;
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
                                LootContext.Builder builder = new LootContext.Builder(level).withParameter(LootContextParams.TOOL, itemStack).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
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
                                LootContext.Builder builder = new LootContext.Builder(level).withParameter(LootContextParams.TOOL, itemStack).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
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
                                LootContext.Builder builder = new LootContext.Builder(level).withParameter(LootContextParams.TOOL, itemStack).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
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

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ANIM_OPEN) {
            // Always use GeckoLibUtil to get AnimationControllers when you don't have
            // access to an AnimationEvent
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "sedimenthammer");

            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("chomp", true));
            }
        }
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new HammerRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }
}
