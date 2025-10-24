package net.funnydude.sunsetarmory.entity.armor;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class NetheriteKnightArmorModel extends AbstractArmorModel {
    private static final ResourceLocation GEO = get("geo/armors/knight_armor.geo.json");

    private static final ResourceLocation TEX = get("textures/models/armor/netherite_knight_armor.png");

    public NetheriteKnightArmorModel() {
        super(AzArmorRendererConfig.builder(GEO, TEX).build());
    }
}
