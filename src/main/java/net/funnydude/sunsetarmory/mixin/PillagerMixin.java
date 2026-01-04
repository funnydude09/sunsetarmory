package net.funnydude.sunsetarmory.mixin;

import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelEntity;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
import net.funnydude.sunsetarmory.entity.wizards.paladin.PaladinEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Pillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Pillager.class)
public class PillagerMixin {
    @Inject(method = "registerGoals", at = @At("HEAD"))
    void injectRegisterGoals(CallbackInfo ci){
        Pillager self = (Pillager) (Object) this;
        self.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(self, PaladinEntity.class, true));
        self.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(self, KnightEntity.class, true));
        self.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(self, ArchangelEntity.class, true));
    }
}
