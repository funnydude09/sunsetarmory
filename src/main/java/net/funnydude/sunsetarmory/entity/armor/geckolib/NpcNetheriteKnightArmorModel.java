package net.funnydude.sunsetarmory.entity.armor.geckolib;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.item.armor.geckolib.NpcNetheriteKnightArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class NpcNetheriteKnightArmorModel extends DefaultedItemGeoModel<NpcNetheriteKnightArmorItem> {
    public NpcNetheriteKnightArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, ""));
    }

    //@Override
    public ResourceLocation getModelResource(NpcNetheriteKnightArmorModel object) {
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "geo/npc_knight_armor.geo.json");
    }

   // @Override
    public ResourceLocation getTextureResource(NpcNetheriteKnightArmorModel object) {
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "textures/models/armor/netherite_knight_armor.png");
    }


    //public ResourceLocation getAnimationResource(NpcNetheriteKnightArmorModel animatable) {
   //     return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "animations/wizard_armor_animation.json");
    //}
}
