package net.funnydude.sunsetarmory.entity.wizards.paladin;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.network.IClientEventEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.NeutralWizard;
import io.redspace.ironsspellbooks.entity.mobs.dead_king_boss.DeadKingBoss;
import io.redspace.ironsspellbooks.entity.mobs.goals.MomentHurtByTargetGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.PatrolNearLocationGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackAnimationData;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackKeyframe;
import io.redspace.ironsspellbooks.entity.mobs.wizards.fire_boss.NotIdioticNavigation;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.SunsetTags;
import net.funnydude.sunsetarmory.registries.ModEntities;
import net.funnydude.sunsetarmory.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;

import javax.annotation.Nullable;
import java.util.List;

public class PaladinEntity extends NeutralWizard implements Enemy, IAnimatedAttacker, IEntityWithComplexSpawn, IClientEventEntity {
    private static final EntityDataAccessor<Boolean> DATA_IS_PLAYING_SUMMON_ANIM = SynchedEntityData.defineId(PaladinEntity.class, EntityDataSerializers.BOOLEAN);

    public boolean isSpawning() {
        return entityData.get(DATA_IS_PLAYING_SUMMON_ANIM);
    }

    public void isPlayingSummonAnim() {
        entityData.set(DATA_IS_PLAYING_SUMMON_ANIM, true);
    }

    public PaladinEntity(Level level) {
        this(ModEntities.PALADIN.get(), level);
        this.populateDefaultEquipmentSlots(randomSource,difficultyInstance);
    }

    @Override
    public void handleClientEvent(byte eventId) {

    }


    @Override
    public void writeSpawnData(RegistryFriendlyByteBuf registryFriendlyByteBuf) {

    }

    @Override
    public void readSpawnData(RegistryFriendlyByteBuf additionalData) {
        float y = this.getYRot();
        this.yBodyRot = y;
        this.yBodyRotO = y;
        this.yHeadRot = y;
        this.yHeadRotO = y;
        this.yRotO = y;
    }

    private static final AttributeModifier MANA_MODIFIER = new AttributeModifier(IronsSpellbooks.id("mana"), 10000, AttributeModifier.Operation.ADD_VALUE);
    private int destroyBlockDelay;
    private boolean canAnimateOver;
    private RandomSource randomSource;
    private DifficultyInstance difficultyInstance;
    private boolean stopHeadAnimation;
    private float summonAnimationTime = 36.6f;
    PaladinAttackGoal attackGoal;
    int parryCooldown;


    public PaladinEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.lookControl = createLookControl();
        this.moveControl = createMoveControl();
        this.isPlayingSummonAnim();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_IS_PLAYING_SUMMON_ANIM, false);
    }

    protected LookControl createLookControl() {
        return new LookControl(this) {
            //This allows us to more rapidly turn towards our target. Helps to make sure his targets are aligned with his swing animations
            @Override
            protected float rotateTowards(float pFrom, float pTo, float pMaxDelta) {
                return super.rotateTowards(pFrom, pTo, pMaxDelta * 2.5f);
            }

            @Override
            protected boolean resetXRotOnTick() {
                return getTarget() == null;
            }
        };
    }

    protected MoveControl createMoveControl() {
        return new PaladinMoveControl(this);
    }

    @Override
    public PaladinMoveControl getMoveControl() {
        return (PaladinMoveControl) super.getMoveControl();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.attackGoal = (PaladinAttackGoal) new PaladinAttackGoal(this, 1.5f, 50, 75)
                .setMoveset(List.of(
                        AttackAnimationData.builder("scythe_dagger_double_horizontal")
                                .length(60)
                                .attacks(
                                        new PaladinAttackKeyframe(15, new Vec3(0, 0, .25), new PaladinAttackKeyframe.SwingData(false, true)),
                                        new PaladinAttackKeyframe(36, new Vec3(0, 0, .75), new PaladinAttackKeyframe.SwingData(false, false)),
                                        new AttackKeyframe(42, new Vec3(0, 0, 0))
                                ).build(),
                        AttackAnimationData.builder("scythe_backpedal")
                                .length(40)
                                .rangeMultiplier(2f)
                                .attacks(
                                        new PaladinAttackKeyframe(20, new Vec3(0, .3, -2), new PaladinAttackKeyframe.SwingData(false, true))
                                ).build(),
                        AttackAnimationData.builder("scythe_sideslash_downslash_sideslash")
                                .length(62)
                                .rangeMultiplier(2f)
                                .attacks(
                                        new PaladinAttackKeyframe(18, new Vec3(0, 0, .45), new PaladinAttackKeyframe.SwingData(false, true)),
                                        new PaladinAttackKeyframe(30, new Vec3(0, 0, .45), new PaladinAttackKeyframe.SwingData(false, false)),
                                        new PaladinAttackKeyframe(50, new Vec3(0, 0.1, 1.25), new Vec3(0, .3, 0.8), new PaladinAttackKeyframe.SwingData(false, false))
                                ).build(),
                        AttackAnimationData.builder("scythe_downslash_sideslash")
                                .length(60)
                                .attacks(
                                        new PaladinAttackKeyframe(22, new Vec3(0, 0, .5f), new Vec3(0, -.2, 0), new PaladinAttackKeyframe.SwingData(true, true)),
                                        new PaladinAttackKeyframe(40, new Vec3(0, .1, 0.8), new PaladinAttackKeyframe.SwingData(false, false))
                                ).build(),
                        AttackAnimationData.builder("scythe_horizontal_slash_spin")
                                .length(45)
                                .area(0.25f)
                                .rangeMultiplier(3f)
                                .attacks(
                                        new PaladinAttackKeyframe(14, new Vec3(0, 0.1, 1.25), new Vec3(0, .1, 0.8), new PaladinAttackKeyframe.SwingData(false, true)),
                                        new PaladinAttackKeyframe(30, new Vec3(0, 0.1, 1.85), new Vec3(0, .3, 0.8), new PaladinAttackKeyframe.SwingData(false, false))
                                ).build()

                ))
                .setComboChance(0.5f)
                .setMeleeAttackInverval(10, 30)
                .setMeleeBias(0.4f, 0.7f)
                .setSpells(
                        //offence
                        List.of(SpellRegistry.SUNBEAM_SPELL.get(), SpellRegistry.FIRE_ARROW_SPELL.get(), SpellRegistry.ROOT_SPELL.get()),
                        //defence
                        List.of(SpellRegistry.EVASION_SPELL.get(),SpellRegistry.ABYSSAL_SHROUD_SPELL.get(),SpellRegistry.HEAL_SPELL.get()),
                        //movement
                        List.of(SpellRegistry.TELEPORT_SPELL.get(),SpellRegistry.ASCENSION_SPELL.get()),
                        //support
                        List.of(SpellRegistry.COUNTERSPELL_SPELL.get(),SpellRegistry.CLEANSE_SPELL.get())
                )
                .setSpellQuality(1.0f, 1.0f);

        this.goalSelector.addGoal(2, new SpellBarrageGoal(this, SpellRegistry.COUNTERSPELL_SPELL.get(), 1, 1, 80, 240, 3));
        this.goalSelector.addGoal(3, attackGoal);
        this.goalSelector.addGoal(4, new PatrolNearLocationGoal(this, 30, .75f));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(1, new MomentHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Pig.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, DeadKingBoss.class, true));
    }


    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundRegistry.FIRE_BOSS_HURT.get();
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() || isSpawning();
    }

    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        return isSpawning() || super.isInvulnerableTo(pSource);
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData) {
        super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData);
        RandomSource randomsource = Utils.random;
        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
        this.setLeftHanded(false);
        this.getAttribute(AttributeRegistry.MAX_MANA).addOrReplacePermanentModifier(MANA_MODIFIER);
        this.setHealth(this.getMaxHealth());
        return pSpawnData;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.NPC_PALADIN_HELMET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.NPC_PALADIN_CHESTPLATE.get()));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ModItems.NPC_PALADIN_LEGGINGS.get()));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(ModItems.NPC_PALADIN_BOOTS.get()));
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.NETHERITE_SPEAR.get()));
        this.setDropChance(EquipmentSlot.HEAD, 0);
        this.setDropChance(EquipmentSlot.CHEST, 0);
        this.setDropChance(EquipmentSlot.MAINHAND, 0);
        this.setDropChance(EquipmentSlot.FEET, 0);
        this.setDropChance(EquipmentSlot.LEGS, 0);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            entity.igniteForSeconds(2);
            return true;
        }
        return false;
    }

    @Override
    public void kill() {
        if (this.isDeadOrDying() || this.isSpawning()) {
            discard();
        } else {
            super.kill();
        }
    }

    @Override
    public void calculateEntityAnimation(boolean pIncludeHeight) {
        super.calculateEntityAnimation(false);
    }

    @Override
    protected void updateWalkAnimation(float f) {
        super.updateWalkAnimation(f * (!this.onGround() ? .5f : (.9f)));
    }

    @Override
    public boolean isHostileTowards(LivingEntity pTarget) {
        return super.isHostileTowards(pTarget)
                || pTarget.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER) > 1.15
                || pTarget.getAttributeValue(AttributeRegistry.ELDRITCH_SPELL_POWER) > 1.8
                || SunsetArmory.hasCurios(pTarget,ModItems.ELDRITCH_CULTIST_BANNER.get())
                || SunsetArmory.hasCurios(pTarget,ModItems.BLOOD_CULTIST_BANNER.get());
    }

    @Override
    public boolean bobBodyWhileWalking() {
        return !isAnimating();
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        this.playSound(SoundRegistry.KEEPER_STEP.get(), .25f, .9f);
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 14.0)
                .add(AttributeRegistry.SPELL_POWER, 1.25)
                .add(Attributes.ARMOR, 45)
                .add(AttributeRegistry.SPELL_RESIST, 1.5)
                .add(AttributeRegistry.BLOOD_MAGIC_RESIST, 1.5)
                .add(AttributeRegistry.ELDRITCH_MAGIC_RESIST, 1.5)
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(Attributes.ATTACK_KNOCKBACK, .6)
                .add(Attributes.FOLLOW_RANGE, 150.0)
                .add(Attributes.SCALE, 1.25)
                .add(Attributes.GRAVITY, 1)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 4)
                .add(Attributes.STEP_HEIGHT, 1)
                .add(Attributes.MOVEMENT_SPEED, .21);
    }

    @Override
    public void push(Entity pEntity) {
        if (!isSpawning()) {
            super.push(pEntity);
        }
    }

    @Override
    public void tick() {
        if (isSpawning()) {
            if (--summonAnimationTime < 0) {
                entityData.set(DATA_IS_PLAYING_SUMMON_ANIM, false);
            }
        }
        else{
            if (parryCooldown > 0) {
                parryCooldown--;
            }
            super.tick();
        }
    }

    @Override
    public boolean isPushable() {
        return super.isPushable() && !isImmobile();
    }

    RawAnimation animationToPlay = null;
    private final AnimationController<PaladinEntity> meleeController = new AnimationController<>(this, "melee_animations", 0, this::predicate);

    @Override
    public void playAnimation(String animationId) {
        animationToPlay = RawAnimation.begin().thenPlay(animationId);
        canAnimateOver = animationId.equals("fire_boss_spawn");
        stopHeadAnimation = animationId.equals("fire_boss_break_stance") || animationId.equals("fire_boss_death");
    }

    @Override
    public boolean shouldAlwaysAnimateHead() {
        return !stopHeadAnimation;
    }

    private PlayState predicate(AnimationState<PaladinEntity> animationEvent) {
        var controller = animationEvent.getController();
        if (this.animationToPlay != null && !isSpawning()) {
            controller.forceAnimationReset();
            controller.setAnimation(animationToPlay);
            animationToPlay = null;
        }
        if(isSpawning()){
            controller.setAnimation(RawAnimation.begin().thenPlay("paladin_spawn"));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(meleeController);
        super.registerControllers(controllerRegistrar);
    }

    @Override
    public boolean isAnimating() {
        return (meleeController.getAnimationState() == AnimationController.State.RUNNING && !canAnimateOver) || super.isAnimating();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (level().isClientSide) {
            return false;
        }
        boolean canParry = this.isAggressive() &&
                parryCooldown <= 0 &&
                !isImmobile() &&
                !attackGoal.isActing() &&
                pSource.getEntity() != null &&
                pSource.getSourcePosition() != null && pSource.getSourcePosition().subtract(this.position()).normalize().dot(this.getForward()) >= 0.35
                && !pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
        if (canParry && this.random.nextFloat() < 0.5) {
            serverTriggerAnimation("spear_block");
            this.parryCooldown = 0;
            this.playSound(SoundEvents.SHIELD_BLOCK);
            return false;
        }
        if (pSource.is(DamageTypes.IN_WALL) && this.destroyBlockDelay <= 0) {
            Utils.doMobBreakSuffocatingBlocks(this);
            destroyBlockDelay = 40;
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        super.actuallyHurt(damageSource, damageAmount);
    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        if(pEntity instanceof LivingEntity && SunsetArmory.hasCurios(((LivingEntity) pEntity),ModItems.SUNSET_BANNER.get())){
            return true;
        }
        return super.isAlliedTo(pEntity) || pEntity.getType().is(SunsetTags.SUNSET_ORDER);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new NotIdioticNavigation(this, pLevel);
    }
}