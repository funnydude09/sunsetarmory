package net.funnydude.sunsetarmory.registries;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.effect.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect>MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, SunsetArmory.MODID);

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

    public static final Holder<MobEffect> RAGE_EFFECT = MOB_EFFECTS.register("rage_effect",
            () -> new RageEffect(MobEffectCategory.BENEFICIAL,0xf44336)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, SunsetArmory.id("rage_effect") , 0.25f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, SunsetArmory.id("rage_effect") , 0.5f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ARMOR, SunsetArmory.id("rage_effect"), -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final DeferredHolder<MobEffect, MobEffect> ARMOR_LOCK_EFFECT = MOB_EFFECTS.register("armor_lock_effect",
            () -> new ArmorLockEffect(MobEffectCategory.BENEFICIAL, 0x808080)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, SunsetArmory.id("armor_lock_effect"), -69420f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH, SunsetArmory.id("armor_lock_effect"), -999999999999999999f,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(AttributeRegistry.SPELL_RESIST, SunsetArmory.id("armor_lock_effect"), 100f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(AttributeRegistry.MANA_REGEN, SunsetArmory.id("armor_lock_effect"), -100f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, SunsetArmory.id("armor_lock_effect"), -100f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, SunsetArmory.id("armor_lock_effect"), -100f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, SunsetArmory.id("armor_lock_effect"), -100f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final Holder<MobEffect> AMBROSIA_EFFECT = MOB_EFFECTS.register("ambrosia_effect",
            () -> new AmbrosiaEffect(MobEffectCategory.BENEFICIAL,0xFFB6C1)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, SunsetArmory.id("ambrosia_effect") , 6f,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH, SunsetArmory.id("ambrosia_effect") , 0.4f,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.LUCK, SunsetArmory.id("ambrosia_effect"), 10f,
                            AttributeModifier.Operation.ADD_VALUE));

    public static final Holder<MobEffect> SOULBOUND_EFFECT = MOB_EFFECTS.register("soulbound_effect",
            () -> new SoulBoundEffect(MobEffectCategory.BENEFICIAL,0x8B0000)
                    .addAttributeModifier(AttributeRegistry.FIRE_SPELL_POWER,SunsetArmory.id("soulbound_effect"),0.2,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(AttributeRegistry.ICE_SPELL_POWER,SunsetArmory.id("soulbound_effect"),-999,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(AttributeRegistry.BLOOD_SPELL_POWER,SunsetArmory.id("soulbound_effect"),-0.2,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(AttributeRegistry.NATURE_SPELL_POWER,SunsetArmory.id("soulbound_effect"),-0.5,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(AttributeRegistry.NATURE_MAGIC_RESIST,SunsetArmory.id("soulbound_effect"),0.5,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(AttributeRegistry.ICE_MAGIC_RESIST,SunsetArmory.id("soulbound_effect"),-0.05,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ARMOR,SunsetArmory.id("soul_bound_effect"),-1,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> CINDEROUS_CHARGE_UP = MOB_EFFECTS.register("cinderous_chargeup_effect",
            () -> new AmbrosiaEffect(MobEffectCategory.BENEFICIAL,0x8B0000)
                    .addAttributeModifier(Attributes.GRAVITY,SunsetArmory.id("cinderous_chargeup_effect"),-0.5,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,SunsetArmory.id("cinderous_chargeup_effect"),5,
                            AttributeModifier.Operation.ADD_VALUE));

    public static final Holder<MobEffect> PILLAGER_ALLY = MOB_EFFECTS.register("pillager_ally",
            () -> new PillagerAllyEffect(MobEffectCategory.NEUTRAL,0xA9A9A9));

    public static final Holder<MobEffect> COOL_DOWN_EFFECT = MOB_EFFECTS.register("cool_down_effect",
            () -> new CoolDownEffect(MobEffectCategory.BENEFICIAL,0x8B0000)
                    .addAttributeModifier(AttributeRegistry.COOLDOWN_REDUCTION,SunsetArmory.id("cool_down_effect"),0.1,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(AttributeRegistry.CASTING_MOVESPEED,SunsetArmory.id("cool_down_effect"),1,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> ADRENALINE_OVERFLOW_EFFECT = MOB_EFFECTS.register("adrenaline_overflow_effect",
            () -> new AdrenalineOverflowEffect(MobEffectCategory.BENEFICIAL,0x8B0000)
                    .addAttributeModifier(AttributeRegistry.COOLDOWN_REDUCTION,SunsetArmory.id("adrenaline_overflow_effect"),2,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(AttributeRegistry.SPELL_RESIST,SunsetArmory.id("adrenaline_overflow_effect"),-0.75,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(AttributeRegistry.SPELL_POWER,SunsetArmory.id("adrenaline_overflow_effect"),-0.5,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> KINETIC_INCREASE_EFFECT = MOB_EFFECTS.register("kinetic_increase_effect",
            () -> new KineticEnergyEffect(MobEffectCategory.BENEFICIAL,0xABCEDE)
                    .addAttributeModifier(ModAttributes.KINETIC_ENERGY,SunsetArmory.id("kinetic_increase_effect"),1,
                            AttributeModifier.Operation.ADD_VALUE));

    public static final DeferredHolder<MobEffect, MobEffect> HALF_STANCE_SWORD_EFFECT = MOB_EFFECTS.register("half_stance_sword_effect",
            () -> new HalfSwordStanceEffect(MobEffectCategory.BENEFICIAL, 0x293805)
                    .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,SunsetArmory.id("half_stance_sword_effect"),10,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

}
