package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class MithrilPaladinArmorModel extends AbstractArmorModel {
    private static final ResourceLocation GEO =get("geo/armors/paladin_armor.geo.json");

    private static final ResourceLocation TEX =get("textures/models/armor/mithril_paladin_armor.png");

    public MithrilPaladinArmorModel() {
        super(AzArmorRendererConfig.builder(GEO, TEX).build());
    }
}
