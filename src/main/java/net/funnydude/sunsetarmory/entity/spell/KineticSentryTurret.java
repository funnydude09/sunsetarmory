package net.funnydude.sunsetarmory.entity.spell;

import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import net.funnydude.sunsetarmory.registries.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Targeting;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class KineticSentryTurret extends Mob implements IMagicSummon , Targeting {
    private LivingEntity owner;
    private ItemStack ammo;
    private Float arrowSpeed;


    public Float getArrowSpeed() {
        return arrowSpeed;
    }

    public void setArrowSpeed(Float arrowSpeed) {
        this.arrowSpeed = arrowSpeed;
    }

    public LivingEntity getOwner() {
        return owner;
    }

    public ItemStack getAmmo() {
        return ammo;
    }

    public void setAmmo(ItemStack ammo) {
        this.ammo = ammo;
    }

    @Override
    public void tick() {
        var target = this.getTarget();
        if(target != null && tickCount % 40 == 0){
            ItemStack crossbow = new ItemStack(Items.CROSSBOW);
            ItemStack arrowItemstack = new ItemStack(Items.ARROW);
            setAmmo(arrowItemstack);
            Arrow arrow = new Arrow(level(),this,getAmmo(),crossbow);
            double d0 = target.getX() - this.getX();
            double d1 = target.getY(0.3333333333333333) - arrow.getY();
            double d2 = target.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            arrow.shoot(d0, d1 + d3 * 0.2F, d2, arrowSpeed, 1);
            level().addFreshEntity(arrow);
        }
        super.tick();
    }

    public void setOwner(LivingEntity owner) {
        this.owner = owner;
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
    public void setTarget(@org.jetbrains.annotations.Nullable LivingEntity target) {
        if(target != getOwner()){
            super.setTarget(target);
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1,new NearestAttackableTargetGoal<>(this, Chicken.class, false));
    }

    @Override
    public void onUnSummon() {
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.FOLLOW_RANGE, 24.0)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 3)
                .add(Attributes.MOVEMENT_SPEED,0);
    }

    @Override
    protected boolean isImmobile() {
        return true;
    }
}
