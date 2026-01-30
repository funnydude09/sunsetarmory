package net.funnydude.sunsetarmory.item.armor.custom;

import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.funnydude.sunsetarmory.item.armor.AbstractArmorItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BlueNightmareArmorItem extends AbstractArmorItem {
    public BlueNightmareArmorItem(Type pType, Properties pProperties, AttributeContainer... attributes) {
        super(ModArmorMaterials.BLUE_NIGHTMARE_ARMOR_MATERIAL, pType, pProperties,
                new AttributeContainer(Attributes.MOVEMENT_SPEED,0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                new AttributeContainer(Attributes.ATTACK_DAMAGE,3, AttributeModifier.Operation.ADD_VALUE));
    }
}
