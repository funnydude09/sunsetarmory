package net.funnydude.sunsetarmory;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererRegistry;
import net.funnydude.sunsetarmory.entity.ModEntities;
import net.funnydude.sunsetarmory.entity.armor.*;
import net.funnydude.sunsetarmory.entity.render.KineticSlashRenderer;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelModel;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelRenderer;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightRenderer;
import net.funnydude.sunsetarmory.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
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
       event.registerEntityRenderer( ModEntities.KINETIC_SLASH.get(), KineticSlashRenderer::new);
        event.registerEntityRenderer(ModEntities.PALADIN.get(), KnightRenderer::new);
        event.registerEntityRenderer(ModEntities.ARCHANGEL.get(), ArchangelRenderer::new);
     }

     static void onClientSetup(FMLClientSetupEvent event) {

    }
}
