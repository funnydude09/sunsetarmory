package net.funnydude.sunsetarmory.effect;

import io.redspace.ironsspellbooks.effect.CustomDescriptionMobEffect;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.EffectCure;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.Set;
@EventBusSubscriber
public class ArmorLockEffect extends CustomDescriptionMobEffect {
    public static final float REDUCTION_PER_LEVEL = 0f;
    public static final float BASE_REDUCTION = 1f;

    protected ArmorLockEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public Component getDescriptionLine(MobEffectInstance instance) {
        int amp = instance.getAmplifier() + 1;
        float reductionAmount = getReductionAmount(amp);
        return Component.translatable("tooltip.irons_spellbooks.oakskin_description", (int) (reductionAmount * 100)).withStyle(ChatFormatting.BLUE);
    }
    @SubscribeEvent
    public static void reduceDamage(LivingIncomingDamageEvent event) {
        var entity = event.getEntity();
        var effect = entity.getEffect(ModEffects.ARMOR_LOCK_EFFECT);
        if (effect != null) {
            int lvl = effect.getAmplifier() + 1;
            float before = event.getAmount();
            float multiplier = 1 - getReductionAmount(lvl);
            event.setAmount(event.getAmount() * multiplier);
        }
    }


    public static float getReductionAmount(int level) {
        return BASE_REDUCTION + REDUCTION_PER_LEVEL*level;
    }
    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public int getSortOrder(MobEffectInstance effectInstance) {
        return super.getSortOrder(effectInstance);
    }
}
