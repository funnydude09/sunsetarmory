package net.funnydude.sunsetarmory.entity.spell;

import io.redspace.ironsspellbooks.api.events.CounterSpellEvent;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity;
import io.redspace.ironsspellbooks.entity.spells.ShieldPart;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.spells.ender.CounterspellSpell;
import net.funnydude.sunsetarmory.entity.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;

import javax.annotation.Nullable;

public class DivineShieldEntity extends AbstractShieldEntity {
    protected ShieldPart[] subEntities;
    protected final Vec3[] subPositions;
    public int LIFETIME;
    protected int width;
    protected int height;
    protected int age;

    public static float getHealth(float x,float health){
        x = (float) (health);
        return x;
    }
    public DivineShieldEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        width = 5;
        height = 5;
        subEntities = new ShieldPart[width * height];
        subPositions = new Vec3[width * height];
        this.setHealth(10);
        LIFETIME = 20 * 6;
        createShield();

    }
    public DivineShieldEntity(Level level, float health) {
        this(EntityRegistry.SHIELD_ENTITY.get(), level);
        this.setHealth(health);
    }
    @Override
    protected void createShield() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int i = x * height + y;
                subEntities[i] = new ShieldPart(this, "part" + (i + 1), 0.5F, 0.5F, true);
                subPositions[i] = new Vec3((x - width / 2f) * .5f + .25f, (y - height / 2f) * .5f, 0);//.xRot(getXRot()).yRot(getYRot());
            }
        }
    }

    @Override
    public void takeDamage(DamageSource source, float amount, @Nullable Vec3 location) {
        if (!this.isInvulnerableTo(source)) {
            this.setHealth(this.getHealth() - amount);
            if (!level().isClientSide && location != null) {
                MagicManager.spawnParticles(level(), ParticleTypes.ELECTRIC_SPARK, location.x, location.y, location.z, 30, .1, .1, .1, .5, false);
                level().playSound(null, location.x, location.y, location.z, SoundRegistry.FORCE_IMPACT.get(), SoundSource.NEUTRAL, .8f, 1f);
            }
        }
    }

    public void setRotation(float x, float y) {
        this.setXRot(x);
        this.xRotO = x;
        this.setYRot(y);
        this.yRotO = y;
    }




    @Override
    public void tick() {
        hurtThisTick = false;
        if (getHealth() <= 0) {
            destroy();
        } else if (++age > LIFETIME) {
            if (!this.level().isClientSide) {
                level().playSound(null, getX(), getY(), getZ(), SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.NEUTRAL, 1, 1.4f);
            }
            discard();
        } else {
            for (int i = 0; i < subEntities.length; i++) {
                var subEntity = subEntities[i];

                Vec3 pos = subPositions[i].xRot(Mth.DEG_TO_RAD * -this.getXRot()).yRot(Mth.DEG_TO_RAD * -this.getYRot()).add(this.position());
                subEntity.setPos(pos);
                subEntity.xo = pos.x;
                subEntity.yo = pos.y;
                subEntity.zo = pos.z;
                subEntity.xOld = pos.x;
                subEntity.yOld = pos.y;
                subEntity.zOld = pos.z;
            }
        }
    }
    @Override
    public PartEntity<?>[] getParts() {
        return this.subEntities;
    }

    @Override
    protected void destroy() {
        if (!this.level().isClientSide) {
            level().playSound(null, getX(), getY(), getZ(), SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL, 2, .65f);
        }
        super.destroy();
    }

}
