package net.funnydude.sunsetarmory.item.curios;

import net.funnydude.sunsetarmory.registries.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class EldritchCultistBanner extends BannerCuriosItem {
    public EldritchCultistBanner(Properties properties, String slotIdentifier) {
        super(properties, slotIdentifier);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        var entity = slotContext.entity();
        entity.addEffect(new MobEffectInstance(ModEffects.CURIOS_COOL_DOWN_EFFECT,1200));
        super.onUnequip(slotContext, newStack, stack);
    }
}
