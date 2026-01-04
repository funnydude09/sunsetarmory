package net.funnydude.sunsetarmory.entity.spell;


import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.entity.spells.AbstractShieldEntity;
import io.redspace.ironsspellbooks.entity.spells.ShieldPart;
import net.funnydude.sunsetarmory.registries.ModEntities;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;


public class DivineShieldEntity extends AbstractShieldEntity {
    protected ShieldPart[] subEntities;
    protected final Vec3[] subPositions;
    protected final int LIFETIME;
    protected int width;
    protected int height;
    protected int age;
    private float lifetime = 20*10;

    private LivingEntity owner;

    public DivineShieldEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        width = 5;
        height = 5;
        subEntities = new ShieldPart[width * height];
        subPositions = new Vec3[width * height];
        this.setHealth(10);
        LIFETIME = 20 * 20;
        createShield();
    }

    public DivineShieldEntity(EntityType<?> pEntityType,Level pLevel,LivingEntity owner) {
        this(ModEntities.DIVINE_SHIELD_ENTITY.get(), pLevel);
        this.setOwner(owner);
    }

    public DivineShieldEntity(Level pLevel,LivingEntity owner) {
        this(ModEntities.DIVINE_SHIELD_ENTITY.get(), pLevel,owner);
    }

    @Override
    protected void createShield() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int i = x * height + y;
                subEntities[i] = new ShieldPart(this, "part" + (i + 1), 0.5F, 0.5F, true);
                subPositions[i] = new Vec3((x - width / 2f) * .5f + .25f, (y - height / 2f) * .5f, 0);//.xRot(getXRot()).yRot(getYRot());
            }
        }
    }

    @Override
    public void takeDamage(DamageSource source, float amount, @Nullable Vec3 location) {
        var entity = source.getEntity();
        var damageType = source.type();
        if(entity instanceof LivingEntity && damageType.equals(ISSDamageTypes.BLOOD_MAGIC) || damageType.equals(ISSDamageTypes.ELDRITCH_MAGIC)){
            entity.remove(RemovalReason.KILLED);
        }
        if (!this.isInvulnerableTo(source)) {
            this.setHealth(this.getHealth() - amount);
        }
    }

    @Override
    public void tick() {
        if(lifetime<tickCount || this.getHealth()<=0){
            this.discard();
        }
        else {
            if(owner !=null && lifetime>=tickCount) {
                for (int i = 0; i < subEntities.length; i++) {
                    var subEntity = subEntities[i];

                    Vec3 pos = subPositions[i].xRot(Mth.DEG_TO_RAD * -this.getXRot()).yRot(Mth.DEG_TO_RAD * -this.getYRot()).add(this.position());
                    subEntity.setPos(pos);
                    subEntity.xo = pos.x;
                    subEntity.yo = pos.y;
                    subEntity.zo = pos.z;
                    subEntity.xOld = pos.x;
                    subEntity.yOld = pos.y;
                    subEntity.zOld = pos.z;
                }
                this.setPos(owner.position().add(owner.getForward().scale(5)));
                this.setXRot(owner.getXRot());
                this.setYRot(owner.getYRot());
            }
        }
        super.tick();
    }

    @Override
    public PartEntity<?>[] getParts() {
        return new PartEntity[0];
    }

    public void setOwner(LivingEntity owner){
        this.owner = owner;
    }
}
