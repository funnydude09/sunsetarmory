package net.funnydude.sunsetarmory.registries;

import io.redspace.ironsspellbooks.api.attribute.MagicPercentAttribute;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
@EventBusSubscriber(modid = SunsetArmory.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModAttributes  {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, SunsetArmory.MODID);

    public static final DeferredHolder<Attribute, Attribute> KINETIC_MAGIC_RESIST = newResistanceAttribute();
    public static final DeferredHolder<Attribute, Attribute> KINETIC_SPELL_POWER =  newPowerAttribute();
    public static final DeferredHolder<Attribute,Attribute> SUNSET_ALLY = ATTRIBUTES.register("sunset_ally",()-> (new RangedAttribute("attribute.sunsetarmory.sunset_ally", 0.00,-1.0F, 1.0F)).setSyncable(true).setSyncable(true));

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent e) {
        e.getTypes().forEach(entity -> ATTRIBUTES.getEntries().forEach(attribute -> e.add(entity, attribute)));
    }

    private static DeferredHolder<Attribute, Attribute> newResistanceAttribute() {
        return  ATTRIBUTES.register("kinetic_magic_resist", () -> (new MagicPercentAttribute("attribute.sunsetarmory.kinetic_magic_resist", 1.0D, -100, 100).setSyncable(true)));
    }
    private static DeferredHolder<Attribute, Attribute> newPowerAttribute() {
        return ATTRIBUTES.register("kinetic_spell_power", () -> (new MagicPercentAttribute("attribute.sunsetarmory.kinetic_spell_power", 1.0D, -100, 100).setSyncable(true)));
    }

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

}