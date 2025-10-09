package net.funnydude.sunsetarmory.item.weapons;


import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.IronsWeaponTier;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class SunsetWeapons implements Tier, IronsWeaponTier {
    //public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons HELLRAZOR = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(2031, 12, -2.6f, 25, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(Items.NETHERITE_SCRAP));
   // public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons LEGIONNAIRE_FLAMBERGE = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(2031, 10, -2.5f, 4, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(Items.NETHERITE_SCRAP), new AttributeContainer(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE));
   // public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons DECREPIT_FLAMBERGE = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1000, 10, -2.7f, 4, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(Items.NETHERITE_SCRAP), new AttributeContainer(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE));
   // public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons DECREPIT_SCYTHE = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1000, 10, -2.6f, 4, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(Items.NETHERITE_SCRAP));          AttributeContainer(AttributeRegistry.COOLDOWN_REDUCTION, .15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons ARCANE_FLAMBERGE = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1000, 8, -2f, 6, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of((ItemLike) ItemRegistry.MITHRIL_SCRAP), new AttributeContainer(AttributeRegistry.SPELL_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons ARCANE_REAPER = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(2031, 9, -2.5f, 30, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of((ItemLike) ItemRegistry.MITHRIL_SCRAP), new AttributeContainer(AttributeRegistry.SPELL_POWER, .08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons LONGSWORD = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1500, 9, -2.8f, 10, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(Items.NETHERITE_SCRAP), new AttributeContainer(Attributes.ENTITY_INTERACTION_RANGE, 1, AttributeModifier.Operation.ADD_VALUE));
    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons PYRIUM_LONGSWORD = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1500, 8, -2.6f, 20, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of((ItemLike) ItemRegistry.PYRIUM_INGOT), new AttributeContainer(Attributes.ENTITY_INTERACTION_RANGE, 1, AttributeModifier.Operation.ADD_VALUE));
    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons MITHRIL_LONGSWORD = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1500, 7, -2.4f, 30, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of((ItemLike) ItemRegistry.MITHRIL_SCRAP), new AttributeContainer(Attributes.ENTITY_INTERACTION_RANGE, 1, AttributeModifier.Operation.ADD_VALUE));

    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons SPEAR = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1500, 13, -3.2f, 10, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(Items.NETHERITE_SCRAP), new AttributeContainer(Attributes.ENTITY_INTERACTION_RANGE, 2, AttributeModifier.Operation.ADD_VALUE));
    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons PYRIUM_SPEAR = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1500, 9, -2.8f, 20, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of((ItemLike) ItemRegistry.PYRIUM_INGOT), new AttributeContainer(Attributes.ENTITY_INTERACTION_RANGE, 2, AttributeModifier.Operation.ADD_VALUE));
    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons MITHRIL_SPEAR = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1500, 5, -2.2f, 30, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of((ItemLike) ItemRegistry.MITHRIL_SCRAP), new AttributeContainer(Attributes.ENTITY_INTERACTION_RANGE, 2, AttributeModifier.Operation.ADD_VALUE));

    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons GREATSWORD = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1500, 15, -3.4f, 10, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(Items.NETHERITE_SCRAP), new AttributeContainer(Attributes.ENTITY_INTERACTION_RANGE, 1, AttributeModifier.Operation.ADD_VALUE));
    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons PYRIUM_GREATSWORD = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1500, 12, -3.2f, 20, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of((ItemLike) ItemRegistry.PYRIUM_INGOT), new AttributeContainer(Attributes.ENTITY_INTERACTION_RANGE, 1, AttributeModifier.Operation.ADD_VALUE));
    public static net.funnydude.sunsetarmory.item.weapons.SunsetWeapons MITHRIL_GREATSWORD = new net.funnydude.sunsetarmory.item.weapons.SunsetWeapons(1500, 10, -3f, 30, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of((ItemLike) ItemRegistry.MITHRIL_SCRAP), new AttributeContainer(Attributes.ENTITY_INTERACTION_RANGE, 1, AttributeModifier.Operation.ADD_VALUE));

    int uses;
    float damage;
    float speed;
    int enchantmentValue;
    TagKey<Block> incorrectBlocksForDrops;
    Supplier<Ingredient> repairIngredient;
    AttributeContainer[] attributes;

    public SunsetWeapons(int uses, float damage, float speed, int enchantmentValue, TagKey<Block> incorrectBlocksForDrops, Supplier<Ingredient> repairIngredient, AttributeContainer... attributes) {
        this.uses = uses;
        this.damage = damage;
        this.speed = speed;
        this.enchantmentValue = enchantmentValue;
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.repairIngredient = repairIngredient;
        this.attributes = attributes;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public AttributeContainer[] getAdditionalAttributes() {
        return this.attributes;
    }
}
