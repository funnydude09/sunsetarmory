package net.funnydude.sunsetarmory.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;


public class NetheriteKnightArmorItem extends AbstractArmorItem {
    public NetheriteKnightArmorItem(Type slot, Properties settings) {
        super(ModArmorMaterials.KNIGHT_ARMOR_MATERIAL, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 25, AttributeModifier.Operation.ADD_VALUE));
    }
}