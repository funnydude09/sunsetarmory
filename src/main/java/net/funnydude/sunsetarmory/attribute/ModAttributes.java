package net.funnydude.sunsetarmory.attribute;

import io.redspace.ironsspellbooks.api.attribute.MagicRangedAttribute;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.w3c.dom.Attr;

public class ModAttributes  {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, SunsetArmory.MODID);
    public static final DeferredHolder<Attribute, Attribute> KINETIC_MAGIC_RESIST = ATTRIBUTES.register("kinetic_magic_resist", () -> (new MagicRangedAttribute("attribute.sunsetarmor.kinetic_magic_resist", 1.0F, -100.0F, 100.0F)).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> KINETIC_MAGIC_POWER =  ATTRIBUTES.register("kinetic_magic_spell_power",()->(new MagicRangedAttribute("attribute.sunsetarmor.kinetic_magic_spell_power", 1.0F,-100.0F,100.0F)).setSyncable(true));
    public static final DeferredHolder<Attribute,Attribute> SUNSET_ALLY = ATTRIBUTES.register("sunset_ally",()-> (new RangedAttribute("attribute.name.generic.gravity", 0.00,-1.0F, 1.0F)).setSyncable(true).setSyncable(true));


}