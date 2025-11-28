package net.funnydude.sunsetarmory.item;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.registries.ModItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final Holder<ArmorMaterial> KNIGHT_ARMOR_MATERIAL = register("knight",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
               attribute.put(ArmorItem.Type.BOOTS, 7);
                attribute.put(ArmorItem.Type.LEGGINGS, 11);
                attribute.put(ArmorItem.Type.CHESTPLATE, 13);
                attribute.put(ArmorItem.Type.HELMET, 8);
                attribute.put(ArmorItem.Type.BODY, 18);
            }), 12, 6f, 0.3f, () -> ModItems.CHAINMAIL.get());
    public static final Holder<ArmorMaterial> SUNSET_ARMOR_MATERIAL = register("sunset",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 8);
                attribute.put(ArmorItem.Type.LEGGINGS, 12);
                attribute.put(ArmorItem.Type.CHESTPLATE, 14);
                attribute.put(ArmorItem.Type.HELMET, 9);
                attribute.put(ArmorItem.Type.BODY, 18);
            }), 19, 4f, 0.4f, () -> ModItems.CHAINMAIL.get());
    public static final Holder<ArmorMaterial> PYRIUM_SUNSET_ARMOR_MATERIAL = register("pyrium_sunset",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 6);
                attribute.put(ArmorItem.Type.LEGGINGS, 7);
                attribute.put(ArmorItem.Type.CHESTPLATE, 12);
                attribute.put(ArmorItem.Type.HELMET, 6);
                attribute.put(ArmorItem.Type.BODY, 18);
            }), 21, 3f, 0.3f, () -> ItemRegistry.PYRIUM_INGOT.get());
    public static final Holder<ArmorMaterial> MITHRIL_SUNSET_ARMOR_MATERIAL = register("mithril_sunset",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 5);
                attribute.put(ArmorItem.Type.LEGGINGS, 8);
                attribute.put(ArmorItem.Type.CHESTPLATE, 9);
                attribute.put(ArmorItem.Type.HELMET, 5);
                attribute.put(ArmorItem.Type.BODY, 18);
            }), 35, 2f, 0.2f, () -> ItemRegistry.MITHRIL_INGOT.get());
    public static final Holder<ArmorMaterial> PYRIUM_KNIGHT_ARMOR_MATERIAL = register("pyrium_knight",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 5);
                attribute.put(ArmorItem.Type.LEGGINGS, 8);
                attribute.put(ArmorItem.Type.CHESTPLATE, 10);
                attribute.put(ArmorItem.Type.HELMET, 5);
                attribute.put(ArmorItem.Type.BODY, 11);
            }), 15, 3f, 0.2f, () -> ItemRegistry.PYRIUM_INGOT.get());
    public static final Holder<ArmorMaterial> MITHRIL_KNIGHT_ARMOR_MATERIAL = register("mithril_knight",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 4);
                attribute.put(ArmorItem.Type.LEGGINGS, 7);
                attribute.put(ArmorItem.Type.CHESTPLATE, 8);
                attribute.put(ArmorItem.Type.HELMET, 4);
                attribute.put(ArmorItem.Type.BODY, 9);
            }), 20, 2f, 0.1f, () -> ItemRegistry.MITHRIL_SCRAP.get());
    public static final Holder<ArmorMaterial> PALADIN_ARMOR_MATERIAL = register("paladin",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 8);
                attribute.put(ArmorItem.Type.LEGGINGS, 13);
                attribute.put(ArmorItem.Type.CHESTPLATE, 15);
                attribute.put(ArmorItem.Type.HELMET, 9);
                attribute.put(ArmorItem.Type.BODY, 21);
            }), 12, 6f, 0.3f, () -> ModItems.CHAINMAIL.get());
    public static final Holder<ArmorMaterial> PYRIUM_PALADIN_ARMOR_MATERIAL = register("pyrium_paladin",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 7);
                attribute.put(ArmorItem.Type.LEGGINGS, 10);
                attribute.put(ArmorItem.Type.CHESTPLATE, 12);
                attribute.put(ArmorItem.Type.HELMET, 8);
                attribute.put(ArmorItem.Type.BODY, 15);
            }), 15, 3f, 0.2f, () -> ItemRegistry.PYRIUM_INGOT.get());
    public static final Holder<ArmorMaterial> MITHRIL_PALADIN_ARMOR_MATERIAL = register("mithril_paladin",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 5);
                attribute.put(ArmorItem.Type.LEGGINGS, 8);
                attribute.put(ArmorItem.Type.CHESTPLATE, 9);
                attribute.put(ArmorItem.Type.HELMET, 6);
                attribute.put(ArmorItem.Type.BODY, 11);
            }), 20, 2f, 0.1f, () -> ItemRegistry.MITHRIL_SCRAP.get());


    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}