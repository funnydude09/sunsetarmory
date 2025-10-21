package net.funnydude.sunsetarmory.spell;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.spell.evocation.ArmorLockSpell;
import net.funnydude.sunsetarmory.spell.holy.DivineShieldSpell;
import net.funnydude.sunsetarmory.spell.kinetic.KineticSlashSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModSpells {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, SunsetArmory.MODID);
    public static final Supplier<AbstractSpell> ARMOR_LOCK_SPELL = registerSpell(new ArmorLockSpell());
    public static final Supplier<AbstractSpell> DIVINE_SHIELD_SPELL = registerSpell(new DivineShieldSpell());
    public static final Supplier<AbstractSpell> KINETIC_SLASH_SPELL = registerSpell(new KineticSlashSpell());

    private static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }
}