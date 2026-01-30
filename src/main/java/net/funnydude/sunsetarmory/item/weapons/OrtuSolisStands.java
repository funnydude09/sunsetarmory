package net.funnydude.sunsetarmory.item.weapons;

import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import net.funnydude.sunsetarmory.registries.ModDataComponents;
import net.funnydude.sunsetarmory.registries.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class OrtuSolisStands extends MagicSwordItem {
    public OrtuSolisStands(Tier pTier, Properties pProperties, SpellDataRegistryHolder[] spellDataRegistryHolders) {
        super(pTier, pProperties, spellDataRegistryHolders);
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.igniteForTicks(60);
        super.postHurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.getCooldowns().addCooldown(this, 20 * 60);
        player.addEffect(new MobEffectInstance(ModEffects.HALF_STANCE_SWORD_EFFECT,20*15));
        player.addEffect(new MobEffectInstance(ModEffects.BURNING_EFFECT,20*30));
        return super.use(level, player, usedHand);
    }
}
