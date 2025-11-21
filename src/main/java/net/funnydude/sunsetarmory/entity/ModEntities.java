package net.funnydude.sunsetarmory.entity;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.*;
import io.redspace.ironsspellbooks.entity.spells.*;
import io.redspace.ironsspellbooks.entity.spells.comet.Comet;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.spell.*;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelEntity;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
import net.funnydude.sunsetarmory.entity.wizards.paladin.PaladinEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
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
            = ENTITIES.register("knight_entity", () -> EntityType.Builder.<KnightEntity>of(KnightEntity::new, MobCategory.MISC)
            .sized(.6f, 1.8f)
            .clientTrackingRange(64)
            .build(ResourceLocation.fromNamespaceAndPath("sunsetarmory", "knight_entity").toString()));

    public static final Supplier<EntityType<Entity>> DIVINE_SHEILD_ENTITY =
            ENTITIES.register("divine_shield_entity", () -> EntityType.Builder.of(DivineShieldEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "divine_shield_entity").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<KineticSlash>> KINETIC_SLASH =
            ENTITIES.register("kinetic_slash_entity", () -> EntityType.Builder.<KineticSlash>of(KineticSlash::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "kinetic_slash_entity").toString()));

    public static final DeferredHolder<EntityType<?>,EntityType<KineticVerticalSlash>> KINETIC_VERTICAL_SLASH =
            ENTITIES.register("kinetic_vertical_slash_entity",() -> EntityType.Builder.<KineticVerticalSlash>of(KineticVerticalSlash::new, MobCategory.MISC)
                    .sized(1.0f,9.0f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID,"kinetic_vertical_slash_entity").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<PaladinEntity>> PALADIN =
            ENTITIES.register("paladin_entity",()-> EntityType.Builder.<PaladinEntity>of(PaladinEntity::new,MobCategory.MISC)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath("sunsetarmory", "paladin_entity").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ArchangelEntity>> ARCHANGEL =
            ENTITIES.register("archangel_entity",()-> EntityType.Builder.<ArchangelEntity>of(ArchangelEntity::new,MobCategory.MISC)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath("sunsetarmory", "archangel_entity").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Projectile>> BLIZZARD_HAIL =
            ENTITIES.register("blizzard_entity", () -> EntityType.Builder.<Projectile>of(BlizzardHail::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "blizzard_entity").toString()));
}




