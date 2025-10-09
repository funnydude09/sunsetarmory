package net.funnydude.sunsetarmory.item;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.item.SimpleDescriptiveItem;
import io.redspace.ironsspellbooks.render.CinderousRarity;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.item.armor.*;
import net.funnydude.sunsetarmory.item.armor.geckolib.NpcNetheriteKnightArmorItem;
import net.funnydude.sunsetarmory.item.weapons.SunsetNonSword;
import net.funnydude.sunsetarmory.item.weapons.SunsetWeapons;
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
    public static final DeferredItem<Item> DECREPIT_SCRAP;
    public static final DeferredItem<Item> PYRIUM_SCRAP;

  public static final DeferredItem<ArmorItem> KNIGHT_HELMET;
  public static final DeferredItem<ArmorItem> KNIGHT_CHESTPLATE;
  public static final DeferredItem<ArmorItem> KNIGHT_LEGGINGS;
  public static final DeferredItem<ArmorItem> KNIGHT_BOOTS;
    public static final DeferredItem<ArmorItem> NPC_KNIGHT_HELMET;
    public static final DeferredItem<ArmorItem> NPC_KNIGHT_CHESTPLATE;
    public static final DeferredItem<ArmorItem> NPC_KNIGHT_LEGGINGS;
    public static final DeferredItem<ArmorItem> NPC_KNIGHT_BOOTS;
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
    public static final DeferredItem<ArmorItem> MITHRIL_SUNSET_HELMET;
    public static final DeferredItem<ArmorItem> MITHRIL_SUNSET_CHESTPLATE;
    public static final DeferredItem<ArmorItem> MITHRIL_SUNSET_LEGGINGS;
    public static final DeferredItem<ArmorItem> MITHRIL_SUNSET_BOOTS;
    public static final DeferredItem<ArmorItem> PYRIUM_SUNSET_HELMET;
    public static final DeferredItem<ArmorItem> PYRIUM_SUNSET_CHESTPLATE;
    public static final DeferredItem<ArmorItem> PYRIUM_SUNSET_LEGGINGS;
    public static final DeferredItem<ArmorItem> PYRIUM_SUNSET_BOOTS;
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

    public static final DeferredItem<ExtendedSwordItem> ARCANE_FLAMBERGE;
    public static final DeferredItem<ExtendedSwordItem> ARCANE_REAPER;

    public static final DeferredItem<ExtendedSwordItem> NETHERITE_LONGSWORD;
    public static final DeferredItem<ExtendedSwordItem> PYRIUM_LONGSWORD;
    public static final DeferredItem<ExtendedSwordItem> MITHRIL_LONGSWORD;

    public static final DeferredItem<SunsetNonSword> NETHERITE_SPEAR;
    public static final DeferredItem<SunsetNonSword> PYRIUM_SPEAR;
    public static final DeferredItem<SunsetNonSword> MITHRIL_SPEAR;

    public static final DeferredItem<SunsetNonSword> NETHERITE_GREATSWORD;
    public static final DeferredItem<SunsetNonSword> PYRIUM_GREATSWORD;
    public static final DeferredItem<SunsetNonSword> MITHRIL_GREATSWORD;

  public static Collection<DeferredHolder<Item, ? extends Item>> getArmoryItems() {
        return ITEMS.getEntries();
    }

  public static void register(IEventBus eventBus) {
      ITEMS.register(eventBus);
  }
    static {
      TESTITEM = ITEMS.register("testitem", () -> new Item(new Item.Properties()));
      JOHN = ITEMS.register("john_item", () -> new Item(new Item.Properties()));

      CHAINMAIL = ITEMS.register("arcane_chainmail", () -> new Item(new Item.Properties().fireResistant()));
      DECREPIT_SCRAP = ITEMS.register("keeper_scrap", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()));
        PYRIUM_SCRAP = ITEMS.register("pyrium_scrap", () -> new Item(new Item.Properties().rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant()));
      MITHRIL_UPGRADE_TEMPLATE= ITEMS.register("mithril_upgrade_template", () -> new SimpleDescriptiveItem(new Item.Properties().rarity(Rarity.RARE)));
      ARCANE_FLAMBERGE = ITEMS.register("arcane_flamberge", () -> new ExtendedSwordItem(SunsetWeapons.ARCANE_FLAMBERGE, ItemPropertiesHelper.equipment().rarity(Rarity.RARE).fireResistant().attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.ARCANE_FLAMBERGE))));
        NETHERITE_LONGSWORD = ITEMS.register("netherite_longsword", () -> new ExtendedSwordItem(SunsetWeapons.LONGSWORD, ItemPropertiesHelper.equipment().fireResistant().attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.LONGSWORD))));
        PYRIUM_LONGSWORD = ITEMS.register("pyrium_longsword", () -> new ExtendedSwordItem(SunsetWeapons.PYRIUM_LONGSWORD, ItemPropertiesHelper.equipment().rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.PYRIUM_LONGSWORD))));
        MITHRIL_LONGSWORD = ITEMS.register("mithril_longsword", () -> new ExtendedSwordItem(SunsetWeapons.MITHRIL_LONGSWORD, ItemPropertiesHelper.equipment().rarity(Rarity.RARE).attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.MITHRIL_LONGSWORD))));
        ARCANE_REAPER = ITEMS.register("arcane_reaper", () -> new MagicSwordItem(SunsetWeapons.ARCANE_REAPER, ItemPropertiesHelper.equipment().attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.ARCANE_REAPER)).rarity(Rarity.RARE).fireResistant(), SpellDataRegistryHolder.of(new SpellDataRegistryHolder(SpellRegistry.SHOCKWAVE_SPELL, 10))));
        NETHERITE_SPEAR = ITEMS.register("netherite_spear", () -> new SunsetNonSword(SunsetWeapons.SPEAR, ItemPropertiesHelper.equipment().fireResistant().attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.SPEAR))));
        PYRIUM_SPEAR = ITEMS.register("pyrium_spear", () -> new SunsetNonSword(SunsetWeapons.PYRIUM_SPEAR, ItemPropertiesHelper.equipment().rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.PYRIUM_SPEAR))));
        MITHRIL_SPEAR = ITEMS.register("mithril_spear", () -> new SunsetNonSword(SunsetWeapons.MITHRIL_SPEAR, ItemPropertiesHelper.equipment().rarity(Rarity.RARE).attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.MITHRIL_SPEAR))));
        NETHERITE_GREATSWORD = ITEMS.register("netherite_greatsword", () -> new SunsetNonSword(SunsetWeapons.GREATSWORD, ItemPropertiesHelper.equipment().fireResistant().attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.GREATSWORD))));
        PYRIUM_GREATSWORD = ITEMS.register("pyrium_greatsword", () -> new SunsetNonSword(SunsetWeapons.PYRIUM_GREATSWORD, ItemPropertiesHelper.equipment().rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.PYRIUM_GREATSWORD))));
        MITHRIL_GREATSWORD = ITEMS.register("mithril_greatsword", () -> new SunsetNonSword(SunsetWeapons.MITHRIL_GREATSWORD, ItemPropertiesHelper.equipment().rarity(Rarity.RARE).attributes(ExtendedSwordItem.createAttributes(SunsetWeapons.MITHRIL_GREATSWORD))));


        KNIGHT_HELMET = ITEMS.register("netherite_knight_helmet", () -> new NetheriteKnightArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(37))));
      KNIGHT_CHESTPLATE = ITEMS.register("netherite_knight_chestplate", () -> new NetheriteKnightArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
      KNIGHT_LEGGINGS = ITEMS.register("netherite_knight_leggings", () -> new NetheriteKnightArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
      KNIGHT_BOOTS = ITEMS.register("netherite_knight_boots", () -> new NetheriteKnightArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(37))));

        NPC_KNIGHT_HELMET = ITEMS.register("netherite_knight_helmet", () -> new NpcNetheriteKnightArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(37))));
        NPC_KNIGHT_CHESTPLATE = ITEMS.register("netherite_knight_chestplate", () -> new NpcNetheriteKnightArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
        NPC_KNIGHT_LEGGINGS = ITEMS.register("netherite_knight_leggings", () -> new NpcNetheriteKnightArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
        NPC_KNIGHT_BOOTS = ITEMS.register("netherite_knight_boots", () -> new NpcNetheriteKnightArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(37))));


        PYRIUM_KNIGHT_HELMET = ITEMS.register("pyrium_knight_helmet", () -> new PyriumKnightArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(34))));
      PYRIUM_KNIGHT_CHESTPLATE = ITEMS.register("pyrium_knight_chestplate", () -> new PyriumKnightArmorItem(ArmorItem.Type.CHESTPLATE,ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(34))));
      PYRIUM_KNIGHT_LEGGINGS = ITEMS.register("pyrium_knight_leggings", () -> new PyriumKnightArmorItem(ArmorItem.Type.LEGGINGS,ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(34))));
      PYRIUM_KNIGHT_BOOTS = ITEMS.register("pyrium_knight_boots", () -> new PyriumKnightArmorItem(ArmorItem.Type.LEGGINGS,ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(34))));

      MITHRIL_KNIGHT_HELMET = ITEMS.register("mithril_knight_helmet", () -> new MithrilKnightArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.HELMET.getDurability(28))));
      MITHRIL_KNIGHT_CHESTPLATE = ITEMS.register("mithril_knight_chestplate", () -> new MithrilKnightArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.CHESTPLATE.getDurability(28))));
      MITHRIL_KNIGHT_LEGGINGS = ITEMS.register("mithril_knight_leggings", () -> new MithrilKnightArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.LEGGINGS.getDurability(28))));
      MITHRIL_KNIGHT_BOOTS = ITEMS.register("mithril_knight_boots", () -> new MithrilKnightArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.BOOTS.getDurability(28))));

      PALADIN_HELMET = ITEMS.register("netherite_paladin_helmet", () -> new NetheritePaladinArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(37))));
      PALADIN_CHESTPLATE = ITEMS.register("netherite_paladin_chestplate", () -> new NetheritePaladinArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
      PALADIN_LEGGINGS = ITEMS.register("netherite_paladin_leggings", () -> new NetheritePaladinArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
      PALADIN_BOOTS = ITEMS.register("netherite_paladin_boots", () -> new NetheritePaladinArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(37))));

      PYRIUM_PALADIN_HELMET = ITEMS.register("pyrium_paladin_helmet", () -> new PyriumPaladinArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(34))));
      PYRIUM_PALADIN_CHESTPLATE = ITEMS.register("pyrium_paladin_chestplate", () -> new PyriumPaladinArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(34))));
      PYRIUM_PALADIN_LEGGINGS = ITEMS.register("pyrium_paladin_leggings", () -> new PyriumPaladinArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(34))));
      PYRIUM_PALADIN_BOOTS = ITEMS.register("pyrium_paladin_boots", () -> new PyriumPaladinArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(34))));

      MITHRIL_PALADIN_HELMET = ITEMS.register("mithril_paladin_helmet", () -> new MithrilPaladinArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.HELMET.getDurability(28))));
      MITHRIL_PALADIN_CHESTPLATE = ITEMS.register("mithril_paladin_chestplate", () -> new MithrilPaladinArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.CHESTPLATE.getDurability(28))));
      MITHRIL_PALADIN_LEGGINGS = ITEMS.register("mithril_paladin_leggings", () -> new MithrilPaladinArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.LEGGINGS.getDurability(28))));
      MITHRIL_PALADIN_BOOTS = ITEMS.register("mithril_paladin_boots", () -> new MithrilPaladinArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.BOOTS.getDurability(28))));

      SUNSET_HELMET = ITEMS.register("netherite_sunset_helmet", () -> new NetheriteSunsetArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).fireResistant().rarity(Rarity.UNCOMMON).durability(ArmorItem.Type.HELMET.getDurability(50))));
      SUNSET_CHESTPLATE = ITEMS.register("netherite_sunset_chestplate", () -> new NetheriteSunsetArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(50))));
      SUNSET_LEGGINGS = ITEMS.register("netherite_sunset_leggings", () -> new NetheriteSunsetArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(50))));
      SUNSET_BOOTS = ITEMS.register("netherite_sunset_boots", () -> new NetheriteSunsetArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(50))));

        PYRIUM_SUNSET_HELMET = ITEMS.register("pyrium_sunset_helmet", () -> new PyriumSunsetArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.HELMET.getDurability(40))));
        PYRIUM_SUNSET_CHESTPLATE = ITEMS.register("pyrium_sunset_chestplate", () -> new PyriumSunsetArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(40))));
        PYRIUM_SUNSET_LEGGINGS = ITEMS.register("pyrium_sunset_leggings", () -> new PyriumSunsetArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(40))));
        PYRIUM_SUNSET_BOOTS = ITEMS.register("pyrium_sunset_boots", () -> new PyriumSunsetArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).rarity(CinderousRarity.CINDEROUS_RARITY_PROXY.getValue()).fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(40))));

        MITHRIL_SUNSET_HELMET = ITEMS.register("mithril_sunset_helmet", () -> new MithrilSunsetArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.HELMET.getDurability(35))));
        MITHRIL_SUNSET_CHESTPLATE = ITEMS.register("mithril_sunset_chestplate", () -> new MithrilSunsetArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.CHESTPLATE.getDurability(35))));
        MITHRIL_SUNSET_LEGGINGS = ITEMS.register("mithril_sunset_leggings", () -> new MithrilSunsetArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.LEGGINGS.getDurability(35))));
        MITHRIL_SUNSET_BOOTS = ITEMS.register("mithril_sunset_boots", () -> new MithrilSunsetArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE).durability(ArmorItem.Type.BOOTS.getDurability(35))));

    }
}
