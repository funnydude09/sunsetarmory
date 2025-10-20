package net.funnydude.sunsetarmory.effect;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect>MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, SunsetArmory.MODID);

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

    public static final Holder<MobEffect> RAGE_EFFECT = MOB_EFFECTS.register("rage",
            () -> new RageEffect(MobEffectCategory.BENEFICIAL,0xf44336)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "rage") , 0.25f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "rage") , 0.5f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID,"rage"), -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final DeferredHolder<MobEffect, MobEffect> ARMOR_LOCK_EFFECT = MOB_EFFECTS.register("armor_lock",
            () -> new ArmorLockEffect(MobEffectCategory.BENEFICIAL, 0x808080)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID,"armor_lock"), -1f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> AMBROSIA_EFFECT = MOB_EFFECTS.register("ambrosia",
            () -> new AmbrosiaEffect(MobEffectCategory.BENEFICIAL,0xFFB6C1)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "ambrosia") , 6f,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH, ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "ambrosia") , 0.4f,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.LUCK, ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID,"ambrosia"), 10f,
                            AttributeModifier.Operation.ADD_VALUE));



}
