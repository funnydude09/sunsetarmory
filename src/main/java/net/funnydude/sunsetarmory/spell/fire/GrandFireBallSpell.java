package net.funnydude.sunsetarmory.spell.fire;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.spells.fireball.MagicFireball;
import io.redspace.ironsspellbooks.network.casting.OnCastStartedPacket;
import io.redspace.ironsspellbooks.network.casting.UpdateCastingStatePacket;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.registries.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.List;
@AutoSpellConfig
public class GrandFireBallSpell extends AbstractSpell {

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage",20),
                Component.translatable("ui.irons_spellbooks.radius",100)
        );
    }

    private float hp;

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.FIRE_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(360)
            .build();

    public GrandFireBallSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 85;
        this.baseManaCost = 250;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData){
            Vec3 origin = entity.getEyePosition();
            MagicFireball fireball = new MagicFireball(level, entity);
            fireball.setDamage(20);
            fireball.setExplosionRadius(100);
            fireball.setPos(origin.add(entity.getForward()).subtract(0, fireball.getBbHeight() / 2, 0));
            fireball.shoot(entity.getLookAngle());
            level.addFreshEntity(fireball);
            super.onCast(level, spellLevel, entity, castSource, playerMagicData);

    }

    @Override
    public void onServerCastTick(Level level, int spellLevel, LivingEntity entity, @Nullable MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(ModEffects.CINDEROUS_CHARGE_UP,3,0,false,false,true));
        super.onServerCastTick(level, spellLevel, entity, playerMagicData);
    }

    @Override
    public boolean canBeInterrupted(@Nullable Player player) {
        if(player.getHealth()<=hp-(player.getMaxHealth()*0.25)){
            return true;
        }
        else{
        return false;
            }
    }
    @Override
    public ResourceLocation getSpellResource() {
        return SunsetArmory.id("grand_fireball_spell");
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        return mob.getHealth() >= 0.5 * mob.getMaxHealth();
    }
}
