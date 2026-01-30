package net.funnydude.sunsetarmory;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class SunsetTags {
    public static final TagKey<EntityType<?>> SUNSET_ORDER = TagKey.create(Registries.ENTITY_TYPE, SunsetArmory.id("sunset_order"));
    public static final TagKey<EntityType<?>> ELDRITCH_GOD = TagKey.create(Registries.ENTITY_TYPE, SunsetArmory.id("eldritch_god"));
    public static final TagKey<Item> KINETIC_FOCUS = ItemTags.create(SunsetArmory.id("kinetic_focus"));

}
