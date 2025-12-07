package net.funnydude.sunsetarmory.entity.spell;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity;
import io.redspace.ironsspellbooks.entity.spells.ShieldPart;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.funnydude.sunsetarmory.registries.ModEntities;
import net.funnydude.sunsetarmory.registries.ModSpells;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;

public class KineticSlash extends Projectile implements AntiMagicSusceptible {
    private List<Entity> entities;
    private int lifetimeInTicks;
    private float thisdamage;

    public KineticSlash(EntityType<KineticSlash> kineticSlashEntityType, Level level) {
        super(kineticSlashEntityType, level);
        this.lifetimeInTicks = 60;
        this.entities = new ArrayList();
        this.setNoGravity(true);
    }

    public KineticSlash(EntityType<KineticSlash> kineticSlashEntityType, Level level, LivingEntity shooter) {
        this(kineticSlashEntityType, level);
        this.setOwner(shooter);
        this.setYRot(shooter.getYRot());
        this.setXRot(shooter.getXRot());
    }

    public KineticSlash(Level level, LivingEntity shooter) {
        this(ModEntities.KINETIC_SLASH.get(), level, shooter);
    }

    public void travel() {
        this.setPos(this.position().add(this.getDeltaMovement()));
        if (!this.isNoGravity()) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x, vec3.y - (double)0.05F, vec3.z);
        }
    }

    @Override
    public void tick() {
        --this.lifetimeInTicks;
        if (this.lifetimeInTicks <= 0) {
            this.discard();
        }
        if (!this.level().isClientSide) {
            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() == Type.BLOCK) {
                this.onHitBlock((BlockHitResult) hitresult);
            }
            for (Entity entity : this.level().getEntities(this, this.getBoundingBox()).stream().filter((target) -> this.canHitEntity(target) && !this.entities.contains(target)).collect(Collectors.toSet())) {
                this.damageEntity(entity);
            }
        }
        this.setXRot(this.getXRot());
        this.setYRot(this.getYRot());
        travel();
        super.tick();
    }

    public float getSpeed() {
        return 1.2F;
    }

    public void setDamage(float damage) {
        thisdamage = damage;
    }

    private void damageEntity(Entity entity) {
        if (!this.entities.contains(entity)) {
            DamageSources.applyDamage(entity, thisdamage, (ModSpells.KINETIC_SLASH_SPELL.get()).getDamageSource(this, this.getOwner()));
            if (entity instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity)entity;
                if (livingTarget instanceof Player) {
                    Player player = (Player)livingTarget; player.disableShield();
                }
                if (DamageSources.applyDamage(livingTarget, thisdamage, (ModSpells.KINETIC_SLASH_SPELL.get()).getDamageSource(this, this.getOwner()))) {
                    EnchantmentHelper.doPostAttackEffects((ServerLevel)this.level(), livingTarget, (ModSpells.KINETIC_SLASH_SPELL.get()).getDamageSource(this, this.getOwner()));
                }
            }
            if (entity instanceof ShieldPart || entity instanceof AbstractShieldEntity) {
                entity.kill();
            }
            this.entities.add(entity);
        }
    }

    protected void doImpactSound(Holder<SoundEvent> sound) {
        level().playSound(null, getX(), getY(), getZ(), sound, SoundSource.NEUTRAL, 2, .9f + Utils.random.nextFloat() * .2f);
    }

    @Override
    protected void onHit(HitResult result) {
        getImpactSound().ifPresent(this::doImpactSound);
        super.onHit(result);
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
        this.discard();
    }
}