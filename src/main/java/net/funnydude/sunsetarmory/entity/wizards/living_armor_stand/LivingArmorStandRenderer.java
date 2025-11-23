package net.funnydude.sunsetarmory.entity.wizards.living_armor_stand;


import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class LivingArmorStandRenderer extends AbstractSpellCastingMobRenderer {
    public LivingArmorStandRenderer(EntityRendererProvider.Context context) {
        super(context, new LivingArmorStandModel());
    }
}
