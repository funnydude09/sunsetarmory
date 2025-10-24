package net.funnydude.sunsetarmory.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class PyriumSunsetArmorItem extends AbstractArmorItem {
    public PyriumSunsetArmorItem(Type slot, Properties settings) {
        super(ModArmorMaterials.PYRIUM_SUNSET_ARMOR_MATERIAL, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 100, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.COOLDOWN_REDUCTION, 0.12, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, 0.02, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }
}
