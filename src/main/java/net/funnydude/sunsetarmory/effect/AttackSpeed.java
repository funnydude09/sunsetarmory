package net.funnydude.sunsetarmory.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class AttackSpeed extends MobEffect {
    public static float attackSpeed = 0.1F;

    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(attributeMap, amplifier);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        attackSpeed = attackSpeed*(amplifier+1);
        super.onEffectAdded(livingEntity, amplifier);
    }

    public AttackSpeed(MobEffectCategory category, int color) {
        super(category, color);
    }
}
