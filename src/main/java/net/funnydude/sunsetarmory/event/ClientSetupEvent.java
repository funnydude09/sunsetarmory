package net.funnydude.sunsetarmory.event;

import io.redspace.ironsspellbooks.fluids.SimpleClientFluidType;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.registries.ModEntities;
import net.funnydude.sunsetarmory.registries.ModFluids;
import net.funnydude.sunsetarmory.render.*;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelRenderer;
import net.funnydude.sunsetarmory.entity.wizards.living_armor_stand.LivingArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = SunsetArmory.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvent {
    private static final long ARMOR_LOCK = 2;
    private static final long COOL_DOWN = 2;

    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
       event.registerEntityRenderer(ModEntities.KNIGHT.get(), KnightRenderer::new);
       event.registerEntityRenderer(ModEntities.KINETIC_SLASH.get(), KineticSlashRenderer::new);
       event.registerEntityRenderer(ModEntities.KINETIC_VERTICAL_SLASH.get(), KineticVerticalSlashRenderer::new);
       event.registerEntityRenderer(ModEntities.PALADIN.get(), KnightRenderer::new);
       event.registerEntityRenderer(ModEntities.ARCHANGEL.get(), ArchangelRenderer::new);
       event.registerEntityRenderer(ModEntities.BLIZZARD_HAIL.get(), (context) -> new BlizzardHailRenderer(context, 0.75f));
       event.registerEntityRenderer(ModEntities.LIVING_ARMOR_STAND.get(), LivingArmorStandRenderer::new);
     }

     @SubscribeEvent
     public static void registerRenderers(final EntityRenderersEvent.AddLayers event){
         addLayerToPlayerSkin(event,PlayerSkin.Model.SLIM);
         addLayerToPlayerSkin(event,PlayerSkin.Model.WIDE);
     }

     @SubscribeEvent
     public static void registerClientExtensions(RegisterClientExtensionsEvent event){
         event.registerFluidType(new SimpleClientFluidType(SunsetArmory.id("block/arcane_mixture")), ModFluids.ARCANE_MIXTURE_TYPE);
         event.registerFluidType(new SimpleClientFluidType(SunsetArmory.id("block/fire_mixture")), ModFluids.FIRE_MIXTURE_TYPE);
         event.registerFluidType(new SimpleClientFluidType(SunsetArmory.id("block/nether_mixture")), ModFluids.NETHER_MIXTURE_TYPE);
     }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, PlayerSkin.Model skinName) {
        EntityRenderer<? extends Player> render = event.getSkin(skinName);
        if (render instanceof LivingEntityRenderer livingRenderer) {
            livingRenderer.addLayer(new CoolDownSwirlRenderer.Vanilla(livingRenderer, SunsetArmory.id("textures/entity/cool_down_layer.png"), COOL_DOWN));
            livingRenderer.addLayer(new ArmorLockSwirlRenderer.Vanilla(livingRenderer, SunsetArmory.id("textures/entity/armor_lock_layer.png"), ARMOR_LOCK));
        }
    }

     static void onClientSetup(FMLClientSetupEvent event) {

     }
}
