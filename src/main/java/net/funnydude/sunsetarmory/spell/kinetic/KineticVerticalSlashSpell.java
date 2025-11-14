package net.funnydude.sunsetarmory.spell.kinetic;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.spell.KineticSlash;
import net.funnydude.sunsetarmory.entity.spell.KineticVerticalSlash;
import net.funnydude.sunsetarmory.spell.ModSchools;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;
import java.util.List;

@AutoSpellConfig
public class KineticVerticalSlashSpell extends AbstractSpell {

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(ModSchools.KINETIC_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(90)
            .build();

    public KineticVerticalSlashSpell() {
        this.manaCostPerLevel = 50;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 10;
        this.baseManaCost = 35;
        this.castTime = 0;
    }

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster)),
                Component.translatable("ui.irons_spellbooks.blast_count", getRecastCount(spellLevel, caster))
        );
    }
    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!playerMagicData.getPlayerRecasts().hasRecastForSpell(getSpellId())) {
            playerMagicData.getPlayerRecasts().addRecast(new RecastInstance(getSpellId(), spellLevel, getRecastCount(spellLevel, entity), 80, castSource, null), playerMagicData);
        }
        KineticVerticalSlash kineticVerticalSlash = new KineticVerticalSlash(level,entity);
        kineticVerticalSlash.setPos(entity.position());
        kineticVerticalSlash.shootFromRotation(entity, entity.getXRot(), entity.getYHeadRot(), 0.0F, kineticVerticalSlash.getSpeed(), 1.0F);
        kineticVerticalSlash.setDamage(this.getDamage(spellLevel, entity));
        level.addFreshEntity(kineticVerticalSlash);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return (float)(this.getSpellPower(spellLevel, caster) * 0.77 + this.getAdditionalDamage(caster));
    }
    @Override
    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 2 + spellLevel;
    }

    private float getAdditionalDamage(LivingEntity entity) {
            float e = (float) (entity.getAttributeValue(Attributes.ATTACK_DAMAGE));
            float fistDamage = 1;
            if (e < fistDamage) {
                return 0;
            }
            return e;
        }


    private String getDamageText(int spellLevel, LivingEntity entity) {
        if (entity != null) {
            float weaponDamage = getAdditionalDamage(entity);
            String plus = "";
            if (weaponDamage > 0) {
                plus = String.format(" (+%s)", Utils.stringTruncation(weaponDamage, 1));
            }
            String damage = Utils.stringTruncation(getDamage(spellLevel, entity), 1);
            return damage + plus;
        }
        return "" + getSpellPower(spellLevel, entity);
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }
    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.SLASH_ANIMATION;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return SunsetArmory.id("kinetic_vertical_slash_spell");
    }
}
