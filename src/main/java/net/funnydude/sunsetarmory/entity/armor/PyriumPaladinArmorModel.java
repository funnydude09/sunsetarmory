package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class PyriumPaladinArmorModel extends AbstractArmorModel {
    private static final ResourceLocation GEO =get("geo/armors/paladin_armor.geo.json");

    private static final ResourceLocation TEX = get("textures/models/armor/pyrium_paladin_armor.png");

    public PyriumPaladinArmorModel() {
        super(AzArmorRendererConfig.builder(GEO, TEX).build());
    }
}
