package net.funnydude.sunsetarmory.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class LoveTrainEffect extends MobEffect{
    public LoveTrainEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    //todo:try to apply mixins on AttackEntityEvent and make it so that the effect gets removed if player attacks
}
