package net.funnydude.sunsetarmory;

import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.funnydude.sunsetarmory.effect.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.*;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void onPlayerCastEvent(SpellPreCastEvent event) {
        Player entity = event.getEntity();
        boolean isImmobile = entity.hasEffect(ModEffects.ARMOR_LOCK_EFFECT);
        if (entity instanceof ServerPlayer player) {
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
}
