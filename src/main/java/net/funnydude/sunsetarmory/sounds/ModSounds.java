package net.funnydude.sunsetarmory.sounds;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModSounds {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, SunsetArmory.MODID);

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    //TODO: bring sound locations into spec
   public static DeferredHolder<SoundEvent, SoundEvent> KINETIC_CAST = registerSoundEvent("kinetic_cast");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, name)));
    }
}
