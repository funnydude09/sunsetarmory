package net.funnydude.sunsetarmory.registries;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, SunsetArmory.MODID);

    public static void register(IEventBus eventBus) {
        ATTACHMENT.register(eventBus);
    }
}

