package net.funnydude.sunsetarmory.entity.wizards.archangel;

import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import io.redspace.ironsspellbooks.entity.mobs.wizards.fire_boss.FireBossEntity;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.util.DefaultBipedBoneIdents;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.WalkAnimationState;
import org.joml.Vector2f;
import org.joml.Vector3f;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;

public class ArchangelModel extends AbstractSpellCastingMobModel {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "textures/entity/archangel.png");
    public static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "geo/archangel/archangel.geo.json");
    private static final float tilt = 15 * Mth.DEG_TO_RAD;
    private static final Vector3f forward = new Vector3f(0, 0, Mth.sin(tilt) * -12);

    @Override
    public ResourceLocation getTextureResource(AbstractSpellCastingMob object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getModelResource(AbstractSpellCastingMob object) {
        return MODEL;
    }

    @Override
    public void setCustomAnimations(AbstractSpellCastingMob entity, long instanceId, AnimationState<AbstractSpellCastingMob> animationState) {
        if (Minecraft.getInstance().isPaused()) {
            return;
        }
        if (entity instanceof FireBossEntity fireBossEntity) {
            float partialTick = animationState.getPartialTick();
            Vector2f limbSwing = getLimbSwing(entity, entity.walkAnimation, partialTick);
                fireBossEntity.isAnimatingDampener = Mth.lerp(.05f * partialTick, fireBossEntity.isAnimatingDampener, 1);

            if (entity.getMainHandItem().is(ItemRegistry.HELLRAZOR) || entity.getMainHandItem().is(ItemRegistry.DECREPIT_SCYTHE)) {
                GeoBone rightArm = this.getAnimationProcessor().getBone(PartNames.RIGHT_ARM);
                GeoBone rightHand = this.getAnimationProcessor().getBone(DefaultBipedBoneIdents.RIGHT_HAND_BONE_IDENT);
                Vector3f armPose = new Vector3f(-30, -30, 10);
                armPose.mul(Mth.DEG_TO_RAD * fireBossEntity.isAnimatingDampener);
                transformStack.pushRotation(rightArm, armPose);

                Vector3f scythePos = new Vector3f(-5, 0, -48);
                scythePos.mul(Mth.DEG_TO_RAD * fireBossEntity.isAnimatingDampener);
                transformStack.pushRotation(rightHand, scythePos);

                if (!entity.isAnimating()) {
                    float walkDampener = (Mth.cos(limbSwing.y() * 0.6662F + (float) Math.PI) * 2.0F * limbSwing.x() * 0.5F) * -.75f;
                    transformStack.pushRotation(rightArm, walkDampener, 0, 0);
                }
            }
            if (fireBossEntity.isHalfHealthAttacking()) {
                GeoBone rightArm = this.getAnimationProcessor().getBone(PartNames.RIGHT_ARM);
                GeoBone leftArm = this.getAnimationProcessor().getBone(PartNames.LEFT_ARM);
                GeoBone rightLeg = this.getAnimationProcessor().getBone(PartNames.RIGHT_LEG);
                GeoBone leftLeg = this.getAnimationProcessor().getBone(PartNames.LEFT_LEG);
                float f = (fireBossEntity.tickCount + partialTick);
                bobBone(rightArm, f * 3, -4);
                bobBone(leftArm, f * 3, 4);
                bobBone(rightLeg, f, -1.5f);
                bobBone(leftLeg, (f + 90), 1.5f);
            }
        }
        super.setCustomAnimations(entity, instanceId, animationState);
    }
    public ResourceLocation getAnimationResource(AbstractSpellCastingMob object) {
        return ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "animations/mech_casting_anims.json");
    }

    @Override
    protected Vector2f getLimbSwing(AbstractSpellCastingMob entity, WalkAnimationState walkAnimationState, float partialTick) {
        Vector2f swing = super.getLimbSwing(entity, walkAnimationState, partialTick);
        swing.mul(0.6f, 1f);
        return swing;
    }
}