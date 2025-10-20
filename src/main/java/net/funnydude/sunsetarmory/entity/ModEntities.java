package net.funnydude.sunsetarmory.entity;

import io.redspace.ironsspellbooks.entity.mobs.*;
import io.redspace.ironsspellbooks.entity.spells.*;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
import net.funnydude.sunsetarmory.entity.wizards.paladin.PaladinEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModEntities {
    private static final DeferredRegister<EntityType<?>> ENTITIES;

    public static final DeferredHolder<EntityType<?>, EntityType<KnightEntity>> KNIGHT;
    public static final DeferredHolder<EntityType<?>, EntityType<PaladinEntity>> PALADIN;

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    static {
        ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, "sunsetarmory");
        KNIGHT = ENTITIES.register("knight", () -> Builder.of(KnightEntity::new, MobCategory.MISC).sized(.6f, 1.8f).clientTrackingRange(64).build(ResourceLocation.fromNamespaceAndPath("sunsetarmory", "knight").toString()));
        PALADIN = ENTITIES.register("paladin", () -> Builder.of(PaladinEntity::new, MobCategory.MISC).sized(.6f, 1.8f).clientTrackingRange(64).build(ResourceLocation.fromNamespaceAndPath("sunsetarmory", "paladin").toString()));
    }

}


