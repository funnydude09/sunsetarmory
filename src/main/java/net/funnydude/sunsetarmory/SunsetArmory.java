package net.funnydude.sunsetarmory;

import io.redspace.ironsspellbooks.entity.mobs.wizards.cultist.CultistEntity;
import io.redspace.ironsspellbooks.entity.mobs.wizards.cultist.CultistRenderer;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererRegistry;
import net.funnydude.sunsetarmory.block.ModBlocks;
import net.funnydude.sunsetarmory.effect.ModEffects;
import net.funnydude.sunsetarmory.entity.ModEntities;
import net.funnydude.sunsetarmory.entity.armor.*;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightRenderer;
import net.funnydude.sunsetarmory.item.ModCreativeModeTabs;
import net.funnydude.sunsetarmory.item.ModItems;
import net.funnydude.sunsetarmory.potion.ModPotions;
import net.funnydude.sunsetarmory.spell.ModSpells;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SunsetArmory.MODID)
public class SunsetArmory {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "sunsetarmory";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
   public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
   public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
   public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
  //  public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
  //  public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
  //  public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
   //         .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    // public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
     //       .title(Component.translatable("itemGroup.examplemod")) //The language key for the title of your CreativeModeTab
     //       .withTabsBefore(CreativeModeTabs.COMBAT)
     //       .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
     //       .displayItems((parameters, output) -> {
      //          output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
      //      }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public SunsetArmory(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModPotions.register(modEventBus);
        ModSpells.register(modEventBus);
        ModEffects.register(modEventBus);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover me thods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }


   //

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = SunsetArmory.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    static class ClientModEvents {
        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
            AzArmorRendererRegistry.register(NetheriteKnightArmorModel::new,
                    ModItems.KNIGHT_HELMET.get(),
                    ModItems.KNIGHT_CHESTPLATE.get(),
                    ModItems.KNIGHT_LEGGINGS.get(),
                    ModItems.KNIGHT_BOOTS.get());
            AzArmorRendererRegistry.register(PyriumKnightArmorModel::new,
                    ModItems.PYRIUM_KNIGHT_HELMET.get(),
                    ModItems.PYRIUM_KNIGHT_CHESTPLATE.get(),
                    ModItems.PYRIUM_KNIGHT_LEGGINGS.get(),
                    ModItems.PYRIUM_KNIGHT_BOOTS.get());
            AzArmorRendererRegistry.register(MithrilKnightArmorModel::new,
                    ModItems.MITHRIL_KNIGHT_HELMET.get(),
                    ModItems.MITHRIL_KNIGHT_CHESTPLATE.get(),
                    ModItems.MITHRIL_KNIGHT_LEGGINGS.get(),
                    ModItems.MITHRIL_KNIGHT_BOOTS.get());

            AzArmorRendererRegistry.register(NetheritePaladinArmorModel::new,
                    ModItems.PALADIN_HELMET.get(),
                    ModItems.PALADIN_CHESTPLATE.get(),
                    ModItems.PALADIN_LEGGINGS.get(),
                    ModItems.PALADIN_BOOTS.get());
            AzArmorRendererRegistry.register(PyriumPaladinArmorModel::new,
                    ModItems.PYRIUM_PALADIN_HELMET.get(),
                    ModItems.PYRIUM_PALADIN_CHESTPLATE.get(),
                    ModItems.PYRIUM_PALADIN_LEGGINGS.get(),
                    ModItems.PYRIUM_PALADIN_BOOTS.get());
            AzArmorRendererRegistry.register(MithrilPaladinArmorModel::new,
                    ModItems.MITHRIL_PALADIN_HELMET.get(),
                    ModItems.MITHRIL_PALADIN_CHESTPLATE.get(),
                    ModItems.MITHRIL_PALADIN_LEGGINGS.get(),
                    ModItems.MITHRIL_PALADIN_BOOTS.get());

            AzArmorRendererRegistry.register(NetheriteSunsetArmorModel::new,
                    ModItems.SUNSET_BOOTS.get(),
                    ModItems.SUNSET_CHESTPLATE.get(),
                    ModItems.SUNSET_LEGGINGS.get(),
                    ModItems.SUNSET_HELMET.get());

            AzArmorRendererRegistry.register(PyriumSunsetArmorModel::new,
                    ModItems.PYRIUM_SUNSET_BOOTS.get(),
                    ModItems.PYRIUM_SUNSET_CHESTPLATE.get(),
                    ModItems.PYRIUM_SUNSET_LEGGINGS.get(),
                    ModItems.PYRIUM_SUNSET_HELMET.get());

            AzArmorRendererRegistry.register(MithrilSunsetArmorModel::new,
                    ModItems.MITHRIL_SUNSET_BOOTS.get(),
                    ModItems.MITHRIL_SUNSET_CHESTPLATE.get(),
                    ModItems.MITHRIL_SUNSET_LEGGINGS.get(),
                    ModItems.MITHRIL_SUNSET_HELMET.get());
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
    public static ResourceLocation id(@NotNull String path) {
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, path);
    }

}
