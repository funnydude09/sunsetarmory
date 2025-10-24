package net.funnydude.sunsetarmory.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;


public class NetheritePaladinArmorItem extends AbstractArmorItem {
    public NetheritePaladinArmorItem(Type slot, Properties settings) {
        super(ModArmorMaterials.PALADIN_ARMOR_MATERIAL, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 50, AttributeModifier.Operation.ADD_VALUE));
    }
}