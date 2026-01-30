package net.funnydude.sunsetarmory.registries;

import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.resources.ResourceLocation;

public class ModAnimations {
    public static ResourceLocation ANIMATION_RESOURCE = ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "animations");

    public static final AnimationHolder ANIMATION_DROPKICK = Animation("sunsetarmory:dropkick");
    public static final AnimationHolder ARMOR_LOCK_CAST = Animation("sunsetarmory:armor_lock_cast");
    public static final AnimationHolder GRAND_SMITE = Animation("sunsetarmory:grand_smite");
    public static final AnimationHolder ARMOR_LOCK = Animation("sunsetarmory:armor_lock");
    public static final AnimationHolder ARMOR_LOCK_RECOVER = Animation("sunsetarmory:armor_lock_recover");

    public static AnimationHolder Animation(String path){
        return new AnimationHolder(path,true,true);
    }
}
