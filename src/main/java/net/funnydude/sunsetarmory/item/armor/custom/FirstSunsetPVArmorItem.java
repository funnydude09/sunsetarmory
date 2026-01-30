package net.funnydude.sunsetarmory.item.armor.custom;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.funnydude.sunsetarmory.item.armor.AbstractArmorItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FirstSunsetPVArmorItem extends AbstractArmorItem {
    public FirstSunsetPVArmorItem(Type pType, Properties pProperties, AttributeContainer... attributes) {
        super(ModArmorMaterials.FIRST_SUNSET_PV_ARMOR_MATERIAL, pType, pProperties,
                new AttributeContainer(Attributes.MOVEMENT_SPEED,0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER,0.4, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }
}
