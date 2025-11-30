package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class NetheriteKnightArmorModel extends AbstractArmorModel {
    private static final ResourceLocation GEO = get("geo/armors/knight_armor.geo.json");

    private static final ResourceLocation TEX = get("textures/models/armor/netherite_knight_armor.png");

    public NetheriteKnightArmorModel() {
        super(AzArmorRendererConfig.builder(GEO, TEX).build());
    }
}
