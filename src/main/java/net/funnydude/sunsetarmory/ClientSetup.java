package net.funnydude.sunsetarmory;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.fluids.SimpleClientFluidType;
import io.redspace.ironsspellbooks.fluids.SimpleTintedClientFluidType;
import io.redspace.ironsspellbooks.registries.FluidRegistry;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.funnydude.sunsetarmory.entity.ModEntities;
import net.funnydude.sunsetarmory.fluids.ModFluids;
import net.funnydude.sunsetarmory.render.BlizzardHailRenderer;
import net.funnydude.sunsetarmory.render.KineticSlashRenderer;
import net.funnydude.sunsetarmory.render.KineticVerticalSlashRenderer;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelRenderer;
import net.funnydude.sunsetarmory.render.KnightRenderer;
import net.funnydude.sunsetarmory.entity.wizards.living_armor_stand.LivingArmorStandRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

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
       event.registerEntityRenderer(ModEntities.LIVING_ARMOR_STAND.get(), LivingArmorStandRenderer::new);
     }

     @SubscribeEvent
     public static void registerClientExtensions(RegisterClientExtensionsEvent event){
         event.registerFluidType(new SimpleClientFluidType(SunsetArmory.id("block/arcane_mixture")), ModFluids.ARCANE_MIXTURE_TYPE);
         event.registerFluidType(new SimpleClientFluidType(SunsetArmory.id("block/fire_mixture")), ModFluids.FIRE_MIXTURE_TYPE);
         event.registerFluidType(new SimpleClientFluidType(SunsetArmory.id("block/nether_mixture")), ModFluids.NETHER_MIXTURE_TYPE);
     }

     static void onClientSetup(FMLClientSetupEvent event) {

     }
}
