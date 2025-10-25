package net.funnydude.sunsetarmory.entity.wizards.archangel;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobRenderer;
import io.redspace.ironsspellbooks.entity.mobs.wizards.fire_boss.*;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.render.RenderHelper;
import io.redspace.ironsspellbooks.util.DefaultBipedBoneIdents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.util.Color;

public class ArchangelRenderer extends AbstractSpellCastingMobRenderer {

    public ArchangelRenderer(EntityRendererProvider.Context context) {
        super(context, new ArchangelModel());
        this.shadowRadius = 0.65f;
    }

    @Override
    public void render(AbstractSpellCastingMob entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity instanceof ArchangelEntity archangel && archangel.isSpawning()) {
            float f = archangel.getSpawnWalkPercent(partialTick);
            if (f == 0) {
                // not visible, dont render
                return;
            }
            shadowRadius = Mth.lerp(f, 2, .65f);
            shadowStrength = Mth.lerp(f, 0, 1);
        } else {
            shadowStrength = 1;
            shadowRadius = .65f;
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, Math.clamp(packedLight + 100, 0, LightTexture.FULL_BLOCK));
    }

    @Override
    public void applyRenderLayersForBone(PoseStack poseStack, AbstractSpellCastingMob animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        super.applyRenderLayersForBone(poseStack, animatable, bone, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
        if (bone.getName().equals(DefaultBipedBoneIdents.LEFT_HAND_BONE_IDENT) && animatable instanceof ArchangelEntity archangel && archangel.spectralDaggerActive()) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotationDegrees(90));
            //[-0.375,.1,-1]
            poseStack.translate(-0.375, .1, -1);
            Minecraft.getInstance().getItemRenderer().render(
                    ItemRegistry.HELLRAZOR.get().getDefaultInstance(),
                    ItemDisplayContext.THIRD_PERSON_LEFT_HAND,
                    true,
                    poseStack,
                    bufferSource, packedLight, packedOverlay,
                    Minecraft.getInstance().getModelManager().getModel(ModelResourceLocation.standalone(IronsSpellbooks.id("item/fiery_dagger")))
            );
            poseStack.popPose();
        }
    }

    @Override
    public int getPackedOverlay(AbstractSpellCastingMob animatable, float u, float partialTick) {
        //disable hurt/death red flashing
        return OverlayTexture.NO_OVERLAY;
    }

    static final int deathFadeTime = 120;

    @Override
    public Color getRenderColor(AbstractSpellCastingMob animatable, float partialTick, int packedLight) {
        Color color = super.getRenderColor(animatable, partialTick, packedLight);
        float f = 1f;
        if (animatable.deathTime > 160 - deathFadeTime) {
            f = Mth.clamp((160 - animatable.deathTime) / (float) deathFadeTime, 0, 1f);
        } else if (animatable instanceof ArchangelEntity archangel && archangel.isSpawning()) {
            f = archangel.getSpawnWalkPercent(partialTick);
        }
        if (!animatable.isInvisible() && f != 1) {
            color = new Color(RenderHelper.colorf(1f, 1f, 1f, f));
        }

        return color;
    }


    @Override
    public RenderType getRenderType(AbstractSpellCastingMob animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        if (animatable.isDeadOrDying() || animatable instanceof ArchangelEntity archangel && (archangel.isSpawning() )) {
            return RenderType.entityTranslucent(texture);
        }
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    protected float getDeathMaxRotation(AbstractSpellCastingMob animatable) {
        return 0;
    }
}
