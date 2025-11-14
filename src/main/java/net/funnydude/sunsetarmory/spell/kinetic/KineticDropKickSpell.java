package net.funnydude.sunsetarmory.spell.kinetic;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.spell.ModSchools;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.unit;

@AutoSpellConfig
public class KineticDropKickSpell extends AbstractSpell {

    public KineticDropKickSpell() {
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 5;
        this.baseManaCost = 40;
        this.manaCostPerLevel = 30;
        this.castTime = 0;
    }
    public DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(ModSchools.KINETIC_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(60)
            .build();

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        return Utils.preCastTargetHelper(level, entity, playerMagicData, this, 32, .35f);
    }
    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData castTargetingData) {
            LivingEntity target = castTargetingData.getTarget((ServerLevel) level);
            if(target !=null){
                Vec3 subtract = target.getForward().scale(3);
                Utils.handleSpellTeleport(this,entity,target.position().add(subtract));
                entity.lookAt(EntityAnchorArgument.Anchor.EYES, target.getEyePosition());
                float radius = 2.2f;
                float range = 0.5f;
                Vec3 smiteLocation = Utils.raycastForBlock(level, entity.getEyePosition().add(entity.getForward()), entity.getEyePosition().add(entity.getForward().multiply(range, 0, range)), ClipContext.Fluid.NONE).getLocation();
                //Vec3 particleLocation = level.clip(new ClipContext(smiteLocation, smiteLocation.add(0, -2, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);
                //MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.HOLY.get().getTargetingColor(), 2),
                        //particleLocation.x, particleLocation.y, particleLocation.z, 1, 0, 0, 0, 0, true);
                //MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, particleLocation.x, particleLocation.y, particleLocation.z, 50, 0, 0, 0, 1, false);
                //CameraShakeManager.addCameraShake(new CameraShakeData(10, particleLocation, 10));
                new Thread(() -> {
                    try {
                        Thread.sleep(700);
                        var entities = level.getEntities(entity, AABB.ofSize(smiteLocation, radius*2,  radius*4, radius*2));
                        var damageSource = this.getDamageSource(entity);
                        for (Entity targetEntity : entities) {
                            if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                                if (DamageSources.applyDamage(targetEntity, 10, damageSource)) {
                                    EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }


    @Override
    public ResourceLocation getSpellResource() {
        return SunsetArmory.id("kinetic_drop_kick_spell");
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }
}
