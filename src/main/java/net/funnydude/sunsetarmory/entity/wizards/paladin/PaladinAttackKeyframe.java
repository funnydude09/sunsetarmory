package net.funnydude.sunsetarmory.entity.wizards.paladin;

import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackKeyframe;
import net.minecraft.world.phys.Vec3;

public class PaladinAttackKeyframe extends AttackKeyframe {
    public record SwingData(boolean vertical, boolean mirrored) {
    }

    final SwingData swingData;

    public PaladinAttackKeyframe(int timeStamp, Vec3 lungeVector, SwingData swingData) {
        this(timeStamp, lungeVector, Vec3.ZERO, swingData);
    }

    public PaladinAttackKeyframe(int timeStamp, Vec3 lungeVector, Vec3 extraKnockback, SwingData swingData) {
        super(timeStamp, lungeVector, extraKnockback);
        this.swingData = swingData;
    }
}
