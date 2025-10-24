package net.funnydude.sunsetarmory.spell.kinetic;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.spell.KineticSlash;
import net.funnydude.sunsetarmory.spell.ModSchools;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;
import java.util.List;


public class KineticVerticalSlashSpell extends AbstractSpell {
    public final String getSpellName= "kinetic_slash";
    private final ResourceLocation spellId = SunsetArmory.id("kinetic_vertical_slash");

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
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.blast_count", (int) (getRecastCount(spellLevel, caster)))
        );
    }
    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!playerMagicData.getPlayerRecasts().hasRecastForSpell(getSpellId())) {
            playerMagicData.getPlayerRecasts().addRecast(new RecastInstance(getSpellId(), spellLevel, getRecastCount(spellLevel, entity), 80, castSource, null), playerMagicData);
        }
        KineticSlash kineticVerticalSlash = new KineticSlash(level,entity);
        kineticVerticalSlash.setPos(entity.position().add(0.0F, entity.getEyeHeight() - kineticVerticalSlash.getBoundingBox().getYsize() * 0.5F, (double)0.0F));
        kineticVerticalSlash.shootFromRotation(entity, entity.getXRot(), entity.getYHeadRot(), 0.0F, kineticVerticalSlash.getSpeed(), 1.0F);
        kineticVerticalSlash.setDamage(this.getDamage(spellLevel, entity));
        level.addFreshEntity(kineticVerticalSlash);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return (float)((double)this.getSpellPower(spellLevel, caster) * 0.77 + (double)this.getWeaponDamage(caster));
    }
    @Override
    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 2 + spellLevel;
    }

    private float getWeaponDamage(LivingEntity caster) {
        float weaponDamage = Utils.getWeaponDamage(caster);
        return weaponDamage;
    }

    private String getDamageText(int spellLevel, LivingEntity caster) {
        if (caster != null) {
            float weaponDamage = Utils.getWeaponDamage(caster);
            String plus = "";
            if (weaponDamage > 0.0F) {
                plus = String.format(" (+%s)", Utils.stringTruncation((double)weaponDamage, 1));
            }

            String damage = Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 1);
            return damage + plus;
        } else {
            float var10000 = this.getSpellPower(spellLevel, caster);
            return "" + var10000;
        }
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
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
        return SpellAnimations.SELF_CAST_ANIMATION;
    }

}
