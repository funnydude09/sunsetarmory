package net.funnydude.sunsetarmory.entity.armor;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.item.armor.geckolib.NpcNetheritePaladinArmorItem;
import net.minecraft.resources.ResourceLocation;

public class NetheritePaladinArmorModel extends AbstractArmorModel {
    private static final ResourceLocation GEO = get("geo/armors/paladin_armor.geo.json");

    private static final ResourceLocation TEX = get("textures/models/armor/netherite_paladin_armor.png");

    public NetheritePaladinArmorModel() {
        super(AzArmorRendererConfig.builder(GEO, TEX).build());
    }

}
