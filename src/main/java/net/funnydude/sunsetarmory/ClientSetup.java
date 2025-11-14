package net.funnydude.sunsetarmory;

import io.redspace.ironsspellbooks.entity.spells.comet.CometRenderer;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import mod.azure.azurelib.core.math.functions.classic.Mod;
import net.funnydude.sunsetarmory.entity.ModEntities;
import net.funnydude.sunsetarmory.entity.render.BlizzardHailRenderer;
import net.funnydude.sunsetarmory.entity.render.KineticSlashRenderer;
import net.funnydude.sunsetarmory.entity.render.KineticVerticalSlashRenderer;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelRenderer;
import net.funnydude.sunsetarmory.entity.render.KnightRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = SunsetArmory.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
       event.registerEntityRenderer(ModEntities.KNIGHT.get(), KnightRenderer::new);
       event.registerEntityRenderer(ModEntities.KINETIC_SLASH.get(), KineticSlashRenderer::new);
       event.registerEntityRenderer(ModEntities.KINETIC_VERTICAL_SLASH.get(), KineticVerticalSlashRenderer::new);
       event.registerEntityRenderer(ModEntities.PALADIN.get(), KnightRenderer::new);
       event.registerEntityRenderer(ModEntities.ARCHANGEL.get(), ArchangelRenderer::new);
        event.registerEntityRenderer(ModEntities.BLIZZARD_HAIL.get(), (context) -> new BlizzardHailRenderer(context, 0.75f));
     }

     static void onClientSetup(FMLClientSetupEvent event) {

    }
}
