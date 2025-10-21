package net.funnydude.sunsetarmory.spell;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.SunsetTags;
import net.funnydude.sunsetarmory.attribute.ModAttributes;
import net.funnydude.sunsetarmory.damagetype.ModDamageTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSchools {
    public static final DeferredRegister<SchoolType> SCHOOLS = DeferredRegister.create(SchoolRegistry.SCHOOL_REGISTRY_KEY.registry(),SunsetArmory.MODID);

    private static Supplier<SchoolType> registerSchool(SchoolType schoolType) {
        return SCHOOLS.register(schoolType.getId().getPath(), () -> schoolType);
    }
    public static final ResourceLocation KINETIC_RESOURCE = ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID,"kinetic");

    public static final Supplier<SchoolType> KINETIC = registerSchool(new SchoolType(
            KINETIC_RESOURCE,
            SunsetTags.KINETIC_FOCUS,
            Component.translatable("school.sunsetarmory.kinetic").withStyle(ChatFormatting.GOLD),
            ModAttributes.KINETIC_MAGIC_POWER,
            ModAttributes.KINETIC_MAGIC_RESIST,
            SoundRegistry.FIRE_CAST,
            ModDamageTypes.KINETIC_MAGIC
    ));
}