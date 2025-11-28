package net.funnydude.sunsetarmory.item.custom;

import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;



public class SmokeBomb extends Item {
    public SmokeBomb(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        player.getCooldowns().addCooldown(this, 20*5);
        if(!level.isClientSide){
            player.addEffect(new MobEffectInstance(MobEffectRegistry.TRUE_INVISIBILITY,100));
            player.addEffect(new MobEffectInstance(MobEffectRegistry.EVASION,60));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,100));
            itemInHand.consume(1, player);
            ((ServerLevel)level).sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    player.getX(),player.getY()+1,player.getZ(),1000,0,-1,0,0.025);
            ((ServerLevel)level).sendParticles(ParticleRegistry.EMBEROUS_ASH_PARTICLE.get(),
                    player.getX(),player.getY()+1,player.getZ(),500,0,-1,0,0.025);
            player.playSound(SoundRegistry.BOSS_STANCE_BREAK.get(), 5, 2);
        }
        return InteractionResultHolder.sidedSuccess(itemInHand, level.isClientSide());
    }
}

