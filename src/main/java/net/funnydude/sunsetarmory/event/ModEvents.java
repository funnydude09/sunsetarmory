package net.funnydude.sunsetarmory.event;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
@EventBusSubscriber(modid = SunsetArmory.MODID, value = Dist.CLIENT)
public class ModEvents {

    @SubscribeEvent
    public static void OnBrewingRecipeRegister(RegisterBrewingRecipesEvent event){
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.TURTLE_MASTER, Items.REDSTONE_ORE, ModPotions.RAGE_POTION);
        builder.addMix(Potions.TURTLE_MASTER, Items.DEEPSLATE_REDSTONE_ORE, ModPotions.AMBROSIA_POTION);
        builder.addMix(Potions.AWKWARD, Items.WITHER_ROSE, ModPotions.WITHERING_POTION);
    }
}
