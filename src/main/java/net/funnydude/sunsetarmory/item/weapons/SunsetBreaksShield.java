package net.funnydude.sunsetarmory.item.weapons;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;


public class SunsetBreaksShield extends TieredItem {

    public SunsetBreaksShield(Tier p_40521_, Item.Properties p_40524_) {
        super(p_40521_, p_40524_);
    }

    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (playerHasShieldUseIntent(context)) {
            return InteractionResult.PASS;
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private static boolean playerHasShieldUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        return context.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
    }

}
