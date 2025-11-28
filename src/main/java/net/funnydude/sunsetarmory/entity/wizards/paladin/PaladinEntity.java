package net.funnydude.sunsetarmory.entity.wizards.paladin;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.network.IClientEventEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.*;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.config.ServerConfigs;
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
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.funnydude.sunsetarmory.SunsetTags;
import net.funnydude.sunsetarmory.registries.ModEffects;
import net.funnydude.sunsetarmory.registries.ModEntities;
import net.funnydude.sunsetarmory.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleContainer;
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

    public void giveThisPaladinSomeEquipment(){
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

    public PaladinEntity(Level level) {
        this(ModEntities.PALADIN.get(), level);
        this.giveThisPaladinSomeEquipment();

    }

    @Override
    public void handleClientEvent(byte eventId) {

    }

    @Override
    public void writeSpawnData(RegistryFriendlyByteBuf buffer) {
        buffer.writeInt(this.spawnTimer);
    }

    @Override
    public void readSpawnData(RegistryFriendlyByteBuf additionalData) {
        this.spawnTimer = additionalData.readInt();
        float y = this.getYRot();
        this.yBodyRot = y;
        this.yBodyRotO = y;
        this.yHeadRot = y;
        this.yHeadRotO = y;
        this.yRotO = y;
    }

    private static final EntityDataAccessor<Boolean> DATA_SOUL_MODE = SynchedEntityData.defineId(PaladinEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_DESPAWNING = SynchedEntityData.defineId(PaladinEntity.class, EntityDataSerializers.BOOLEAN);
    private static final AttributeModifier SOUL_SPEED_MODIFIER = new AttributeModifier(IronsSpellbooks.id("soul_mode"), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final AttributeModifier SOUL_SCALE_MODIFIER = new AttributeModifier(IronsSpellbooks.id("soul_mode"), 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final AttributeModifier MANA_MODIFIER = new AttributeModifier(IronsSpellbooks.id("mana"), 10000, AttributeModifier.Operation.ADD_VALUE);
    private int despawnAggroDelay;
    private int destroyBlockDelay;
    /**
     * Amount of non-creative/spectator players within 60 blocks of summoning this entity. Affects attribute scaling and drop count.
     */
    private int playerScale;

    /**
     * Client flag for whether code animations should pause over current animation
     */
    private boolean canAnimateOver;
    /**
     * Client flag for whether the head should stop animating lookat for the current animation
     */
    private boolean stopHeadAnimation;

    /**
     * Client side model control value
     */
    public float isAnimatingDampener;

    public PaladinEntity(EntityType<? extends AbstractSpellCastingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 25;
        this.lookControl = createLookControl();
        this.moveControl = createMoveControl();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_SOUL_MODE, false);
        pBuilder.define(DATA_IS_DESPAWNING, false);
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

    PaladinAttackGoal attackGoal;

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
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    /*
     * Stance Break Mechanic
     * - In order for a long-form cinematic and serializable ability to take place, we must store a decent bit of data on the entity itself
     * - At 2/3 and 1/3 health, the boss's stance will break, interrupting all actions, and playing a short stun animation
     * - At the end of the stun, he performs 3 strikes of Raise Hell
     * - He goes into Soul Mode on the second break
     */
    /*
     * Spawn Animation Handlers
     */
    int spawnTimer;
    private static final int SPAWN_ANIM_TIME = (int) (8.75 * 20);
    private static final int SPAWN_DELAY = 40;

    /*
     * Half Health Ability
     * - Upon reaching half health, the boss performs a pseudo wipe mechanic
     * - He Jumps into the air and beings charging a fireball/meteor
     * - After 10 seconds, he will launch it, which is powerful enough to nearly kill most anything
     * - However, if 10% of his max health is dealt as damage during this phase, the ability is interrupted and blows up the boss instead
     */
    /*
     * Spectral Dagger
     * client synced timer
     */
    int daggerTime;
    int parryCooldown;
    boolean clientDaggerParticles;

    public void triggerSpawnAnim() {
        this.spawnTimer = SPAWN_ANIM_TIME + SPAWN_DELAY;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundRegistry.FIRE_BOSS_HURT.get();
    }

    public boolean isSpawning() {
        return spawnTimer > 0;
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
        this.playerScale = pLevel.players().stream().filter(player -> distanceToSqr(player) < 3600 && !player.isSpectator() && !player.isCreative()).toList().size();
        int extraPlayers = Math.max(0, playerScale - 1);
        double extraHealthPercent = extraPlayers * 0.40 + extraPlayers * extraPlayers * 0.10;
        double extraHealth = ServerConfigs.TYROS_ADDITIONAL_HEALTH.get();
        double extraDamage = ServerConfigs.TYROS_ADDITIONAL_ATTACK_DAMAGE.get();
        double extraPower = ServerConfigs.TYROS_ADDITIONAL_SPELL_POWER.get();
        if (extraHealth != 0) {
            this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(IronsSpellbooks.id("config"), extraHealth, AttributeModifier.Operation.ADD_VALUE));
        }
        if (extraHealthPercent != 0) {
            this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(IronsSpellbooks.id("player_scale"), extraHealthPercent, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        }
        if (extraDamage != 0) {
            this.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(new AttributeModifier(IronsSpellbooks.id("config"), extraDamage, AttributeModifier.Operation.ADD_VALUE));
        }
        if (extraPower != 0) {
            this.getAttribute(AttributeRegistry.SPELL_POWER).addPermanentModifier(new AttributeModifier(IronsSpellbooks.id("config"), extraPower, AttributeModifier.Operation.ADD_VALUE));
        }
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
    public void tick() {
        super.tick();
        float maxHealth = this.getMaxHealth();
        float currentHealth = this.getHealth();
        if (daggerTime > 0) {
            daggerTime--;
        }
        if (parryCooldown > 0) {
            parryCooldown--;
        }
        if (isSpawning()) {
            spawnTimer--;
            handleSpawnSequence();
            if (spawnTimer == 0 && !level().isClientSide) {
            }
        }
        if (destroyBlockDelay > 0) {
            --destroyBlockDelay;
        }
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

    private void handleSpawnSequence() {
        int animProgress = SPAWN_ANIM_TIME + SPAWN_DELAY - spawnTimer; // counts up to max (whereas timer counts down from max)
        float walkProgress = getSpawnWalkPercent(0); // 0-1f, percent progress of the spawn animation from starting to walk to finishing animation
        float worldZOffset = Mth.lerp(walkProgress, -60 / 16f * getScale(), 0);
        Vec3 position = this.position().add(new Vec3(0, 0, worldZOffset).yRot(-this.getYRot() * Mth.DEG_TO_RAD));
        // timed delay to sync beginning of music with beginning of fight
        if (animProgress == SPAWN_DELAY) {
            // begin walking out of puff of smoke
            if (!level().isClientSide) {
                //smoke to step out of
                MagicManager.spawnParticles(level(), ParticleTypes.CAMPFIRE_COSY_SMOKE, position.x, position.y + 1.2, position.z, (int) (165 * getScale()), 0.4 * getScale(), 1.0 * getScale(), 0.4 * getScale(), 0.01, true);
                MagicManager.spawnParticles(level(), ParticleHelper.FOG_CAMPFIRE_SMOKE, position.x, position.y + 0.1, position.z, 6, 0.6, .1, 0.6, 0.05, true);
                // responding bell toll echo
                MagicManager.spawnParticles(level(), new BlastwaveParticleOptions(1, .6f, 0.3f, 8), position.x, position.y, position.z, 0, 0, 0, 0, 0, true);
                serverTriggerAnimation("fire_boss_spawn");
            }
            level().playSound(null, position.x, position.y, position.z, SoundRegistry.SOULCALLER_TOLL_SUCCESS, SoundSource.PLAYERS, 5f, .75f);
        }
        //step sounds
        if (animProgress == SPAWN_DELAY + 20 || animProgress == SPAWN_DELAY + 40 || animProgress == SPAWN_DELAY + 60 || animProgress == SPAWN_DELAY + 80 || animProgress == SPAWN_DELAY + 100 || animProgress == SPAWN_DELAY + 114 || animProgress == SPAWN_DELAY + 128) {
            level().playSound(null, position.x, position.y, position.z, SoundRegistry.KEEPER_STEP, this.getSoundSource(), 0.4f, 1f);
        }
        // summon scythe sound (happens at tick 132, with 17 tick windup)
        if (animProgress == SPAWN_DELAY + 132 - 17) {
            level().playSound(null, position.x, position.y, position.z, SoundRegistry.FIRE_BOSS_SUMMON_SCYTHE, this.getSoundSource(), 3f, 1f);
        }
    }

    /**
     * @return 0-1f, percent progress of the spawn animation from starting to walk to finishing animation
     */
    protected float getSpawnWalkPercent(float partialTick) {
        return Math.clamp((SPAWN_ANIM_TIME - spawnTimer + partialTick) / (float) SPAWN_ANIM_TIME, 0, 1);
    }

    SimpleContainer deathLoot = null;

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
        //reduce walk animation swing if we are floating or meleeing
        super.updateWalkAnimation(f * (!this.onGround() ? .5f : (this.isSoulMode() ? .7f : .9f)));
    }

    @Override
    public boolean isHostileTowards(LivingEntity pTarget) {
        return super.isHostileTowards(pTarget)
                || pTarget.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER) > 1.15
                || pTarget.getAttributeValue(AttributeRegistry.ELDRITCH_SPELL_POWER) > 1.8
                || pTarget.hasEffect(ModEffects.PILLAGER_ALLY);
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
    public void knockback(double pStrength, double pX, double pZ) {
        super.knockback(pStrength, pX, pZ);
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
        canAnimateOver = animationId.equals("fire_boss_spawn") || animationId.equals("summon_fiery_daggers");
        stopHeadAnimation = animationId.equals("fire_boss_break_stance") || animationId.equals("fire_boss_death");
    }

    @Override
    public boolean shouldAlwaysAnimateHead() {
        return !stopHeadAnimation;
    }

    private PlayState predicate(AnimationState<PaladinEntity> animationEvent) {
        var controller = animationEvent.getController();

        if (this.animationToPlay != null) {
            controller.forceAnimationReset();
            controller.setAnimation(animationToPlay);
            animationToPlay = null;
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
        /*
        can parry:
        - serverside
        - in combat
        - we aren't in melee attack anim or spell cast
        - the damage source is caused by an entity (ie not fall damage)
        - the damage is caused within our rough field of vision (117 degrees)
        - the damage is not /kill
         */
        boolean canParry = this.isAggressive() &&
                parryCooldown <= 0 &&
                !isImmobile() &&
                !attackGoal.isActing() &&
                pSource.getEntity() != null &&
                pSource.getSourcePosition() != null && pSource.getSourcePosition().subtract(this.position()).normalize().dot(this.getForward()) >= 0.35
                && !pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
        if (canParry && this.random.nextFloat() < 0.5) {
            //todo: dynamic parry chance (recent hits, ominious mode, damage type, etc)
            serverTriggerAnimation("spear_block");
            //procSpectralDagger();
            this.parryCooldown = 0;
            this.playSound(SoundEvents.SHIELD_BLOCK);
            return false;
        }
        // damage limiter
        var limit = getMaxHealth() * 0.025f;
        if (pAmount > limit) {
            pAmount = limit + (pAmount - limit) * .3f; // damage about limit has .3x multiplier applied
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

    public boolean isSoulMode() {
        return false;
    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity) || pEntity.getType().is(SunsetTags.SUNSET_ORDER);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new NotIdioticNavigation(this, pLevel);
    }

    public boolean spectralDaggerActive() {
        return false;
    }
}