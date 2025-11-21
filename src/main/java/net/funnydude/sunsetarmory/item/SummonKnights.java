package net.funnydude.sunsetarmory.item;

import io.redspace.ironsspellbooks.api.util.Utils;
import net.funnydude.sunsetarmory.entity.wizards.knight.KnightEntity;
import net.funnydude.sunsetarmory.entity.wizards.paladin.PaladinEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SummonKnights extends Item {
    int i;

    public double getRandomArbitrary(double min,double max) {
        return Math.random() * (max - min) + min;
    }

    public SummonKnights(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level level, Entity player, int slotId, boolean isSelected) {
        if(player instanceof Player && itemstack.isDamageableItem()){
            itemstack.hurtAndBreak(-1,(Player)player,((Player) player).getEquipmentSlotForItem(itemstack));
        }
        super.inventoryTick(itemstack, level, player, slotId, isSelected);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GOAT_HORN_PLAY, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            for(i=0; i<=2; i++) {
                KnightEntity knightEntity = new KnightEntity(level);
                double randRot = getRandomArbitrary(-Math.PI/2,Math.PI/2);
                Vec3 spawn = Utils.moveToRelativeGroundLevel(level, player.getEyePosition().add(new Vec3(5 * Mth.cos((float) randRot), 0, 5 * Mth.sin((float) randRot))), 10);
                knightEntity.setPos(spawn);
                level.addFreshEntity(knightEntity);
            }
            PaladinEntity paladinEntity = new PaladinEntity(level);
            double randRot = getRandomArbitrary(-Math.PI/2,Math.PI/2);
            Vec3 spawn = Utils.moveToRelativeGroundLevel(level, player.getEyePosition().add(new Vec3(5 * Mth.cos((float) randRot), 0, 5 * Mth.sin((float) randRot))), 10);
            paladinEntity.setPos(spawn);
            level.addFreshEntity(paladinEntity);
        }
        itemstack.hurtAndBreak(itemstack.getMaxDamage()-1,player,player.getEquipmentSlotForItem(itemstack));
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}

