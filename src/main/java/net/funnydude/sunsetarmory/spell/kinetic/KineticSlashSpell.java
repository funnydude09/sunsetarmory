package net.funnydude.sunsetarmory.spell.kinetic;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.spells.blood_slash.BloodSlashProjectile;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.entity.spell.KineticSlash;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Projectile;
import net.funnydude.sunsetarmory.spell.ModSchools;
import net.neoforged.fml.common.Mod;

public class KineticSlashSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(SunsetArmory.MODID, "kinetic_slash");

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(ModSchools.KINETIC_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(90)
            .build();

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        KineticSlash kineticSlash = new KineticSlash(level,entity);
        kineticSlash.setPos(entity.position().add(0.0F, entity.getEyeHeight() - kineticSlash.getBoundingBox().getYsize() * 0.5F, (double)0.0F));
        kineticSlash.shootFromRotation(entity, entity.getXRot(), entity.getYHeadRot(), 0.0F, kineticSlash.getSpeed(), 1.0F);
        kineticSlash.setDamage(this.getDamage(spellLevel, entity));
        level.addFreshEntity(kineticSlash);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        return (float)((double)this.getSpellPower(spellLevel, caster) * 0.77 + (double)this.getWeaponDamage(caster));
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

}
