package net.funnydude.sunsetarmory.item.weapons;

import io.redspace.ironsspellbooks.entity.spells.EchoingStrikeEntity;
import io.redspace.ironsspellbooks.spells.ender.EchoingStrikesSpell;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class TruthEnforcer extends SwordItem {
    public TruthEnforcer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        var dmg = attacker.getMainHandItem().getDamageValue();
        var percent = 0.8;
        EchoingStrikeEntity echo = new EchoingStrikeEntity(attacker.level(), attacker, (float) (dmg * percent), EchoingStrikesSpell.radius);
        echo.setTracking(target);
        echo.setPos(target.getBoundingBox().getCenter().subtract(0, echo.getBbHeight() * .5f, 0));
        attacker.level().addFreshEntity(echo);
        super.postHurtEnemy(stack, target, attacker);
    }

    public static ItemAttributeModifiers createAttributes(SunsetWeaponsTier e) {
        var builder = ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID, e.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, e.getSpeed(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                );
        return builder.build();
    }
}
