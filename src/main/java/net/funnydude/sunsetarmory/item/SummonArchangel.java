package net.funnydude.sunsetarmory.item;

import io.redspace.ironsspellbooks.api.util.Utils;
import net.funnydude.sunsetarmory.effect.ModEffects;
import net.funnydude.sunsetarmory.entity.wizards.archangel.ArchangelEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SummonArchangel extends Item {

    public SummonArchangel(Properties properties) {
        super(properties);
    }

    public double getRangedRandom(double min, double max) {
        return Math.random() * (max - min) + min;
    }
//level.getNearestEntity()[D4cLoveTrainSoon]
    @Override
    public void inventoryTick(ItemStack itemstack, Level level, Entity player, int slotId, boolean isSelected) {
        if(player instanceof Player && itemstack.isDamageableItem()){
            itemstack.hurtAndBreak(-1,(Player)player,((Player) player).getEquipmentSlotForItem(itemstack));
        }
        super.inventoryTick(itemstack, level, player, slotId, isSelected);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide&&!isDamaged(itemstack)) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GOAT_HORN_PLAY, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            ArchangelEntity archangelEntity = new ArchangelEntity(level);
            double randRot = getRangedRandom(-Math.PI,Math.PI);
            Vec3 spawn = Utils.moveToRelativeGroundLevel(level, player.getEyePosition().add(new Vec3(10 * Mth.cos((float) randRot), 0, 10 * Mth.sin((float) randRot))), 10);
            archangelEntity.setPos(spawn);
            level.addFreshEntity(archangelEntity);
            itemstack.hurtAndBreak(itemstack.getMaxDamage()-1,player,player.getEquipmentSlotForItem(itemstack));
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
