package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.rewrite.render.AzRendererConfig;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public abstract class AbstractArmorModel extends AzArmorRenderer {

    public AbstractArmorModel(AzRendererConfig<ItemStack> config) {
        super(config);
    }

    public static ResourceLocation get(@NotNull String path){
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, path);
    }

}
