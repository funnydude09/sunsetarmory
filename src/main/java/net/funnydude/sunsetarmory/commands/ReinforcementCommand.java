package net.funnydude.sunsetarmory.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelEntity;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
import net.funnydude.sunsetarmory.entity.wizards.paladin.PaladinEntity;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ReinforcementCommand {

    private static final SimpleCommandExceptionType INVALID_POSITION = new SimpleCommandExceptionType(Component.translatable("commands.summon.invalidPosition"));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
                dispatcher.register(Commands.literal("reinforce")
                        .requires((commandSourceStack) -> commandSourceStack.hasPermission(2))
                        .then(Commands.argument("casters", EntityArgument.entities())
                                .then(Commands.literal("sunset")
                                        .then(Commands.literal("light").executes((commandSourceStack)-> SunsetLight(commandSourceStack.getSource(),commandSourceStack.getSource().getPosition())))
                                        .then(Commands.literal("medium").executes((commandSourceStack)->SunsetMedium(commandSourceStack.getSource(),commandSourceStack.getSource().getPosition())))
                                        .then(Commands.literal("heavy").executes((commandSourceStack)->SunsetHeavy(commandSourceStack.getSource(),commandSourceStack.getSource().getPosition()))))
                                .then(Commands.literal("unnamed")
                                        .then(Commands.literal("light").executes((commandSourceStack)-> SunsetLight(commandSourceStack.getSource(),commandSourceStack.getSource().getPosition())))
                                        .then(Commands.literal("medium").executes((commandSourceStack)->SunsetMedium(commandSourceStack.getSource(),commandSourceStack.getSource().getPosition())))
                                        .then(Commands.literal("heavy").executes((commandSourceStack)->SunsetHeavy(commandSourceStack.getSource(),commandSourceStack.getSource().getPosition()))))));

    }

    public static int SunsetHeavy(CommandSourceStack source, Vec3 pos) throws CommandSyntaxException {
        BlockPos blockpos = BlockPos.containing(pos);
        if (!Level.isInSpawnableBounds(blockpos)) {
            throw INVALID_POSITION.create();
        } else {
            ServerLevel serverlevel = source.getLevel();
            int i,j,k;
            for(i=0;i<1;i++){
                ArchangelEntity archangel = new ArchangelEntity(serverlevel);
                archangel.setPos(pos);
                serverlevel.addFreshEntity(archangel);
            }
            for(j=0;j<5;j++){
                PaladinEntity paladin = new PaladinEntity(serverlevel);
                paladin.setPos(pos);
                serverlevel.addFreshEntity(paladin);
            }
            for(k=0;k<9;k++){
                KnightEntity knight = new KnightEntity(serverlevel);
                knight.setPos(pos);
                serverlevel.addFreshEntity(knight);
            }
        }
        return 1;
    }

    public static int SunsetMedium(CommandSourceStack source, Vec3 pos) throws CommandSyntaxException {
        BlockPos blockpos = BlockPos.containing(pos);
        if (!Level.isInSpawnableBounds(blockpos)) {
            throw INVALID_POSITION.create();
        } else {
            ServerLevel serverlevel = source.getLevel();
            int j,k;
            for(j=0;j<3;j++){
                PaladinEntity paladin = new PaladinEntity(serverlevel);
                paladin.setPos(pos);
                serverlevel.addFreshEntity(paladin);
            }
            for(k=0;k<7;k++){
                KnightEntity knight = new KnightEntity(serverlevel);
                knight.setPos(pos);
                serverlevel.addFreshEntity(knight);
            }
        }
        return 1;
    }
    public static int SunsetLight(CommandSourceStack source, Vec3 pos) throws CommandSyntaxException {
        BlockPos blockpos = BlockPos.containing(pos);
        if (!Level.isInSpawnableBounds(blockpos)) {
            throw INVALID_POSITION.create();
        } else {
            ServerLevel serverlevel = source.getLevel();
            int j,k;
            for(j=0;j<1;j++){
                PaladinEntity paladin = new PaladinEntity(serverlevel);
                paladin.setPos(pos);
                serverlevel.addFreshEntity(paladin);
            }
            for(k=0;k<5;k++){
                KnightEntity knight = new KnightEntity(serverlevel);
                knight.setPos(pos);
                serverlevel.addFreshEntity(knight);
            }
        }
        return 1;
    }
}

