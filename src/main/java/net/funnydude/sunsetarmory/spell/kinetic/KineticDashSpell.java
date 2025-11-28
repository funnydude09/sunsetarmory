package net.funnydude.sunsetarmory.spell.kinetic;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.capabilities.magic.ImpulseCastData;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.registries.ModSchools;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

@AutoSpellConfig
public class KineticDashSpell extends AbstractSpell {

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.strength", String.format("%s%%", (int) ((15 + getSpellPower(spellLevel, caster)) / 12f * 100 / 2.08f))));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(ModSchools.KINETIC_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(30)
            .build();

    @Override
    public ResourceLocation getSpellResource() {
        return SunsetArmory.id("kinetic_dash_spell");
    }

    public KineticDashSpell() {
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 5;
        this.baseManaCost = 40;
        this.manaCostPerLevel = 30;
        this.castTime = 0;
    }

    @Override
    public void onClientCast(Level level, int spellLevel, LivingEntity entity, ICastData castData) {
        if (castData instanceof ImpulseCastData data) {
            entity.hasImpulse = data.hasImpulse;
            entity.setDeltaMovement(entity.getDeltaMovement().add(data.x, data.y, data.z));
        }
        super.onClientCast(level, spellLevel, entity, castData);
    }

    @Override
    public ICastDataSerializable getEmptyCastData() {
        return new ImpulseCastData();
    }

    @Override
    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return spellLevel;
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!playerMagicData.getPlayerRecasts().hasRecastForSpell(getSpellId())) {
            playerMagicData.getPlayerRecasts().addRecast(new RecastInstance(getSpellId(), spellLevel, getRecastCount(spellLevel, entity), 800, castSource, null), playerMagicData);
        }
        ((ServerLevel)world).sendParticles(ParticleTypes.GUST,
                entity.getX(),entity.getY(),entity.getZ(),1,0,-0.5,0,0.025);
        entity.hasImpulse = true;
        float multiplier = (15 + getSpellPower(spellLevel, entity)) / 12f;

        Vec3 forward = entity.getLookAngle();
        var vec = forward.multiply(1.5, 1, 1.5).normalize().scale(multiplier);
        playerMagicData.setAdditionalCastData(new ImpulseCastData((float) vec.x, (float) 0, (float) vec.z, true));

        entity.setDeltaMovement(new Vec3(
                Mth.lerp(1, entity.getDeltaMovement().x, vec.x),
                entity.getDeltaMovement().y,
                Mth.lerp(1, entity.getDeltaMovement().z, vec.z)
        ));
        entity.invulnerableTime = 5;

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }
}