package net.funnydude.sunsetarmory.item.armor.custom;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.funnydude.sunsetarmory.item.armor.AbstractArmorItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RedTerrorArmorItem extends AbstractArmorItem {
    public RedTerrorArmorItem(Type pType, Properties pProperties, AttributeContainer... attributes) {
        super(ModArmorMaterials.RED_TERROR_ARMOR_MATERIAL, pType, pProperties,
                new AttributeContainer(Attributes.MOVEMENT_SPEED,0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                new AttributeContainer(AttributeRegistry.MAX_MANA,100, AttributeModifier.Operation.ADD_VALUE));
    }
}
