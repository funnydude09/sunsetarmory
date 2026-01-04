package net.funnydude.sunsetarmory.block;

import com.mojang.serialization.MapCodec;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.funnydude.sunsetarmory.entity.wizards.paladin.PaladinEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class AwoogaBlock extends BaseEntityBlock {

    public AwoogaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TRIGGERED, Boolean.valueOf(false)));
    }

    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        boolean flag = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
        boolean flag1 = state.getValue(TRIGGERED);
        if (flag && !flag1) {
            level.scheduleTick(pos, this, 4);
            level.setBlock(pos, state.setValue(TRIGGERED, Boolean.valueOf(true)), 2);
        } else if (!flag && flag1) {
            level.setBlock(pos, state.setValue(TRIGGERED, Boolean.valueOf(false)), 2);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.summonReinforcement(state,level,pos);
        super.tick(state, level, pos, random);
    }

    public double getRangedRandom(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    protected void summonReinforcement(BlockState state,ServerLevel level,BlockPos pos){
        Vec3 position = new Vec3(pos.getX(),pos.getY()+1,pos.getZ());
        double randRot = getRangedRandom(-Math.PI,Math.PI);
        Vec3 spawnPosition = Utils.moveToRelativeGroundLevel(level, position.add(new Vec3(5*Mth.cos((float) randRot), 0, 5 * Mth.sin((float) randRot))), 5);
        if(state.getValue(TRIGGERED)){
            PaladinEntity paladin = new PaladinEntity(level);
            paladin.setPos(spawnPosition);
            level.addFreshEntity(paladin);
        }
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TRIGGERED);
    }
}

