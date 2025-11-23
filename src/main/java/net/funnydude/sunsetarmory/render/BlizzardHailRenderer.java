package net.funnydude.sunsetarmory.render;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.spells.fireball.FireballRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Projectile;

public class BlizzardHailRenderer extends FireballRenderer {
    private final static ResourceLocation BASE_TEXTURE = IronsSpellbooks.id("textures/entity/comet/comet.png");
    private final static ResourceLocation FIRE_TEXTURES[] = {
            IronsSpellbooks.id("textures/entity/comet/fire_1.png"),
            IronsSpellbooks.id("textures/entity/comet/fire_2.png"),
            IronsSpellbooks.id("textures/entity/comet/fire_3.png"),
            IronsSpellbooks.id("textures/entity/comet/fire_4.png")
    };

    public BlizzardHailRenderer(Context context, float scale) {
        super(context, scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Projectile entity) {
        return BASE_TEXTURE;
    }

    public ResourceLocation getFireTextureLocation(Projectile entity) {
        int frame = (entity.tickCount / 2) % FIRE_TEXTURES.length;
        return FIRE_TEXTURES[frame];
    }

}