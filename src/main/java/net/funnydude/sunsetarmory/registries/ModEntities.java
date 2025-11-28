package net.funnydude.sunsetarmory.registries;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.spell.*;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelEntity;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
import net.funnydude.sunsetarmory.entity.wizards.living_armor_stand.LivingArmorStandEntity;
import net.funnydude.sunsetarmory.entity.wizards.paladin.PaladinEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Projectile;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModEntities {
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, "sunsetarmory");

    public static final DeferredHolder<EntityType<?>, EntityType<KnightEntity>> KNIGHT
            = ENTITIES.register("knight", () -> EntityType.Builder.<KnightEntity>of(KnightEntity::new, MobCategory.MISC)
            .sized(.6f, 1.8f)
            .clientTrackingRange(64)
            .build(ResourceLocation.fromNamespaceAndPath("sunsetarmory", "knight").toString()));

    public static final Supplier<EntityType<Entity>> DIVINE_SHIELD_ENTITY =
            ENTITIES.register("divine_shield", () -> EntityType.Builder.of(DivineShieldEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "divine_shield").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<KineticSlash>> KINETIC_SLASH =
            ENTITIES.register("kinetic_slash", () -> EntityType.Builder.<KineticSlash>of(KineticSlash::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "kinetic_slash").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<LivingArmorStandEntity>> LIVING_ARMOR_STAND =
            ENTITIES.register("living_armor_stand", () -> EntityType.Builder.<LivingArmorStandEntity>of(LivingArmorStandEntity::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "living_armor_stand").toString()));


    public static final DeferredHolder<EntityType<?>,EntityType<KineticVerticalSlash>> KINETIC_VERTICAL_SLASH =
            ENTITIES.register("kinetic_vertical_slash",() -> EntityType.Builder.<KineticVerticalSlash>of(KineticVerticalSlash::new, MobCategory.MISC)
                    .sized(1.0f,9.0f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID,"kinetic_vertical_slash").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<PaladinEntity>> PALADIN =
            ENTITIES.register("paladin",()-> EntityType.Builder.<PaladinEntity>of(PaladinEntity::new,MobCategory.MISC)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath("sunsetarmory", "paladin").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ArchangelEntity>> ARCHANGEL =
            ENTITIES.register("archangel",()-> EntityType.Builder.<ArchangelEntity>of(ArchangelEntity::new,MobCategory.MISC)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath("sunsetarmory", "archangel").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Projectile>> BLIZZARD_HAIL =
            ENTITIES.register("blizzard", () -> EntityType.Builder.<Projectile>of(BlizzardHail::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "blizzard").toString()));
}




