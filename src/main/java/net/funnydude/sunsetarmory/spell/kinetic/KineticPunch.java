package net.funnydude.sunsetarmory.spell.kinetic;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.registries.ModSchools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class KineticPunch extends AbstractSpell {

    public KineticPunch(){
        this.baseSpellPower = 5;
        this.baseManaCost = 40;
        this.castTime = 0;
        this.manaCostPerLevel = 50;
    }

    private DefaultConfig defaultConfig = new DefaultConfig()
            .setSchoolResource(ModSchools.KINETIC_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(60)
            .setMinRarity(SpellRarity.RARE)
            .build();

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public ResourceLocation getSpellResource() {
        return SunsetArmory.id("kinetic_punch");
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
