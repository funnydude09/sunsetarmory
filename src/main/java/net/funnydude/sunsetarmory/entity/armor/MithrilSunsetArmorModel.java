package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class MithrilSunsetArmorModel extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            "sunsetarmory",
            "geo/armors/sunset_armor.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            "sunsetarmory",
            "textures/models/armor/mithril_sunset_armor.png"
    );

    public MithrilSunsetArmorModel() {
        super(AzArmorRendererConfig.builder(GEO, TEX).build());
    }
}
