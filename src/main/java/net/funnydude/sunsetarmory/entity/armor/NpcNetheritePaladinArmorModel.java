package net.funnydude.sunsetarmory.entity.armor;


import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.item.armor.geckolib.NpcNetheritePaladinArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class NpcNetheritePaladinArmorModel extends DefaultedItemGeoModel<NpcNetheritePaladinArmorItem> {
    public NpcNetheritePaladinArmorModel() {super(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, ""));}

    //@Override
    public ResourceLocation getModelResource(NpcNetheritePaladinArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "geo/npc_paladin_armor.geo.json");
    }

    //@Override
    public ResourceLocation getTextureResource(NpcNetheritePaladinArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "textures/models/armor/netherite_paladin_armor.png");
    }

    //@Override
    public ResourceLocation getAnimationResource(NpcNetheritePaladinArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}
