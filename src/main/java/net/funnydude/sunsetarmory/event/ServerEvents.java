package net.funnydude.sunsetarmory.event;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.funnydude.sunsetarmory.registries.ModEffects;
import net.funnydude.sunsetarmory.registries.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingUseTotemEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import top.theillusivec4.curios.api.event.CurioCanEquipEvent;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void onPlayerCastEvent(SpellPreCastEvent event) {
        Player entity = event.getEntity();
        boolean isImmobile = entity.hasEffect(ModEffects.ARMOR_LOCK_EFFECT);
        if (entity instanceof ServerPlayer player) {
            if (!player.level().isClientSide() && event.getSpellId().equals("grand_divine_smite_spell") && (player.getHealth() > 0.5 * player.getMaxHealth())) {
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
    public static void OnTotemUse(LivingUseTotemEvent event){
        var entity = event.getEntity();
        if(entity instanceof Player player&& hasArmourSet(ModArmorMaterials.RED_TERROR_ARMOR_MATERIAL,player)&& !player.level().isClientSide) {
                player.setItemInHand(event.getHandHolding(), new ItemStack(Items.TOTEM_OF_UNDYING));
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                   player.forceAddEffect(new MobEffectInstance(MobEffectRegistry.EVASION,200),player);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @SubscribeEvent
    public static void reduceDamage(LivingDamageEvent.Pre event) {
        var livingEntity = event.getEntity();
        var sourceEntity = event.getSource().getEntity();
        if(sourceEntity instanceof LivingEntity &&((LivingEntity) sourceEntity).hasEffect(ModEffects.LOVE_TRAIN)){
            ((LivingEntity) sourceEntity).removeEffect(ModEffects.LOVE_TRAIN);
        }
        if(livingEntity instanceof Player player && hasArmourSet(ModArmorMaterials.ROARING_KNIGHT_ARMOR_MATERIAL,player)){
            player.addEffect(new MobEffectInstance(MobEffectRegistry.ABYSSAL_SHROUD,20));
        }
        if(sourceEntity instanceof Player player && hasArmourSet(ModArmorMaterials.FIRST_SUNSET_PV_ARMOR_MATERIAL,player)){
            livingEntity.setRemainingFireTicks(40);
        }
        if (livingEntity instanceof IMagicEntity || livingEntity instanceof ServerPlayer) {
            var playerMagicData = MagicData.getPlayerMagicData(livingEntity);
            if (livingEntity.hasEffect(ModEffects.HALF_STANCE_SWORD_EFFECT)) {
                playerMagicData.getSyncedData().addHeartstopDamage(event.getOriginalDamage() * .8f);
                var damage = event.getOriginalDamage() * .2;
                event.setNewDamage((float) damage);
            }
            if(livingEntity.hasEffect(ModEffects.LOVE_TRAIN)){
                playerMagicData.getSyncedData().addHeartstopDamage(event.getOriginalDamage());
                event.setNewDamage(0);
            }
        }
    }

    @SubscribeEvent
    public static void canEquipCurios(CurioCanEquipEvent event){
        var entity = event.getEntity();
        if(entity.hasEffect(ModEffects.CURIOS_COOL_DOWN_EFFECT) && event.getSlotContext().identifier().equals("banner")){
            event.setEquipResult(TriState.FALSE);
        }
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        var entity = event.getEntity();
        var level = entity.level();

        if(entity instanceof Player player && hasArmourSet(ModArmorMaterials.FIRST_SUNSET_PV_ARMOR_MATERIAL,player)){
            if(player.getRemainingFireTicks()>0) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2,1));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,2,1));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,2,1));
                player.addEffect(new MobEffectInstance(MobEffects.LUCK,2,1));
            }
            if(player.getHealth()<=0.5*player.getMaxHealth()){
                player.setRemainingFireTicks(2);
            }
        }
        if (entity instanceof LivingEntity livingEntity&& livingEntity.hasEffect(ModEffects.COOL_DOWN_EFFECT) && livingEntity.hasEffect(MobEffectRegistry.CHARGED) && level instanceof ServerLevel serverLevel) {
            Vec3 explosionLocation = entity.position();
            var damage = Math.pow(2, livingEntity.getAttribute(AttributeRegistry.FIRE_SPELL_POWER).getValue()+3);
            livingEntity.removeEffect(ModEffects.COOL_DOWN_EFFECT);
            livingEntity.removeEffect(MobEffectRegistry.CHARGED);
            var entities = level.getEntities(entity, AABB.ofSize(explosionLocation, 5, 2, 5));
            serverLevel.sendParticles(ParticleRegistry.FIRE_PARTICLE.get(),
                    entity.getX(),entity.getY(),entity.getZ(),50,0,0,0,0.4);
            serverLevel.sendParticles(ParticleRegistry.FIERY_SMOKE_PARTICLE.get(),
                    entity.getX(),entity.getY(),entity.getZ(),200,0,0,0,0.05);
            serverLevel.sendParticles(ParticleRegistry.EMBEROUS_ASH_PARTICLE.get(),
                    entity.getX(),entity.getY(),entity.getZ(),200,0,0,0,0.5);
            var damageSource = new DamageSource(serverLevel.damageSources().explosion(null).typeHolder(), entity);
            DamageSources.applyDamage(entity, (float) (damage*0.5),damageSource);
            for (Entity targetEntity : entities) {
                if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, explosionLocation.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                    if (DamageSources.applyDamage(targetEntity, (float) damage, damageSource)) {
                        EnchantmentHelper.doPostAttackEffects(serverLevel, targetEntity, damageSource);
                    }
                }
            }
        }
        if (entity instanceof LivingEntity livingEntity&& livingEntity.hasEffect(ModEffects.ADRENALINE_OVERFLOW_EFFECT) && livingEntity.hasEffect(MobEffectRegistry.HEARTSTOP)) {
            livingEntity.removeEffect(ModEffects.ADRENALINE_OVERFLOW_EFFECT);
        }
        if (entity instanceof LivingEntity livingEntity&& livingEntity.hasEffect(ModEffects.ARMOR_LOCK_EFFECT)) {
            if(livingEntity.getAttributeValue(Attributes.JUMP_STRENGTH)>=1){
                livingEntity.removeEffect(MobEffects.JUMP);
            }
        }
        if(entity instanceof Player player && hasArmourSet(ModArmorMaterials.YELLOW_MENACE_ARMOR_MATERIAL,player) && player.getHealth() <= 0.5*player.getMaxHealth()){
            player.addEffect(new MobEffectInstance(ModEffects.ATTACK_SPEED_EFFECT,2,99));
        }
        if(entity instanceof Player player && hasArmourSet(ModArmorMaterials.BLUE_NIGHTMARE_ARMOR_MATERIAL,player)){
            int i = 0;
            var potentialAlly  = level.getEntities(player, AABB.ofSize(player.position(), 5, 2, 5));
            for(Entity potentAlly : potentialAlly){
                if(potentAlly instanceof Player ally){
                    if(SunsetArmory.hasCurios(player, ModItems.SUNSET_BANNER.get()) && !SunsetArmory.hasCurios(ally,ModItems.SUNSET_BANNER.get())){
                        i++;
                    }
                    if(SunsetArmory.hasCurios(player, ModItems.ELDRITCH_CULTIST_BANNER.get()) && !SunsetArmory.hasCurios(ally,ModItems.ELDRITCH_CULTIST_BANNER.get())){
                        i++;
                    }
                    if(SunsetArmory.hasCurios(player, ModItems.BLOOD_CULTIST_BANNER.get()) && !SunsetArmory.hasCurios(ally,ModItems.BLOOD_CULTIST_BANNER.get())){
                        i++;
                    }
                }
            }
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,2,i));
        }
        if(entity instanceof Player player && hasArmourSet(ModArmorMaterials.BLUE_NIGHTMARE_ARMOR_MATERIAL,player)){
            int i = 0;
            var potentialAlly  = level.getEntities(player, AABB.ofSize(player.position(), 5, 2, 5));
            for(Entity potentAlly : potentialAlly){
                if(potentAlly instanceof Player ally){
                    if(SunsetArmory.hasCurios(player, ModItems.SUNSET_BANNER.get()) && !SunsetArmory.hasCurios(ally,ModItems.SUNSET_BANNER.get())){
                        i++;
                    }
                    if(SunsetArmory.hasCurios(player, ModItems.ELDRITCH_CULTIST_BANNER.get()) && !SunsetArmory.hasCurios(ally,ModItems.ELDRITCH_CULTIST_BANNER.get())){
                        i++;
                    }
                    if(SunsetArmory.hasCurios(player, ModItems.BLOOD_CULTIST_BANNER.get()) && !SunsetArmory.hasCurios(ally,ModItems.BLOOD_CULTIST_BANNER.get())){
                        i++;
                    }
                }
            }
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,2,i));
        }
        if(entity instanceof LivingEntity livingEntity&& livingEntity.hasEffect(ModEffects.LOVE_TRAIN) && !(MagicData.getPlayerMagicData(livingEntity).getSyncedData().getHeartstopAccumulatedDamage()==0)){
            var playerMagicData = MagicData.getPlayerMagicData(livingEntity);
            AABB maxRange = AABB.ofSize(entity.position(),10,2,10);
            var target = level.getNearestEntity(LivingEntity.class, TargetingConditions.forCombat().ignoreInvisibilityTesting(), livingEntity, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(),maxRange);
            if(!(target==null)) {
                var damageSource = new DamageSource(target.level().damageSources().dragonBreath().typeHolder(), target);
                DamageSources.applyDamage(target, playerMagicData.getSyncedData().getHeartstopAccumulatedDamage(), damageSource);
                playerMagicData.getSyncedData().setHeartstopAccumulatedDamage(0);
            }
            playerMagicData.getSyncedData().setHeartstopAccumulatedDamage(0);
        }
        if(entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(ModEffects.BURNING_EFFECT)){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1,1));
            livingEntity.igniteForTicks(1);
        }
    }

    public static boolean hasArmourSet(Holder<ArmorMaterial> mapArmorMaterial, Player player) {
        for(ItemStack armorStack : player.getArmorSlots()) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());
        return boots.getMaterial() == mapArmorMaterial && leggings.getMaterial() == mapArmorMaterial
                && chestplate.getMaterial() == mapArmorMaterial && helmet.getMaterial() == mapArmorMaterial;
    }
}
