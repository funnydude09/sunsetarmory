package net.funnydude.sunsetarmory;

import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.damage.PortalDamageSource;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.funnydude.sunsetarmory.damagetype.ModDamageTypes;
import net.funnydude.sunsetarmory.effect.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void onPlayerCastEvent(SpellPreCastEvent event) {
        Player entity = event.getEntity();
        boolean isImmobile = entity.hasEffect(ModEffects.ARMOR_LOCK_EFFECT);
        if (entity instanceof ServerPlayer player) {
            if (!player.level().isClientSide() && event.getSpellId().equals("grand_divine_smite_spell") && (player.getHealth()>0.5*player.getMaxHealth())) {
                ServerGamePacketListenerImpl serverGamePacketListener = player.connection;
                serverGamePacketListener.send(new ClientboundSetActionBarTextPacket(Component.translatable("spell.sunsetarmory.grand_divine_smite.message").withStyle(ChatFormatting.DARK_RED)));
            }
            if (!player.level().isClientSide() && isImmobile) {
                event.setCanceled(true);
                if (player instanceof ServerPlayer) {
                    ServerGamePacketListenerImpl var10000 = player.connection;
                    var10000.send(new ClientboundSetActionBarTextPacket(Component.translatable("spell.sunsetarmory.armor_lock.message").withStyle(ChatFormatting.WHITE)));
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SHULKER_BOX_OPEN, SoundSource.PLAYERS, 0.5F, 1.0F);
                }
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerCastEvent(EntityTickEvent.Post event){
        var entity = event.getEntity();
        var level = entity.level();
        if(entity instanceof LivingEntity && ((LivingEntity) entity).hasEffect(ModEffects.COOL_DOWN_EFFECT)&& ((LivingEntity) entity).hasEffect(MobEffectRegistry.CHARGED)){
            Vec3 explosionLocation  = entity.position();
            ((LivingEntity)entity).removeEffect(ModEffects.COOL_DOWN_EFFECT);
            ((LivingEntity)entity).removeEffect(MobEffectRegistry.CHARGED);
            var entities = level.getEntities(entity, AABB.ofSize(explosionLocation, 5, 2, 5));
            var damageSource = new DamageSource(entity.level().damageSources().cactus().typeHolder(),entity);
            for (Entity targetEntity : entities) {
                if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, explosionLocation.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                    if (DamageSources.applyDamage(targetEntity,10 , damageSource)) {
                        EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                    }
                }
            }
        }
    }
}
