package net.funnydude.sunsetarmory.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.spell.KineticSlash;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;

import java.util.Random;

public class KineticSlashRenderer extends EntityRenderer<KineticSlash> {
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[]{SunsetArmory.id("textures/entity/kinetic_slash")};
    public KineticSlashRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public void render(KineticSlash kineticSlash, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        PoseStack.Pose pose = poseStack.last();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, kineticSlash.yRotO, kineticSlash.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(partialTick, kineticSlash.xRotO, kineticSlash.getXRot())));
        float randomZ = (float)(new Random(31L * (long)kineticSlash.getId())).nextInt(-8, 8);
        poseStack.mulPose(Axis.XP.rotationDegrees(randomZ));
        this.createSlashTexturePlace(pose, kineticSlash, bufferSource, kineticSlash.getBbWidth() * 1.5F);
        poseStack.popPose();
        super.render(kineticSlash, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    private void createSlashTexturePlace(PoseStack.Pose pose, KineticSlash kineticSlash, MultiBufferSource bufferSource, float width) {
        Matrix4f poseMatrix = pose.pose();
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(kineticSlash)));
        float halfWidth = width * 0.5F;
        float height = kineticSlash.getBbHeight() * 0.5F;
        consumer.addVertex(poseMatrix, -halfWidth, height, -halfWidth).setColor(255, 255, 255, 255).setUv(0.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(0.0F, 1.0F, 0.0F);
        consumer.addVertex(poseMatrix, halfWidth, height, -halfWidth).setColor(255, 255, 255, 255).setUv(1.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(0.0F, 1.0F, 0.0F);
        consumer.addVertex(poseMatrix, halfWidth, height, halfWidth).setColor(255, 255, 255, 255).setUv(1.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(0.0F, 1.0F, 0.0F);
        consumer.addVertex(poseMatrix, -halfWidth, height, halfWidth).setColor(255, 255, 255, 255).setUv(0.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(0.0F, 1.0F, 0.0F);
    }

    public ResourceLocation getTextureLocation(KineticSlash kineticSlash) {
        int frame = kineticSlash.tickCount / 4 % TEXTURES.length;
        return TEXTURES[frame];
    }
}

