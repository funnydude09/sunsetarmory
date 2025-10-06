package net.funnydude.sunsetarmory.item;

import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.item.armor.NetheriteKnightArmorItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class ModItems {
  public static final DeferredRegister.Items ITEMS =
          DeferredRegister.createItems(SunsetArmory.MODID);;

  public static final DeferredItem<Item> TESTITEM;
  public static final DeferredItem<Item> JOHN;
  public static final DeferredItem<Item> MITHRIL_UPGRADE_TEMPLATE;
  public static final DeferredItem<Item> CHAINMAIL;
  public static final DeferredItem<ArmorItem> KNIGHT_HELMET;
  public static final DeferredItem<ArmorItem> KNIGHT_CHESTPLATE;
  public static final DeferredItem<ArmorItem> KNIGHT_LEGGINGS;
  public static final DeferredItem<ArmorItem> KNIGHT_BOOTS;
    public static final DeferredItem<ArmorItem> PYRIUM_KNIGHT_HELMET;
    public static final DeferredItem<ArmorItem> PYRIUM_KNIGHT_CHESTPLATE;
    public static final DeferredItem<ArmorItem> PYRIUM_KNIGHT_LEGGINGS;
    public static final DeferredItem<ArmorItem> PYRIUM_KNIGHT_BOOTS;
    public static final DeferredItem<ArmorItem> MITHRIL_KNIGHT_HELMET;
    public static final DeferredItem<ArmorItem> MITHRIL_KNIGHT_CHESTPLATE;
    public static final DeferredItem<ArmorItem> MITHRIL_KNIGHT_LEGGINGS;
    public static final DeferredItem<ArmorItem> MITHRIL_KNIGHT_BOOTS;
    public static final DeferredItem<ArmorItem> PALADIN_HELMET;
    public static final DeferredItem<ArmorItem> PALADIN_CHESTPLATE;
    public static final DeferredItem<ArmorItem> PALADIN_LEGGINGS;
    public static final DeferredItem<ArmorItem> PALADIN_BOOTS;
    public static final DeferredItem<ArmorItem> PYRIUM_PALADIN_HELMET;
    public static final DeferredItem<ArmorItem> PYRIUM_PALADIN_CHESTPLATE;
    public static final DeferredItem<ArmorItem> PYRIUM_PALADIN_LEGGINGS;
    public static final DeferredItem<ArmorItem> PYRIUM_PALADIN_BOOTS;
    public static final DeferredItem<ArmorItem> MITHRIL_PALADIN_HELMET;
    public static final DeferredItem<ArmorItem> MITHRIL_PALADIN_CHESTPLATE;
    public static final DeferredItem<ArmorItem> MITHRIL_PALADIN_LEGGINGS;
    public static final DeferredItem<ArmorItem> MITHRIL_PALADIN_BOOTS;

  public static Collection<DeferredHolder<Item, ? extends Item>> getArmoryItems() {
        return ITEMS.getEntries();
    }

  public static void register(IEventBus eventBus) {
      ITEMS.register(eventBus);
  }
    static {
      TESTITEM = ITEMS.register("testitem", () -> new Item(new Item.Properties()));
      JOHN = ITEMS.register("john_item", () -> new Item(new Item.Properties()));

      CHAINMAIL = ITEMS.register("arcane_chainmail", () -> new Item(new Item.Properties()));
      MITHRIL_UPGRADE_TEMPLATE= ITEMS.register("mithril_upgrade_template", () -> new Item(new Item.Properties()));

      KNIGHT_HELMET = ITEMS.register("netherite_knight_helmet", () -> new NetheriteKnightArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(37))));
      KNIGHT_CHESTPLATE = ITEMS.register("netherite_knight_chestplate", () -> new NetheriteKnightArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
      KNIGHT_LEGGINGS = ITEMS.register("netherite_knight_leggings", () -> new NetheriteKnightArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
      KNIGHT_BOOTS = ITEMS.register("netherite_knight_boots", () -> new NetheriteKnightArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.BOOTS.getDurability(37))));

      PYRIUM_KNIGHT_HELMET = ITEMS.register("pyrium_knight_helmet", () -> new ArmorItem(ModArmorMaterials.PYRIUM_KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(34))));
      PYRIUM_KNIGHT_CHESTPLATE = ITEMS.register("pyrium_knight_chestplate", () -> new ArmorItem(ModArmorMaterials.PYRIUM_KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(34))));
      PYRIUM_KNIGHT_LEGGINGS = ITEMS.register("pyrium_knight_leggings", () -> new ArmorItem(ModArmorMaterials.PYRIUM_KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(34))));
      PYRIUM_KNIGHT_BOOTS = ITEMS.register("pyrium_knight_boots", () -> new ArmorItem(ModArmorMaterials.PYRIUM_KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(34))));

      MITHRIL_KNIGHT_HELMET = ITEMS.register("mithril_knight_helmet", () -> new ArmorItem(ModArmorMaterials.MITHRIL_KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(28))));
      MITHRIL_KNIGHT_CHESTPLATE = ITEMS.register("mithril_knight_chestplate", () -> new ArmorItem(ModArmorMaterials.MITHRIL_KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(28))));
      MITHRIL_KNIGHT_LEGGINGS = ITEMS.register("mithril_knight_leggings", () -> new ArmorItem(ModArmorMaterials.MITHRIL_KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(28))));
      MITHRIL_KNIGHT_BOOTS = ITEMS.register("mithril_knight_boots", () -> new ArmorItem(ModArmorMaterials.MITHRIL_KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(28))));

      PALADIN_HELMET = ITEMS.register("netherite_paladin_helmet", () -> new ArmorItem(ModArmorMaterials.PALADIN_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
      PALADIN_CHESTPLATE = ITEMS.register("netherite_paladin_chestplate", () -> new ArmorItem(ModArmorMaterials.PALADIN_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
      PALADIN_LEGGINGS = ITEMS.register("netherite_paladin_leggings", () -> new ArmorItem(ModArmorMaterials.PALADIN_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
      PALADIN_BOOTS = ITEMS.register("netherite_paladin_boots", () -> new ArmorItem(ModArmorMaterials.PALADIN_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

      PYRIUM_PALADIN_HELMET = ITEMS.register("pyrium_paladin_helmet", () -> new ArmorItem(ModArmorMaterials.PYRIUM_PALADIN_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(34))));
      PYRIUM_PALADIN_CHESTPLATE = ITEMS.register("pyrium_paladin_chestplate", () -> new ArmorItem(ModArmorMaterials.PYRIUM_PALADIN_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(34))));
      PYRIUM_PALADIN_LEGGINGS = ITEMS.register("pyrium_paladin_leggings", () -> new ArmorItem(ModArmorMaterials.PYRIUM_PALADIN_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(34))));
      PYRIUM_PALADIN_BOOTS = ITEMS.register("pyrium_paladin_boots", () -> new ArmorItem(ModArmorMaterials.PYRIUM_PALADIN_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(34))));

      MITHRIL_PALADIN_HELMET = ITEMS.register("mithril_paladin_helmet", () -> new ArmorItem(ModArmorMaterials.MITHRIL_PALADIN_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(28))));
      MITHRIL_PALADIN_CHESTPLATE = ITEMS.register("mithril_paladin_chestplate", () -> new ArmorItem(ModArmorMaterials.MITHRIL_PALADIN_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(28))));
      MITHRIL_PALADIN_LEGGINGS = ITEMS.register("mithril_paladin_leggings", () -> new ArmorItem(ModArmorMaterials.MITHRIL_PALADIN_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(28))));
      MITHRIL_PALADIN_BOOTS = ITEMS.register("mithril_paladin_boots", () -> new ArmorItem(ModArmorMaterials.MITHRIL_PALADIN_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(28))));
    }
}
