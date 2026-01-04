package net.funnydude.sunsetarmory.entity.wizards.cultist;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class EldritchCultistRenderer extends AbstractSpellCastingMobRenderer {
    public EldritchCultistRenderer(EntityRendererProvider.Context context) {
        super(context, new EldritchCultistModel());
    }
}
