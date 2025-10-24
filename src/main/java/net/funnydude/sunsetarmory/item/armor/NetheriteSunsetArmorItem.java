package net.funnydude.sunsetarmory.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class NetheriteSunsetArmorItem extends AbstractArmorItem {
    public NetheriteSunsetArmorItem(Type slot, Properties settings) {
        super(ModArmorMaterials.SUNSET_ARMOR_MATERIAL, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 100, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.BLOOD_MAGIC_RESIST, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.ELDRITCH_MAGIC_RESIST, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }
}