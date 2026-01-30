package net.funnydude.sunsetarmory.item.custom;

import net.funnydude.sunsetarmory.entity.wizards.living_armor_stand.LivingArmorStandEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class LivingArmorStand extends Item {
    public LivingArmorStand(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        var level = context.getLevel();
        var blockPos = context.getClickedPos().getCenter();
        var item = context.getItemInHand();
        LivingArmorStandEntity livingArmorStandEntity = new LivingArmorStandEntity(level);
        livingArmorStandEntity.setPos(blockPos.x,blockPos.y+0.5,blockPos.z);
        livingArmorStandEntity.setOwner(player);
        level.addFreshEntity(livingArmorStandEntity);
        item.consume(1,player);
        return super.useOn(context);
    }
}
