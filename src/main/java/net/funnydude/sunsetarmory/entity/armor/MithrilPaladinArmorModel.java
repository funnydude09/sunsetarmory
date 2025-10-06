package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class MithrilPaladinArmorModel extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            "sunsetarmory",
            "geo/armors/paladin_armor.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            "sunsetarmory",
            "textures/models/armor/mithril_paladin_armor.png"
    );

    public MithrilPaladinArmorModel() {
        super(AzArmorRendererConfig.builder(GEO, TEX).build());
    }
}
