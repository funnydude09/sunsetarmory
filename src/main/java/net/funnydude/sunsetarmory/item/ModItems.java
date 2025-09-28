package net.funnydude.sunsetarmory.item;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
  public static final DeferredRegister.Items ITEMS =
          DeferredRegister.createItems(SunsetArmory.MODID);

  public static final DeferredItem<Item> TESTITEM = ITEMS.register("testitem",
          () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> JOHN = ITEMS.register("john_item",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHAINMAIL = ITEMS.register("arcane_chainmail",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<ArmorItem> KNIGHT_HELMET = ITEMS.register("netherite_knight_helmet",
            () -> new ArmorItem(ModArmorMaterials.KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<ArmorItem> KNIGHT_CHESTPLATE = ITEMS.register("netherite_knight_chestplate",
            () -> new ArmorItem(ModArmorMaterials.KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<ArmorItem> KNIGHT_LEGGINGS = ITEMS.register("netherite_knight_leggings",
            () -> new ArmorItem(ModArmorMaterials.KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<ArmorItem> KNIGHT_BOOTS = ITEMS.register("netherite_knight_boots",
            () -> new ArmorItem(ModArmorMaterials.KNIGHT_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

  public static void register(IEventBus eventBus) {
      ITEMS.register(eventBus);
  }
}
