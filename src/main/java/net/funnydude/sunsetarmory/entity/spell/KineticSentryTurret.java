package net.funnydude.sunsetarmory.entity.spell;

import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import net.funnydude.sunsetarmory.registries.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.level.Level;


public class KineticSentryTurret extends Mob implements IMagicSummon {

    private LivingEntity owner;

    public LivingEntity getOwner() {
        return owner;
    }

    public void setOwner(LivingEntity owner) {
        this.owner = owner;
    }

    public void LivingEntity (LivingEntity entity){
        this.owner = entity;
    }

    public KineticSentryTurret(EntityType<KineticSentryTurret> entityType, Level level) {
        super(entityType, level);
    }

    public KineticSentryTurret(EntityType<KineticSentryTurret> entityType, Level level,LivingEntity owner){
        this(entityType, level);
        this.setOwner(owner);
    }

    public KineticSentryTurret(Level level,LivingEntity owner){
        this(ModEntities.KINETIC_SENTRY_TURRET.get(),level,owner);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1,new NearestAttackableTargetGoal(this, LivingEntity.class, true));
    }

    @Override
    public void onUnSummon() {

    }

    @Override
    protected boolean isImmobile() {
        return true;
    }
}
