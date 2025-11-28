package net.funnydude.sunsetarmory.registries;

import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.resources.ResourceLocation;

public class ModAnimations {
    public static ResourceLocation ANIMATION_RESOURCE = ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "animations");

    public static final AnimationHolder ANIMATION_DROPKICK = new AnimationHolder("sunsetarmory:dropkick", true,true);
    public static final AnimationHolder ARMOR_LOCK_CAST = new AnimationHolder("sunsetarmory:armor_lock_cast", true,true);
    public static final AnimationHolder GRAND_SMITE = new AnimationHolder("sunsetarmory:grand_smite", true);
    public static final AnimationHolder ARMOR_LOCK = new AnimationHolder("sunsetarmory:armor_lock", true,true);
    public static final AnimationHolder ARMOR_LOCK_RECOVER = new AnimationHolder("sunsetarmory:armor_lock_recover", true,true);

   }
