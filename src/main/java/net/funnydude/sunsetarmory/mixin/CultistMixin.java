package net.funnydude.sunsetarmory.mixin;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.NeutralWizard;
import io.redspace.ironsspellbooks.entity.mobs.wizards.cultist.CultistEntity;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelEntity;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
import net.funnydude.sunsetarmory.entity.wizards.paladin.PaladinEntity;
import net.funnydude.sunsetarmory.registries.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import software.bernie.geckolib.animation.RawAnimation;

@Mixin(CultistEntity.class)
public abstract class CultistMixin extends NeutralWizard implements Enemy, IAnimatedAttacker {

    RawAnimation animationToPlay = null;

    protected CultistMixin(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"))
    void injectRegisterGoals(CallbackInfo ci){
        CultistEntity self = (CultistEntity) (Object) this;
        self.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(self, PaladinEntity.class, true));
        self.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(self, KnightEntity.class, true));
        self.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(self, ArchangelEntity.class, true));
    }

    @Override
    public void playAnimation(String animationId) {
        try {
            animationToPlay = RawAnimation.begin().thenPlay(animationId);
        } catch (Exception ignored) {
            IronsSpellbooks.LOGGER.error("Entity {} Failed to play animation: {}", this, animationId);
        }
    }

    @Override
    public boolean canAttack(LivingEntity livingentity, TargetingConditions condition) {
        return super.canAttack(livingentity, condition) && !SunsetArmory.hasCurios(livingentity,ModItems.BLOOD_CULTIST_BANNER.get());
    }

    @Override
    public boolean isAlliedTo(Entity entityIn) {
        if (SunsetArmory.hasCurios(((LivingEntity) entityIn),ModItems.BLOOD_CULTIST_BANNER.get())) {
            return true;
        }
        else return super.isAlliedTo(entityIn);
    }

    @Inject(method = "isHostileTowards", at = @At(value = "HEAD"),cancellable = true)
    public void injectIsHostileTowards(LivingEntity entity, CallbackInfoReturnable cir) {
        if(SunsetArmory.hasCurios(entity,ModItems.BLOOD_CULTIST_BANNER.get())){
            cir.setReturnValue(false);
        }
        if(SunsetArmory.hasCurios(entity,ModItems.ELDRITCH_CULTIST_BANNER.get())|| SunsetArmory.hasCurios(entity,ModItems.SUNSET_BANNER.get())){
            cir.setReturnValue(true);
        }
        else cir.setReturnValue(super.isHostileTowards(entity));
    }

}