package net.funnydude.sunsetarmory.item;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.block.ModBlocks;
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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.JOHN.get()))
                    .title(Component.translatable("creativetab.sunsetarmory.sunset_armory_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CHAINMAIL);
                        output.accept(ModItems.KNIGHT_HELMET);
                        output.accept(ModItems.KNIGHT_CHESTPLATE);
                        output.accept(ModItems.KNIGHT_LEGGINGS);
                        output.accept(ModItems.KNIGHT_BOOTS);
                    }).build());

    public  static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
