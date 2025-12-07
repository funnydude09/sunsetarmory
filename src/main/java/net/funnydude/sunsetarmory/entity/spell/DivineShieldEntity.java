package net.funnydude.sunsetarmory.entity.spell;


import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity;
import io.redspace.ironsspellbooks.entity.spells.ShieldPart;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import net.neoforged.neoforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;


public class DivineShieldEntity extends AbstractShieldEntity {
//todo: bro check echoing strikes and learn how to track the entity and use that to fix this(hopefully)
    public DivineShieldEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void createShield() {

    }

    @Override
    public void takeDamage(DamageSource source, float amount, @Nullable Vec3 location) {

    }

    @Override
    public PartEntity<?>[] getParts() {
        return new PartEntity[0];
    }
}
