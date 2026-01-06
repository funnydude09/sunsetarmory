package net.funnydude.sunsetarmory.registries;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public  static  final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SunsetArmory.MODID);

    public static final Supplier<CreativeModeTab> SUNSET_ARMORY_TAB = CREATIVE_MODE_TAB.register("sunset_armory_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PYRIUM_SUNSET_HELMET.get()))
                    .title(Component.translatable("creativetab.sunsetarmory.sunset_armory_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.KNIGHT_HELMET);
                        output.accept(ModItems.PYRIUM_KNIGHT_HELMET);
                        output.accept(ModItems.MITHRIL_KNIGHT_HELMET);

                        output.accept(ModItems.PALADIN_HELMET);
                        output.accept(ModItems.PYRIUM_PALADIN_HELMET);
                        output.accept(ModItems.MITHRIL_PALADIN_HELMET);

                        output.accept(ModItems.SUNSET_HELMET);
                        output.accept(ModItems.PYRIUM_SUNSET_HELMET);
                        output.accept(ModItems.MITHRIL_SUNSET_HELMET);

                        output.accept(ModItems.KNIGHT_CHESTPLATE);
                        output.accept(ModItems.PYRIUM_KNIGHT_CHESTPLATE);
                        output.accept(ModItems.MITHRIL_KNIGHT_CHESTPLATE);

                        output.accept(ModItems.PALADIN_CHESTPLATE);
                        output.accept(ModItems.PYRIUM_PALADIN_CHESTPLATE);
                        output.accept(ModItems.MITHRIL_PALADIN_CHESTPLATE);

                        output.accept(ModItems.SUNSET_CHESTPLATE);
                        output.accept(ModItems.PYRIUM_SUNSET_CHESTPLATE);
                        output.accept(ModItems.MITHRIL_SUNSET_CHESTPLATE);

                        output.accept(ModItems.KNIGHT_LEGGINGS);
                        output.accept(ModItems.PYRIUM_KNIGHT_LEGGINGS);
                        output.accept(ModItems.MITHRIL_KNIGHT_LEGGINGS);

                        output.accept(ModItems.PALADIN_LEGGINGS);
                        output.accept(ModItems.PYRIUM_PALADIN_LEGGINGS);
                        output.accept(ModItems.MITHRIL_PALADIN_LEGGINGS);

                        output.accept(ModItems.SUNSET_LEGGINGS);
                        output.accept(ModItems.PYRIUM_SUNSET_LEGGINGS);
                        output.accept(ModItems.MITHRIL_SUNSET_LEGGINGS);

                        output.accept(ModItems.KNIGHT_BOOTS);
                        output.accept(ModItems.PYRIUM_KNIGHT_BOOTS);
                        output.accept(ModItems.MITHRIL_KNIGHT_BOOTS);

                        output.accept(ModItems.PALADIN_BOOTS);
                        output.accept(ModItems.PYRIUM_PALADIN_BOOTS);
                        output.accept(ModItems.MITHRIL_PALADIN_BOOTS);

                        output.accept(ModItems.SUNSET_BOOTS);
                        output.accept(ModItems.PYRIUM_SUNSET_BOOTS);
                        output.accept(ModItems.MITHRIL_SUNSET_BOOTS);

                        output.accept(ModItems.NETHERITE_LONGSWORD);
                        output.accept(ModItems.PYRIUM_LONGSWORD);
                        output.accept(ModItems.MITHRIL_LONGSWORD);

                        output.accept(ModItems.NETHERITE_GREATSWORD);
                        output.accept(ModItems.PYRIUM_GREATSWORD);
                        output.accept(ModItems.MITHRIL_GREATSWORD);

                        output.accept(ModItems.NETHERITE_SPEAR);
                        output.accept(ModItems.PYRIUM_SPEAR);
                        output.accept(ModItems.MITHRIL_SPEAR);

                        output.accept(ModItems.ARCANE_FLAMBERGE);
                        output.accept(ModItems.ARCANE_REAPER);
                        output.accept(ModItems.SMOKE_BOMB);
                        output.accept(ModItems.PYRIUM_SPELL_BOOK);
                        output.accept(ModItems.SUMMON_KNIGHTS);
                        output.accept(ModItems.SUMMON_ARCHANGEL);
                        output.accept(ModItems.D4C_LOVE_TRAIN);

                        output.accept(ModItems.CHAINMAIL);
                        output.accept(ModItems.DECREPIT_SCRAP);
                        output.accept(ModItems.PYRIUM_SCRAP);
                        output.accept(ModBlocks.PYRIUM_BLOCK);
                        output.accept(ModItems.MITHRIL_UPGRADE_TEMPLATE);

                        output.accept(ModItems.NETHER_VIAL);
                        output.accept(ModItems.PYRIUM_VIAL);
                        output.accept(ModItems.ARCANE_VIAL);

                        output.accept(ModItems.UNSTABLE_KNIGHTSTEEL);
                        output.accept(ModItems.HOLLOW_KNIGHTSTEEL);

                        output.accept(ModItems.FORTIFIED_KNIGHTSTEEL);
                        output.accept(ModItems.SCORCHED_KNIGHTSTEEL);
                        output.accept(ModItems.POTENT_KNIGHTSTEEL);

                        output.accept(ModItems.SUNSET_BANNER.get());
                        output.accept(ModItems.BLOOD_CULTIST_BANNER.get());
                        output.accept(ModItems.ELDRITCH_CULTIST_BANNER.get());

                        output.accept(ModItems.LOVE_LANGUAGEHATRED);
                        output.accept(ModItems.ORTU_SOLIS_STANDS);
                        output.accept(ModItems.NIGHT_BLADE);
                        output.accept(ModItems.GOLDEN_SPEAR);
                        output.accept(ModItems.TRUTH_ENFORCER);
                        output.accept(ModItems.SACRIFICIAL_DAGGER);





                    }).build());

    public  static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
