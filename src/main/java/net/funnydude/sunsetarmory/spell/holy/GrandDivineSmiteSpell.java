package net.funnydude.sunsetarmory.spell.holy;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
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
import java.util.Optional;

import static java.lang.Math.PI;

@AutoSpellConfig
public class GrandDivineSmiteSpell extends AbstractSpell {

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

    private static Vec3 rotateVectorCC(Vec3 vec){
        double a = vec.x;
        double b = vec.y;
        double c = vec.z;

        double xPrime = 0*(0*a + 1*b + 0*c)*(1d - Math.cos(PI/4))
                + a*Math.cos(PI/4)
                + (-0*b + 1*c)*Math.sin(PI/4);


        double zPrime = 0*(0*a + 1*b + 0*c)*(1d - Math.cos(PI/4))
                + c*Math.cos(PI/4)
                + (-1*a + 0*b)*Math.sin(PI/4);

        return new Vec3(xPrime, b, zPrime);
    }

   @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = 2.2f;
        float range = 1.7f;
       Vec3 smiteLocation1 = Utils.raycastForBlock(level, rotateVectorCC(Utils.getPositionFromEntityLookDirection(entity,5)), rotateVectorCC(Utils.getPositionFromEntityLookDirection(entity,5)).add(rotateVectorCC(entity.getForward()).multiply(range, 0, range)), ClipContext.Fluid.NONE).getLocation();
       Vec3 particleLocation1 = level.clip(new ClipContext(smiteLocation1, smiteLocation1.add(0, -2, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);
       MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.HOLY.get().getTargetingColor(), radius * 2),
               particleLocation1.x, particleLocation1.y, particleLocation1.z, 1, 0, 0, 0, 0, true);
       MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, particleLocation1.x, particleLocation1.y, particleLocation1.z, 50, 0, 0, 0, 1, false);
       var entities = level.getEntities(entity, AABB.ofSize(smiteLocation1, radius * 2, radius * 4, radius * 2));
       var damageSource = this.getDamageSource(entity);
       for (Entity targetEntity : entities) {
           if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation1.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
               if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource)) {
                   EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
               }
           }

       }

        Vec3 smiteLocation2 = Utils.raycastForBlock(level, rotateVectorCC(Utils.getPositionFromEntityLookDirection(entity,5)), rotateVectorCC(Utils.getPositionFromEntityLookDirection(entity,5)).add(rotateVectorCC(entity.getForward()).multiply(range, 0, range)), ClipContext.Fluid.NONE).getLocation();
        Vec3 particleLocation2 = level.clip(new ClipContext(smiteLocation2, smiteLocation2.add(0, -2, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.HOLY.get().getTargetingColor(), radius * 2),
                particleLocation2.x, particleLocation2.y, particleLocation2.z, 1, 0, 0, 0, 0, true);
       MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, particleLocation2.x, particleLocation2.y, particleLocation2.z, 50, 0, 0, 0, 1, false);
       var entities1 = level.getEntities(entity, AABB.ofSize(smiteLocation2, radius * 2, radius * 4, radius * 2));
       var damageSource1 = this.getDamageSource(entity);
       for (Entity targetEntity : entities1) {
           if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation2.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
               if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource1)) {
                   EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource1);
               }
           }

       }
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
        return SunsetArmory.id("grand_divine_smite");
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


