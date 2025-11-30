package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class PyriumSunsetArmorModel extends AbstractArmorModel {
    private static final ResourceLocation GEO = get("geo/armors/sunset_armor.geo.json");

    private static final ResourceLocation TEX = get("textures/models/armor/pyrium_sunset_armor.png");

    public PyriumSunsetArmorModel() {
        super(AzArmorRendererConfig.builder(GEO, TEX).build());
    }
}
