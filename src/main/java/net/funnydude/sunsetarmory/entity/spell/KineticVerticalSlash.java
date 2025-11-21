//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.funnydude.sunsetarmory.entity.spell;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity;
import io.redspace.ironsspellbooks.entity.spells.ShieldPart;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import net.funnydude.sunsetarmory.entity.ModEntities;
import net.funnydude.sunsetarmory.spell.ModSpells;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;

public class KineticVerticalSlash extends AbstractMagicProjectile implements AntiMagicSusceptible {
    private List<Entity> entities;
    private int lifetimeInTicks;

    public KineticVerticalSlash(EntityType<KineticVerticalSlash> kineticVerticalSlashEntityType, Level level) {
        super(kineticVerticalSlashEntityType, level);
        this.lifetimeInTicks = 60;
        this.entities = new ArrayList();
        this.setNoGravity(true);
    }

    public KineticVerticalSlash(EntityType<KineticVerticalSlash> kineticVerticalSlashEntityType, Level level, LivingEntity shooter) {
        this(kineticVerticalSlashEntityType, level);
        this.setOwner(shooter);
        this.setYRot(shooter.getYRot());
        this.setXRot(shooter.getXRot());
        this.setXRot(shooter.getXRot());
    }

    public KineticVerticalSlash(Level level, LivingEntity shooter) {
        this(ModEntities.KINETIC_VERTICAL_SLASH.get(), level, shooter);
    }

    public void travel() {
        this.setPos(this.position().add(this.getDeltaMovement()));
        if (!this.isNoGravity()) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x, vec3.y - (double)0.05F, vec3.z);
        }

    }

    public void tick() {
        --this.lifetimeInTicks;
        if (this.lifetimeInTicks <= 0) {
            this.discard();
        }

        if (!this.level().isClientSide) {
            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() == Type.BLOCK) {
                this.onHitBlock((BlockHitResult)hitresult);
            }

            for(Entity entity : this.level().getEntities(this, this.getBoundingBox()).stream().filter((target) -> this.canHitEntity(target) && !this.entities.contains(target)).collect(Collectors.toSet())) {
                this.damageEntity(entity);
            }
        }
        this.setXRot(this.getXRot());
        this.setYRot(this.getYRot());
        super.tick();
    }

    public void trailParticles() {
    }

    public void impactParticles(double x, double y, double z) {
    }

    public float getSpeed() {
        return 1.2F;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    private void damageEntity(Entity entity) {
        if (!this.entities.contains(entity)) {
            DamageSources.applyDamage(entity, this.damage, (ModSpells.KINETIC_SLASH_SPELL.get()).getDamageSource(this, this.getOwner()));
            if (entity instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity)entity;
                if (livingTarget instanceof Player) {
                    Player player = (Player)livingTarget;
                    player.disableShield();
                }
                if (DamageSources.applyDamage(livingTarget, this.damage, (ModSpells.KINETIC_SLASH_SPELL.get()).getDamageSource(this, this.getOwner()))) {
                    EnchantmentHelper.doPostAttackEffects((ServerLevel)this.level(), livingTarget, (ModSpells.KINETIC_SLASH_SPELL.get()).getDamageSource(this, this.getOwner()));
                }
            }

            if (entity instanceof ShieldPart || entity instanceof AbstractShieldEntity) {
                entity.kill();
            }

            this.entities.add(entity);
        }

    }

    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard();
    }

    protected boolean canHitEntity(Entity pTarget) {
        return pTarget != this.getOwner() && super.canHitEntity(pTarget);
    }

    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    public void onAntiMagic(MagicData playerMagicData) {
    }
}
