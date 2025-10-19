package net.funnydude.sunsetarmory.entity.armor;


import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.item.armor.NetheriteMageArmorItem;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.item.armor.geckolib.NpcNetheriteKnightArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class NpcNetheriteKnightArmorModel extends DefaultedItemGeoModel<NpcNetheriteKnightArmorItem> {
    public NpcNetheriteKnightArmorModel() {super(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, ""));}

    //@Override
    public ResourceLocation getModelResource(NpcNetheriteKnightArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "geo/npc_knight_armor.geo.json");
    }

    //@Override
    public ResourceLocation getTextureResource(NpcNetheriteKnightArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "textures/models/armor/netherite_knight_armor.png");
    }

    //@Override
    public ResourceLocation getAnimationResource(NpcNetheriteKnightArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}
