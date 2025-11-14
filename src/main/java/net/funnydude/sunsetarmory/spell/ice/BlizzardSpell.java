package net.funnydude.sunsetarmory.spell.ice;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.spell.BlizzardHail;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class BlizzardSpell extends AbstractSpell {

    private final DefaultConfig defaultConfig = new DefaultConfig()
           .setSchoolResource(SchoolRegistry.ICE_RESOURCE)
           .setMinRarity(SpellRarity.EPIC)
           .setCooldownSeconds(60)
           .setMaxLevel(5)
           .build();

    public BlizzardSpell(){
        this.manaCostPerLevel = 25;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 100;
        this.baseManaCost = 200;
    }

    @Override
    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        if (playerMagicData == null || !(playerMagicData.getAdditionalCastData() instanceof BlizzardCastData castData)) {
            return;
        }
        float radius = 6;
        int tick = playerMagicData.getCastDurationRemaining() - 1;
        if (tick % 20 == 0) {
            castData.updateTrackedEntities(level.getEntities(entity, AABB.ofSize(castData.center, radius * 3, radius, radius * 3), e -> e instanceof LivingEntity && !DamageSources.isFriendlyFireBetween(entity, e)));
        }
        if (tick % 4 == 0)
            for (int i = 0; i < 2; i++) {
                Vec3 center = castData.center;
                Vec3 weightedArea = Vec3.ZERO;
                for (Entity target : castData.trackedEntities) {
                    weightedArea = weightedArea.add(target.position().subtract(center).scale(1f / castData.trackedEntities.size()));
                }
                var spawnRadius = Mth.clampedLerp(radius, radius * .5, weightedArea.length() / radius);
                Vec3 spawnTarget = Utils.moveToRelativeGroundLevel(level, center.add(weightedArea).add(new Vec3(0, 0, entity.getRandom().nextFloat() * spawnRadius).yRot(entity.getRandom().nextInt(360) * Mth.DEG_TO_RAD)), 3).add(0, 0.5, 0);
                var trajectory = new Vec3(.15f, -.85f, 0).normalize();
                Vec3 spawn = Utils.raycastForBlock(level, spawnTarget, spawnTarget.add(trajectory.scale(-12)), ClipContext.Fluid.NONE).getLocation().add(trajectory);
                shootBlizzard(level, spellLevel, entity, spawn, trajectory);
                //MagicManager.spawnParticles(level, ParticleHelper.COMET_FOG, spawn.x, spawn.y, spawn.z, 1, 1, 1, 1, 1, false);
                //MagicManager.spawnParticles(level, ParticleHelper.COMET_FOG, spawn.x, spawn.y, spawn.z, 1, 1, 1, 1, 1, true);
            }
    }
        public static class BlizzardCastData implements ICastData {
            Vec3 center;
            final List<Entity> trackedEntities = new ArrayList<>();

            public BlizzardCastData(Vec3 center) {
                this.center = center;
            }

            @Override
            public void reset() {
                trackedEntities.clear();
            }

            public void updateTrackedEntities(List<Entity> entities) {
                trackedEntities.clear();
                trackedEntities.addAll(entities);
            }

        }

    public void shootBlizzard(Level world, int spellLevel, LivingEntity entity, Vec3 spawn, Vec3 trajectory) {
        BlizzardHail fireball = new BlizzardHail(world, entity);
        fireball.setPos(spawn.add(-1, 0, 0));
        fireball.shoot(trajectory/*new Vec3(.15f, -.85f, 0)*/, .075f);
        fireball.setDamage(getDamage(spellLevel, entity));
        fireball.setExplosionRadius(2f);
        world.addFreshEntity(fireball);
        world.playSound(null, spawn.x, spawn.y, spawn.z, SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.PLAYERS, 3.0f, 0.7f + Utils.random.nextFloat() * .3f);

    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster) * .5f;
    }


    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!(playerMagicData.getAdditionalCastData() instanceof BlizzardCastData)) {
            Vec3 targetArea = Utils.moveToRelativeGroundLevel(world, Utils.raycastForEntity(world, entity, 40, true).getLocation(), 12);
            playerMagicData.setAdditionalCastData(new BlizzardCastData(targetArea));
        }
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }


    @Override
    public ResourceLocation getSpellResource() {
        return SunsetArmory.id("blizzard_spell");
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.CONTINUOUS;
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.HEARTSTOP_CAST.get());
    }
}
