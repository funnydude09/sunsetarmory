package net.funnydude.sunsetarmory.item.armor;

import io.redspace.ironsspellbooks.item.armor.UpgradeType;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.registries.ModAttributes;
import net.funnydude.sunsetarmory.registries.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

import java.util.Optional;

public enum ModUpgradeTypes implements UpgradeType {
    KINETIC_SPELL_POWER("kinetic_power", ModItems.KINETIC_UPGRADE_ORB, ModAttributes.KINETIC_SPELL_POWER, AttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.05F);

    final Holder<Attribute> attribute;
    final AttributeModifier.Operation operation;
    final float amountPerUpgrade;
    final ResourceLocation id;
    final Optional<Holder<Item>> containerItem;

    ModUpgradeTypes(String key, Holder<Item> containerItem, Holder<Attribute> attribute, AttributeModifier.Operation operation, float amountPerUpgrade) {
        this(key, Optional.of(containerItem), attribute, operation, amountPerUpgrade);
    }

    ModUpgradeTypes(String key, Optional<Holder<Item>> containerItem, Holder<Attribute> attribute, AttributeModifier.Operation operation, float amountPerUpgrade) {
        this.id = SunsetArmory.id(key);
        this.attribute = attribute;
        this.operation = operation;
        this.amountPerUpgrade = amountPerUpgrade;
        this.containerItem = containerItem;
        UpgradeType.registerUpgrade(this);
    }

    @Override
    public Holder<Attribute> getAttribute() {
        return attribute;
    }

    @Override
    public AttributeModifier.Operation getOperation() {
        return operation;
    }

    @Override
    public float getAmountPerUpgrade() {
        return amountPerUpgrade;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public Optional<Holder<Item>> getContainerItem() {
        return containerItem;
    }
}