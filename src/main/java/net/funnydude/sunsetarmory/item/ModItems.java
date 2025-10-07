package net.funnydude.sunsetarmory.item;

import io.redspace.ironsspellbooks.render.CinderousRarity;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.item.armor.*;
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
    public static final DeferredItem<ArmorItem> SUNSET_HELMET;
    public static final DeferredItem<ArmorItem> SUNSET_CHESTPLATE;
    public static final DeferredItem<ArmorItem> SUNSET_LEGGINGS;
    public static final DeferredItem<ArmorItem> SUNSET_BOOTS;
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
      KNIGHT_LEGGINGS = ITEMS.register("netherite_knight_leggings", () -> new NetheriteKnightArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
      KNIGHT_BOOTS = ITEMS.register("netherite_knight_boots", () -> new NetheriteKnightArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(37))));

      PYRIUM_KNIGHT_HELMET = ITEMS.register("pyrium_knight_helmet", () -> new PyriumKnightArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(34))));
      PYRIUM_KNIGHT_CHESTPLATE = ITEMS.register("pyrium_knight_chestplate", () -> new PyriumKnightArmorItem(ArmorItem.Type.CHESTPLATE,ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(34))));
      PYRIUM_KNIGHT_LEGGINGS = ITEMS.register("pyrium_knight_leggings", () -> new PyriumKnightArmorItem(ArmorItem.Type.LEGGINGS,ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(34))));
      PYRIUM_KNIGHT_BOOTS = ITEMS.register("pyrium_knight_boots", () -> new PyriumKnightArmorItem(ArmorItem.Type.LEGGINGS,ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(34))));

      MITHRIL_KNIGHT_HELMET = ITEMS.register("mithril_knight_helmet", () -> new MithrilKnightArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(28))));
      MITHRIL_KNIGHT_CHESTPLATE = ITEMS.register("mithril_knight_chestplate", () -> new MithrilKnightArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(28))));
      MITHRIL_KNIGHT_LEGGINGS = ITEMS.register("mithril_knight_leggings", () -> new MithrilKnightArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(28))));
      MITHRIL_KNIGHT_BOOTS = ITEMS.register("mithril_knight_boots", () -> new MithrilKnightArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(28))));

      PALADIN_HELMET = ITEMS.register("netherite_paladin_helmet", () -> new NetheritePaladinArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(37))));
      PALADIN_CHESTPLATE = ITEMS.register("netherite_paladin_chestplate", () -> new NetheritePaladinArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
      PALADIN_LEGGINGS = ITEMS.register("netherite_paladin_leggings", () -> new NetheritePaladinArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
      PALADIN_BOOTS = ITEMS.register("netherite_paladin_boots", () -> new NetheritePaladinArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(37))));

      PYRIUM_PALADIN_HELMET = ITEMS.register("pyrium_paladin_helmet", () -> new PyriumPaladinArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(34))));
      PYRIUM_PALADIN_CHESTPLATE = ITEMS.register("pyrium_paladin_chestplate", () -> new PyriumPaladinArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(34))));
      PYRIUM_PALADIN_LEGGINGS = ITEMS.register("pyrium_paladin_leggings", () -> new PyriumPaladinArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(34))));
      PYRIUM_PALADIN_BOOTS = ITEMS.register("pyrium_paladin_boots", () -> new PyriumPaladinArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(34))));

      MITHRIL_PALADIN_HELMET = ITEMS.register("mithril_paladin_helmet", () -> new MithrilPaladinArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(28))));
      MITHRIL_PALADIN_CHESTPLATE = ITEMS.register("mithril_paladin_chestplate", () -> new MithrilPaladinArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(28))));
      MITHRIL_PALADIN_LEGGINGS = ITEMS.register("mithril_paladin_leggings", () -> new MithrilPaladinArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(28))));
      MITHRIL_PALADIN_BOOTS = ITEMS.register("mithril_paladin_boots", () -> new MithrilPaladinArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(28))));

      SUNSET_HELMET = ITEMS.register("netherite_sunset_helmet", () -> new NetheriteSunsetArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(37))));
      SUNSET_CHESTPLATE = ITEMS.register("netherite_sunset_chestplate", () -> new NetheriteSunsetArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
      SUNSET_LEGGINGS = ITEMS.register("netherite_sunset_leggings", () -> new NetheriteSunsetArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
      SUNSET_BOOTS = ITEMS.register("netherite_sunset_boots", () -> new NetheriteSunsetArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    }
}
