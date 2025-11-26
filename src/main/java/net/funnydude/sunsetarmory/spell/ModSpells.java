package net.funnydude.sunsetarmory.spell;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import mod.azure.azurelib.core.math.functions.classic.Abs;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.funnydude.sunsetarmory.spell.evocation.ArmorLockSpell;
import net.funnydude.sunsetarmory.spell.fire.GrandFireBallSpell;
import net.funnydude.sunsetarmory.spell.fire.SoulBoundSpell;
import net.funnydude.sunsetarmory.spell.holy.DivineShieldSpell;
import net.funnydude.sunsetarmory.spell.holy.GrandDivineSmiteSpell;
import net.funnydude.sunsetarmory.spell.ice.BlizzardSpell;
import net.funnydude.sunsetarmory.spell.ice.CoolDownSpell;
import net.funnydude.sunsetarmory.spell.kinetic.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModSpells {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, SunsetArmory.MODID);

    //Evocation Spells
    public static final Supplier<AbstractSpell> ARMOR_LOCK_SPELL = registerSpell(new ArmorLockSpell());

    //Holy Spells
    //public static final Supplier<AbstractSpell> DIVINE_SHIELD_SPELL = registerSpell(new DivineShieldSpell());
    public static final Supplier<AbstractSpell> GRAND_DIVINE_SMITE_SPELL = registerSpell(new GrandDivineSmiteSpell());

    //Kinetic Spells
    public static final Supplier<AbstractSpell> KINETIC_SLASH_SPELL = registerSpell(new KineticSlashSpell());
    //public static final Supplier<AbstractSpell> KINETIC_VERTICAL_SLASH_SPELL = registerSpell(new KineticVerticalSlashSpell());
    public static final Supplier<AbstractSpell> KINETIC_DASH_SPELL = registerSpell(new KineticDashSpell());
    public static final Supplier<AbstractSpell> KINETIC_DROP_KICK_SPELL = registerSpell(new KineticDropKickSpell());
    //public static final Supplier<AbstractSpell> HALF_SWORD_STANCE_SPELL = registerSpell(new HalfSwordStanceSpell());

    //Fire Spells
    public static final Supplier<AbstractSpell> SOUL_BOUND_SPELL = registerSpell(new SoulBoundSpell());
    public static final Supplier<AbstractSpell> GRAND_FIREBALL_SPELL = registerSpell(new GrandFireBallSpell());

    //Ice Spells
    //public static final Supplier<AbstractSpell> BLIZZARD_SPELL = registerSpell(new BlizzardSpell());
    //public static final Supplier<AbstractSpell> COOL_DOWN_SPELL = registerSpell(new CoolDownSpell());

    private static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }
}