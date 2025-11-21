package net.funnydude.sunsetarmory.spell.holy;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.PI;

@AutoSpellConfig
public class GrandDivineSmiteSpell extends AbstractSpell {

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster))
        );
    }

   private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.HOLY_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(5)
            .build();


    public GrandDivineSmiteSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 10;
        this.baseManaCost = 35;
        this.castTime = 100;
    }

    private Vec3 rotateVector(Vec3 vec, double theta){
        double a = vec.x;
        double b = vec.y;
        double c = vec.z;
        double xPrime = a*Math.cos(theta)
                - c*Math.sin(theta);
        double zPrime = c*Math.cos(theta)
                + a*Math.sin(theta);
        return new Vec3(xPrime, b, zPrime);
    }



   @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = 2.2f;
       //float range = 1.7f;
        Vec3 rotatedVectorCC1 = rotateVector(entity.getForward(), PI/4).scale(8);
        Vec3 rotatedVectorCC2 = rotateVector(entity.getForward(), -PI/4).scale(8);
        Vec3 rotatedVectorCC3 = rotateVector(entity.getForward(), 3*PI/4).scale(8);
        Vec3 rotatedVectorCC4 = rotateVector(entity.getForward(), -3*PI/4).scale(8);
       Vec3 smiteLocation1 = Utils.moveToRelativeGroundLevel(level, entity.getEyePosition().add(rotatedVectorCC1), 10);
       Vec3 smiteLocation2 = Utils.moveToRelativeGroundLevel(level, entity.getEyePosition().add(rotatedVectorCC2), 10);
       Vec3 smiteLocation3 = Utils.moveToRelativeGroundLevel(level, entity.getEyePosition().add(rotatedVectorCC3), 10);
       Vec3 smiteLocation4 = Utils.moveToRelativeGroundLevel(level, entity.getEyePosition().add(rotatedVectorCC4), 10);
       Vec3 smiteLocation  = entity.position();

       Vec3 particleLocation = level.clip(new ClipContext(smiteLocation, smiteLocation.add(0, 1, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);
       MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.HOLY.get().getTargetingColor(), 5),
               particleLocation.x, particleLocation.y, particleLocation.z, 1, 0, 0, 0, 0, true);
       MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, particleLocation.x, particleLocation.y, particleLocation.z, 50, 0, 0, 0, 1, false);

       var entities = level.getEntities(entity, AABB.ofSize(smiteLocation, 5, 3, 5));
       var damageSource = this.getDamageSource(entity);

       for (Entity targetEntity : entities) {
           if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
               if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource)) {
                   EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
               }
           }
       }

       new Thread(() -> {
           try {
               Thread.sleep(1000);
               Vec3 particleLocation1 = level.clip(new ClipContext(smiteLocation1, smiteLocation1.add(0, 1, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);
               MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.HOLY.get().getTargetingColor(), 3),
                       particleLocation1.x, particleLocation1.y, particleLocation1.z, 1, 0, 0, 0, 0, true);
               MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, particleLocation1.x, particleLocation1.y, particleLocation1.z, 50, 0, 0, 0, 1, false);

               Vec3 particleLocation2 = level.clip(new ClipContext(smiteLocation2, smiteLocation2.add(0, 1, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);
               MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.HOLY.get().getTargetingColor(), 3),
                       particleLocation2.x, particleLocation2.y, particleLocation2.z, 1, 0, 0, 0, 0, true);
               MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, particleLocation2.x, particleLocation2.y, particleLocation2.z, 50, 0, 0, 0, 1, false);

               Vec3 particleLocation3 = level.clip(new ClipContext(smiteLocation3, smiteLocation3.add(0, 1, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);
               MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.HOLY.get().getTargetingColor(), 3),
                       particleLocation3.x, particleLocation3.y, particleLocation3.z, 1, 0, 0, 0, 0, true);
               MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, particleLocation3.x, particleLocation3.y, particleLocation3.z, 50, 0, 0, 0, 1, false);

               Vec3 particleLocation4 = level.clip(new ClipContext(smiteLocation4, smiteLocation4.add(0, 1, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);
               MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.HOLY.get().getTargetingColor(), 3),
                       particleLocation4.x, particleLocation4.y, particleLocation4.z, 1, 0, 0, 0, 0, true);
               MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, particleLocation4.x, particleLocation4.y, particleLocation4.z, 50, 0, 0, 0, 1, false);

               var entities1 = level.getEntities(entity, AABB.ofSize(smiteLocation1, 3, 2, 3));
               for (Entity targetEntity : entities1) {
                   if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation1.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                       if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource)) {
                           EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                       }
                   }
               }
               var entities2 = level.getEntities(entity, AABB.ofSize(smiteLocation2, 3, 2, 3));
               for (Entity targetEntity : entities2) {
                   if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation2.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                       if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource)) {
                           EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                       }
                   }
               }
               var entities3 = level.getEntities(entity, AABB.ofSize(smiteLocation3, 3, 2, 3));
               for (Entity targetEntity : entities3) {
                   if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation3.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                       if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource)) {
                           EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                       }
                   }
               }
               var entities4 = level.getEntities(entity, AABB.ofSize(smiteLocation4, 3, 2, 3));
               for (Entity targetEntity : entities4) {
                   if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation4.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                       if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource)) {
                           EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                       }
                   }
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }).start();
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundRegistry.DIVINE_SMITE_WINDUP.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.DIVINE_SMITE_CAST.get());
    }

    @Override
    public ResourceLocation getSpellResource() {
        return SunsetArmory.id("grand_divine_smite_spell");
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity) + getAdditionalDamage(entity);
    }

    private float getAdditionalDamage(LivingEntity entity) {
        if (entity == null) {
            return 0;
        }
        float weaponDamage = Utils.getWeaponDamage(entity);
        var weaponItem = entity.getWeaponItem();
        if (!weaponItem.isEmpty() && weaponItem.has(DataComponents.ENCHANTMENTS)) {
            weaponDamage += Utils.processEnchantment(entity.level(), Enchantments.SMITE, EnchantmentEffectComponents.DAMAGE, weaponItem.get(DataComponents.ENCHANTMENTS));
        }
        return weaponDamage;
    }


    private String getDamageText(int spellLevel, LivingEntity entity) {
        if (entity != null) {
            float weaponDamage = getAdditionalDamage(entity);
            String plus = "";
            if (weaponDamage > 0) {
                plus = String.format(" (+%s)", Utils.stringTruncation(weaponDamage, 1));
            }
            String damage = Utils.stringTruncation(getDamage(spellLevel, entity), 1);
            return damage + plus;
        }
        return "" + getSpellPower(spellLevel, entity);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.OVERHEAD_MELEE_SWING_ANIMATION;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }
}


