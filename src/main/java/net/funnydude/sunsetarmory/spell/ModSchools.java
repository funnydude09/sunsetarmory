package net.funnydude.sunsetarmory.spell;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.SunsetTags;
import net.funnydude.sunsetarmory.attribute.ModAttributes;
import net.funnydude.sunsetarmory.damagetype.ModDamageTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSchools {
    public static final DeferredRegister<SchoolType> SUNSET_ARMORY_SCHOOLS = DeferredRegister.create(SchoolRegistry.SCHOOL_REGISTRY_KEY, SunsetArmory.MODID);

    private static Supplier<SchoolType> registerSchool(SchoolType schoolType) {
        return SUNSET_ARMORY_SCHOOLS.register(schoolType.getId().getPath(), () -> schoolType);
    }
    public static final ResourceLocation KINETIC_RESOURCE = SunsetArmory.id("kinetic");

    public static final Supplier<SchoolType> KINETIC = registerSchool(new SchoolType(
            KINETIC_RESOURCE,
            SunsetTags.KINETIC_FOCUS,
            Component.translatable("school.sunsetarmory.kinetic").withStyle(ChatFormatting.WHITE),
            ModAttributes.KINETIC_MAGIC_POWER,
            ModAttributes.KINETIC_MAGIC_RESIST,
            SoundRegistry.FIRE_CAST,
            ModDamageTypes.KINETIC_MAGIC
    ));
    public static void register(IEventBus eventBus) {
        SUNSET_ARMORY_SCHOOLS.register(eventBus);
    }
}