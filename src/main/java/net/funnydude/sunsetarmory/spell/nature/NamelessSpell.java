package net.funnydude.sunsetarmory.spell.nature;

import com.mojang.authlib.minecraft.TelemetrySession;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.entity.spells.root.RootEntity;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;

@AutoSpellConfig
public class NamelessSpell extends AbstractSpell {

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setCooldownSeconds(60)
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(5)
            .build();

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        return Utils.preCastTargetHelper(level, entity, playerMagicData, this, 32, .35f);
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData castTargetingData) {
            LivingEntity target = castTargetingData.getTarget((ServerLevel) level);
            boolean canSee = CuriosApi.getCuriosHelper().findEquippedCurio(ItemRegistry.INVISIBILITY_RING.get(), entity).isPresent();
            if(target !=null ){
                Vec3 subtract = new Vec3(target.getForward().x, 0, target.getForward().z).scale(3);
                Utils.handleSpellTeleport(this,entity,target.position().add(subtract));
                entity.lookAt(EntityAnchorArgument.Anchor.EYES, target.getEyePosition());
                Vec3 location1 = Utils.raycastForBlock(level, entity.getEyePosition().add(entity.getForward()), entity.getEyePosition().add(entity.getForward().multiply(2, 0, 2)), ClipContext.Fluid.NONE).getLocation();
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                       //Particles ((ServerLevel)level).sendParticles(ParticleTypes.EXPLOSION_EMITTER,entity.getX(),entity.getY(),entity.getZ(),1,0,-0.5,0,0.025);
                        var entities = level.getEntities(entity, AABB.ofSize(location1, 5,  2, 5));
                        for (Entity targetEntity : entities) {
                            if (target.hasEffect(MobEffectRegistry.TRUE_INVISIBILITY) && !canSee) {
                                if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, location1.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                                    Vec3 location = target.position();
                                    RootEntity rootEntity = new RootEntity(level, entity);
                                    rootEntity.setDuration(2 * 20);
                                    rootEntity.setTarget(target);
                                    rootEntity.moveTo(location);
                                    level.addFreshEntity(rootEntity);
                                    target.stopRiding();
                                    target.startRiding(rootEntity, true);
                                }
                            }
                        }
                        if(!target.hasEffect(MobEffectRegistry.TRUE_INVISIBILITY) || canSee) {
                            target.addEffect(new MobEffectInstance(MobEffects.POISON, 4000, 1), entity);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public ResourceLocation getSpellResource() {
        return SunsetArmory.id("nameless_spell");
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
