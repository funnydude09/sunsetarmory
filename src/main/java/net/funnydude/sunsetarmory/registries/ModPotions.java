package net.funnydude.sunsetarmory.registries;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, SunsetArmory.MODID);

    public static final Holder<Potion> RAGE_POTION = POTIONS.register("rage_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.RAGE_EFFECT,1800,0)));
   public static final Holder<Potion> AMBROSIA_POTION = POTIONS.register("ambrosia_potion",
           () -> new Potion(new MobEffectInstance(ModEffects.AMBROSIA_EFFECT,3600,0), new MobEffectInstance(MobEffects.ABSORPTION,3600,2)));
    public static final Holder<Potion> WITHERING_POTION = POTIONS.register("withering_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER,200,1)));
    public static final Holder<Potion> REND_POTION = POTIONS.register("rend_potion",
            () -> new Potion(new MobEffectInstance(MobEffectRegistry.REND,100,19)));

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
