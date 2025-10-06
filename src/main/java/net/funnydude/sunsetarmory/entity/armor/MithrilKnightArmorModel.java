package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class MithrilKnightArmorModel extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            "sunsetarmory",
            "geo/armors/knight_armor.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            "sunsetarmory",
            "textures/models/armor/mithril_knight_armor.png"
    );

    public MithrilKnightArmorModel() {
        super(AzArmorRendererConfig.builder(GEO, TEX).build());
    }
}
