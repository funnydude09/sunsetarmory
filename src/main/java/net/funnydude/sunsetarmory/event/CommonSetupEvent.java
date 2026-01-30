package net.funnydude.sunsetarmory.event;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.spell.KineticSentryTurret;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelEntity;
import net.funnydude.sunsetarmory.entity.wizards.cultist.EldritchCultistEntity;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
import net.funnydude.sunsetarmory.entity.wizards.living_armor_stand.LivingArmorStandEntity;
import net.funnydude.sunsetarmory.entity.wizards.paladin.PaladinEntity;
import net.funnydude.sunsetarmory.registries.ModEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = SunsetArmory.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CommonSetupEvent {
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
         event.put(ModEntities.KNIGHT.get(), KnightEntity.prepareAttributes().build());
         event.put(ModEntities.PALADIN.get(), PaladinEntity.prepareAttributes().build());
         event.put(ModEntities.ARCHANGEL.get(), ArchangelEntity.prepareAttributes().build());
         event.put(ModEntities.ELDRITCH_CULTIST.get(), EldritchCultistEntity.prepareAttributes().build());
         event.put(ModEntities.LIVING_ARMOR_STAND.get(), LivingArmorStandEntity.prepareAttributes().build());
         event.put(ModEntities.KINETIC_SENTRY_TURRET.get(), KineticSentryTurret.prepareAttributes().build());
    }
}
