package net.funnydude.sunsetarmory.item.weapons;

import net.funnydude.sunsetarmory.registries.ModItems;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class SacrificialDagger extends SwordItem {
    public SacrificialDagger(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @SubscribeEvent
    public static void backStab(LivingDamageEvent.Pre event) {
        var target = event.getEntity();
        var attacker = event.getSource().getEntity();
        if(attacker != null) {
            var targetYRot = target.getYRot();
            var attackerYRot = attacker.getYRot();
            var damage = event.getOriginalDamage();
            if (attacker instanceof LivingEntity && attackerYRot <= targetYRot + 30 && attackerYRot >= targetYRot - 30 && ((LivingEntity) attacker).getMainHandItem().is(ModItems.SACRIFICIAL_DAGGER)) {
                event.setNewDamage(damage * 3);
            }
        }
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
