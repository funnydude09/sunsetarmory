package net.funnydude.sunsetarmory.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.spell.WallOfClearEffect;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class WallOfClearEffectRenderer extends EntityRenderer<WallOfClearEffect> {

    private static ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "textures/entity/...");

    public WallOfClearEffectRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(WallOfClearEffect entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutout(TEXTURE));
        Pose pose = poseStack.last();
        Matrix4f poseMatrix = pose.pose();

        float height = 3;
        Vec3 origin = entity.position();

        for (int i = 0; i < entity.subEntities.length - 1; i++) {
            Vec3 start = entity.subEntities[i].position().subtract(origin);
            Vec3 end = entity.subEntities[i + 1].position().subtract(origin);
            int frameCount = 32;
            int frame = (entity.tickCount + i * 87) % frameCount;
            float uvPerFrame = (1 / (float) frameCount);
            float uvY = frame * uvPerFrame;
            poseStack.pushPose();
            consumer.addVertex(poseMatrix, (float) start.x, (float) start.y, (float) start.z).setColor(255, 255, 255, 255).setUv(0f, uvY + uvPerFrame).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
            consumer.addVertex(poseMatrix, (float) start.x, (float) start.y + height, (float) start.z).setColor(255, 255, 255, 255).setUv(0f, uvY).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
            consumer.addVertex(poseMatrix, (float) end.x, (float) end.y + height, (float) end.z).setColor(255, 255, 255, 255).setUv(1f, uvY).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
            consumer.addVertex(poseMatrix, (float) end.x, (float) end.y, (float) end.z).setColor(255, 255, 255, 255).setUv(1f, uvY + uvPerFrame).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180));
            consumer.addVertex(poseMatrix, (float) start.x, (float) start.y, (float) start.z).setColor(255, 255, 255, 255).setUv(0f, uvY + uvPerFrame).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
            consumer.addVertex(poseMatrix, (float) start.x, (float) start.y + height, (float) start.z).setColor(255, 255, 255, 255).setUv(0f, uvY).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
            consumer.addVertex(poseMatrix, (float) end.x, (float) end.y + height, (float) end.z).setColor(255, 255, 255, 255).setUv(1f, uvY).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);
            consumer.addVertex(poseMatrix, (float) end.x, (float) end.y, (float) end.z).setColor(255, 255, 255, 255).setUv(1f, uvY + uvPerFrame).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(0f, 1f, 0f);

            poseStack.popPose();
        }
        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(WallOfClearEffect entity) {
        return TEXTURE;
    }
}