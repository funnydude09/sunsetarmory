package net.funnydude.sunsetarmory.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;


public class PyriumPaladinArmorItem extends AbstractArmorItem {
    public PyriumPaladinArmorItem(Type slot, Properties settings) {
        super(ModArmorMaterials.PYRIUM_PALADIN_ARMOR_MATERIAL, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 50, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.COOLDOWN_REDUCTION, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, 0.075, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }
}