package net.funnydude.sunsetarmory.entity.render;


import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class KnightRenderer extends AbstractSpellCastingMobRenderer {

    public KnightRenderer(EntityRendererProvider.Context context) {
        super(context, new KnightModel());
    }

}
