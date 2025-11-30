package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public abstract class AbstractArmorModel extends AzArmorRenderer {

    public AbstractArmorModel(AzArmorRendererConfig config) {
        super(config);
    }

    public static ResourceLocation get(@NotNull String path){
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, path);
    }

}
