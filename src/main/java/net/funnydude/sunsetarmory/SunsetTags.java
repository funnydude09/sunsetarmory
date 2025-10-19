package net.funnydude.sunsetarmory;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class SunsetTags {
    public static final TagKey<EntityType<?>> SUNSET_ORDER = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "sunset_order"));
}
