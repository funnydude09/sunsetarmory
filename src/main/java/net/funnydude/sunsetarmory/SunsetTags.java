package net.funnydude.sunsetarmory;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class SunsetTags {
    public static final TagKey<EntityType<?>> SUNSET_ORDER = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "sunset_order"));
    public static final TagKey<Item> KINETIC_FOCUS = ItemTags.create((ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID,"kinetic_focus")));

}
