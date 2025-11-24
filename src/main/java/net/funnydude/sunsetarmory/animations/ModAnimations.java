package net.funnydude.sunsetarmory.animations;

import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.Animation;

public class ModAnimations {
    public static ResourceLocation ANIMATION_RESOURCE = ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "animations");

    public static final AnimationHolder ANIMATION_DROPKICK = new AnimationHolder("sunsetarmory:animations/dropkick", true);

   }
