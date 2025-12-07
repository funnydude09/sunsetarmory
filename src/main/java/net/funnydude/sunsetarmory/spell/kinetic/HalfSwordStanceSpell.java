package net.funnydude.sunsetarmory.spell.kinetic;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.registries.ModEffects;
import net.funnydude.sunsetarmory.registries.ModSchools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

@AutoSpellConfig
public class HalfSwordStanceSpell extends AbstractSpell {

    public HalfSwordStanceSpell(){
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 5;
        this.baseManaCost = 40;
        this.manaCostPerLevel = 30;
        this.castTime = 0;
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMaxLevel(5)
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(ModSchools.KINETIC_RESOURCE)
            .setCooldownSeconds(60)
            .build();

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(ModEffects.HALF_STANCE_SWORD_EFFECT,200));
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public ResourceLocation getSpellResource() {
        return SunsetArmory.id("half_stance_sword_spell");
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
