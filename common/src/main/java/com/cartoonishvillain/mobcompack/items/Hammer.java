package com.cartoonishvillain.mobcompack.items;

import com.cartoonishvillain.mobcompack.Tags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

public abstract class Hammer extends DiggerItem implements GeoItem {
    public Hammer(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42963_, p_42962_, p_42961_, Tags.MINEABLE_WITH_JAWHAMMER, p_42964_);
    }

    @Override
    public boolean mineBlock(ItemStack itemstack, Level level, BlockState blockState, BlockPos pos, LivingEntity player) {
        if(!player.level().isClientSide && player instanceof Player) {
            BlockHitResult lookingAt = (BlockHitResult) player.pick(16, 1, false);
            if(itemstack.isCorrectToolForDrops(blockState) && lookingAt.getType().equals(HitResult.Type.BLOCK)) {
                Direction pointTowards = lookingAt.getDirection();
                threeByThree(itemstack, pos, (Player) player, pointTowards);
            }
        }
        return super.mineBlock(itemstack, level, blockState, pos, player);
    }

    public void threeByThree(ItemStack itemStack, BlockPos initialPos, Player player, Direction pointTowards) {
        ServerLevel level = (ServerLevel) player.level();
        switch (pointTowards) {
            case UP, DOWN -> {
                for (int x = initialPos.getX() - 1; x <= initialPos.getX() + 1; x++) {
                    for (int z = initialPos.getZ() - 1; z <= initialPos.getZ() + 1; z++) {
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
            }
            case EAST, WEST -> {
                for (int y = initialPos.getY() - 1; y <= initialPos.getY() + 1; y++) {
                    for (int z = initialPos.getZ() - 1; z <= initialPos.getZ() + 1; z++) {
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
            }
            case NORTH, SOUTH -> {
                for (int x = initialPos.getX() - 1; x <= initialPos.getX() + 1; x++) {
                    for (int y = initialPos.getY() - 1; y <= initialPos.getY() + 1; y++) {
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
            }
            default -> {
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }
}
