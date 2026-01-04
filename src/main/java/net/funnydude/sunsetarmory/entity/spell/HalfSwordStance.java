package net.funnydude.sunsetarmory.entity.spell;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.funnydude.sunsetarmory.registries.ModEntities;
import net.funnydude.sunsetarmory.registries.ModSpells;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class HalfSwordStance extends AoeEntity {

    private float lifetime = 1f;

    public HalfSwordStance(Level level, LivingEntity owner) {
        this(ModEntities.HALF_SWORD_STANCE_ENTITY.get(),level);
        setOwner(owner);
    }

    public HalfSwordStance(EntityType<HalfSwordStance> halfSwordStanceEntityEntityType, Level level) {
        super(halfSwordStanceEntityEntityType, level);
    }

    @Override
    public void tick() {
        if(tickCount < lifetime || tickCount > lifetime){
            this.discard();
        } else if(tickCount == lifetime) {
            if (!level().isClientSide) {
                this.playSound(SoundEvents.PLAYER_BREATH, 1, 1);
                var center = this.getBoundingBox().getCenter();
                float damageDistance = getRadius();
                var damageDistanceSqr = damageDistance*damageDistance;
                var damage = getDamage();
                var entities = level().getEntities(this, this.getBoundingBox().inflate(damageDistance));
                for (Entity entity : entities) {
                    if (!(entity == getOwner())) {
                        double distanceSqr = entity.distanceToSqr(center);
                        if (distanceSqr < damageDistanceSqr && canHitEntity(entity) && Utils.hasLineOfSight(level(), center, entity.getBoundingBox().getCenter(), true)) {
                            ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffectRegistry.REND,1,0));
                            DamageSources.applyDamage(entity, damage, ModSpells.HALF_SWORD_STANCE_SPELL.get().getDamageSource(this, getOwner()));
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean canHitTargetForGroundContext(LivingEntity target) {
        return true;
    }

    @Override
    public void applyEffect(LivingEntity target) {
        return;
    }

    @Override
    public float getParticleCount() {
        return 0;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }
}
