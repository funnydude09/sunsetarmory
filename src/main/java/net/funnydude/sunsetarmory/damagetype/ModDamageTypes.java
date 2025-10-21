package net.funnydude.sunsetarmory.damagetype;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {
    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, name).toString()));
    }
    public static final ResourceKey<DamageType> KINETIC_MAGIC = register("kinetic_magic");
}