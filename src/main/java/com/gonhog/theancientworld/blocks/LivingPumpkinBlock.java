package com.gonhog.theancientworld.blocks;

import com.gonhog.theancientworld.entities.dirt_golem.DirtGolemEntity;
import com.gonhog.theancientworld.entities.wood_golem.WoodGolemEntity;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockMaterialMatcher;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Predicate;

public class LivingPumpkinBlock extends CarvedPumpkinBlock {
    public LivingPumpkinBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    @Nullable
    private BlockPattern dirtGolemBasePattern;
    @Nullable
    private BlockPattern dirtGolemPattern;
    @Nullable
    private BlockPattern woodGolemBasePattern;
    @Nullable
    private BlockPattern woodGolemPattern;
    @Nullable
    private BlockPattern stoneGolemBasePattern;
    @Nullable
    private BlockPattern stoneGolemPattern;
    @Nullable
    private BlockPattern sandGolemBasePattern;
    @Nullable
    private BlockPattern sandGolemPattern;

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.isIn(state.getBlock())) {
            this.trySpawnGolem(worldIn, pos);
        }
    }

    private static final Predicate<BlockState> IS_LIVING_PUMPKIN = Objects::nonNull;

    public boolean canDispenserPlace(IWorldReader reader, BlockPos pos) {
        return this.getDirtGolemBasePattern().match(reader, pos) != null || this.getDirtGolemBasePattern().match(reader, pos) != null;
    }

    private void trySpawnGolem(World world, BlockPos pos) {

        BlockPattern.PatternHelper dirtGolemPattern = this.getDirtGolemPattern().match(world, pos);
        BlockPattern.PatternHelper woodGolemPattern = this.getWoodGolemPattern().match(world, pos);

        if (dirtGolemPattern != null) {
            for(int j = 0; j < this.getDirtGolemPattern().getPalmLength(); ++j) {
                for(int k = 0; k < this.getDirtGolemPattern().getThumbLength(); ++k) {
                    CachedBlockInfo cachedblockinfo2 = dirtGolemPattern.translateOffset(j, k, 0);
                    world.setBlockState(cachedblockinfo2.getPos(), Blocks.AIR.getDefaultState(), 2);
                    world.playEvent(2001, cachedblockinfo2.getPos(), Block.getStateId(cachedblockinfo2.getBlockState()));
                }
            }

            DirtGolemEntity dirtgolem = RegistryHandler.DIRT_GOLEM.get().create(world);
            BlockPos blockpos1 = dirtGolemPattern.translateOffset(1, 2, 0).getPos();
            assert dirtgolem != null;
            dirtgolem.setLocationAndAngles((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.05D, (double)blockpos1.getZ() + 0.5D, 0.0F, 0.0F);
            world.addEntity(dirtgolem);

            for(ServerPlayerEntity serverplayerentity : world.getEntitiesWithinAABB(ServerPlayerEntity.class, dirtgolem.getBoundingBox().grow(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, dirtgolem);
            }

            for(int l = 0; l < this.getDirtGolemPattern().getThumbLength(); ++l) {
                CachedBlockInfo cachedblockinfo3 = dirtGolemPattern.translateOffset(0, l, 0);
                world.func_230547_a_(cachedblockinfo3.getPos(), Blocks.AIR);
            }
        } else  if (woodGolemPattern != null) {
            for(int j = 0; j < this.getWoodGolemPattern().getPalmLength(); ++j) {
                for(int k = 0; k < this.getWoodGolemPattern().getThumbLength(); ++k) {
                    CachedBlockInfo cachedblockinfo2 = woodGolemPattern.translateOffset(j, k, 0);
                    world.setBlockState(cachedblockinfo2.getPos(), Blocks.AIR.getDefaultState(), 2);
                    world.playEvent(2001, cachedblockinfo2.getPos(), Block.getStateId(cachedblockinfo2.getBlockState()));
                }
            }

            WoodGolemEntity woodgolem = RegistryHandler.WOOD_GOLEM.get().create(world);
            BlockPos blockpos1 = woodGolemPattern.translateOffset(1, 2, 0).getPos();
            assert woodgolem != null;
            woodgolem.setLocationAndAngles((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.05D, (double)blockpos1.getZ() + 0.5D, 0.0F, 0.0F);
            world.addEntity(woodgolem);

            for(ServerPlayerEntity serverplayerentity : world.getEntitiesWithinAABB(ServerPlayerEntity.class, woodgolem.getBoundingBox().grow(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, woodgolem);
            }

            for(int l = 0; l < this.getWoodGolemPattern().getThumbLength(); ++l) {
                CachedBlockInfo cachedblockinfo3 = woodGolemPattern.translateOffset(0, l, 0);
                world.func_230547_a_(cachedblockinfo3.getPos(), Blocks.AIR);
            }
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private BlockPattern getDirtGolemBasePattern() {
        if (this.dirtGolemBasePattern == null) {
            this.dirtGolemBasePattern = BlockPatternBuilder.start().aisle(" ", "#", "#").where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.DIRT))).build();
        }

        return this.dirtGolemBasePattern;
    }

    private BlockPattern getDirtGolemPattern() {
        if (this.dirtGolemPattern == null) {
            this.dirtGolemPattern = BlockPatternBuilder.start().aisle("~^~", "###", "###", "~#~")
                    .where('^', CachedBlockInfo.hasState(IS_LIVING_PUMPKIN))
                    .where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.DIRT)))
                    .where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)))
                    .build();
        }
        return this.dirtGolemPattern;
    }

    private BlockPattern getWoodGolemPattern() {
        if (this.woodGolemPattern == null) {
            this.woodGolemPattern = BlockPatternBuilder.start().aisle("~^~", "#G#", "#G#", "~#~")
                    .where('^', CachedBlockInfo.hasState(IS_LIVING_PUMPKIN))
                    .where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.OAK_PLANKS)))
                    .where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)))
                    .where('G', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.GOLD_BLOCK)))
                    .build();
        }
        return this.woodGolemPattern;
    }
    private BlockPattern getSandGolemPattern() {
        if (this.dirtGolemPattern == null) {
            this.dirtGolemPattern = BlockPatternBuilder.start().aisle("~^~", "###", "###", "~#~")
                    .where('^', CachedBlockInfo.hasState(IS_LIVING_PUMPKIN))
                    .where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.SAND)))
                    .where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)))
                    .build();
        }
        return this.sandGolemPattern;
    }
}
