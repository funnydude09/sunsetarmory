package net.funnydude.sunsetarmory.effect;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.funnydude.sunsetarmory.entity.spell.HalfSwordStance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class HalfSwordStanceEffect extends MagicMobEffect implements ISyncedMobEffect {

    public HalfSwordStanceEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void onEffectRemoved(LivingEntity entity, int pAmplifier) {
        super.onEffectRemoved(entity, pAmplifier);
        Level level = entity.level();
        var playerMagicData = MagicData.getPlayerMagicData(((ServerPlayer)entity));
        float weaponDamage = Utils.getWeaponDamage(entity);
        var damage = playerMagicData.getSyncedData().getHeartstopAccumulatedDamage();
        Vec3 spawnPos = entity.position().add(entity.getForward().scale(3));
        HalfSwordStance halfSwordStanceEntity = new HalfSwordStance(level,entity);
        halfSwordStanceEntity.setPos(spawnPos);
        halfSwordStanceEntity.setDamage(damage+weaponDamage);
        halfSwordStanceEntity.setRadius(3);
        level.addFreshEntity(halfSwordStanceEntity);
        playerMagicData.getSyncedData().setHeartstopAccumulatedDamage(0);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
