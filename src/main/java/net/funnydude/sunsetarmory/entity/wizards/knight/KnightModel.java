package net.funnydude.sunsetarmory.entity.wizards.knight;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.resources.ResourceLocation;

public class KnightModel extends AbstractSpellCastingMobModel {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("sunsetarmory", "textures/entity/knight.png");

    @Override
    public ResourceLocation getTextureResource(AbstractSpellCastingMob object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(AbstractSpellCastingMob object) {
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "animations/casting_animations.json");
    }
}