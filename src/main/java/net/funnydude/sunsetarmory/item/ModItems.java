package net.funnydude.sunsetarmory.item;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SunsetArmory.MODID);

  public static final DeferredItem<Item> TESTITEM = ITEMS.register("testitem",
          () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> JOHN = ITEMS.register("john_item",
            () -> new Item(new Item.Properties()));

  public static void register(IEventBus eventBus) {
      ITEMS.register(eventBus);
  }
}
