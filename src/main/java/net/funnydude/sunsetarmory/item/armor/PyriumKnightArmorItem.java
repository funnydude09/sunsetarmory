package net.funnydude.sunsetarmory.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class PyriumKnightArmorItem extends AbstractArmorItem {
    public PyriumKnightArmorItem(Type slot, Properties settings) {
        super(ModArmorMaterials.PYRIUM_KNIGHT_ARMOR_MATERIAL, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 25, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.COOLDOWN_REDUCTION, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }
}