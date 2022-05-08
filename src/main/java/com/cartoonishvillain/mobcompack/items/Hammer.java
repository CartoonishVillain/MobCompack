package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.Tags;
import com.cartoonishvillain.mobcompack.client.renderer.HammerRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.network.PacketDistributor;
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
    private static final String CONTROLLER_NAME = "hammerController";
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
        AnimationController controller = new AnimationController(this, CONTROLLER_NAME, 20, this::predicate);

        data.addAnimationController(controller);

    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);

        if (!p_41405_.isClientSide && p_41406_ instanceof Player && p_41406_.tickCount % 20 == 0) {
            final int id = GeckoLibUtil.guaranteeIDForStack(p_41404_, (ServerLevel) p_41405_);
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> p_41406_);
            GeckoLibNetwork.syncAnimation(target, this, id, 0);
        }
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
            // Always use GeckoLibUtil to get AnimationControllers when you don't have
            // access to an AnimationEvent
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, CONTROLLER_NAME);

            if (controller.getAnimationState() == AnimationState.Stopped && Minecraft.useFancyGraphics()) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("chomp", true));
        } else if (!Minecraft.useFancyGraphics()) {
                controller.setAnimation(new AnimationBuilder().addAnimation("chomp", false));
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
