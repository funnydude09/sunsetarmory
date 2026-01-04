package net.funnydude.sunsetarmory.item.custom;

import net.funnydude.sunsetarmory.registries.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LoveTrain extends Item {

    public LoveTrain(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand Hand) {
        ItemStack itemInHand = player.getItemInHand(Hand);
        itemInHand.consume(1, player);
        player.addEffect(new MobEffectInstance(ModEffects.LOVE_TRAIN,2000,0));
        return InteractionResultHolder.sidedSuccess(itemInHand, level.isClientSide());
    }
}
