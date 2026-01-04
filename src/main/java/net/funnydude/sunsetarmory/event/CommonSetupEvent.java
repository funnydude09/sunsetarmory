package net.funnydude.sunsetarmory.event;

import io.redspace.ironsspellbooks.entity.mobs.wizards.cultist.CultistEntity;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelEntity;
import net.funnydude.sunsetarmory.entity.wizards.cultist.EldritchCultistEntity;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
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
    }
}
