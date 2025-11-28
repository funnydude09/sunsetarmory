package net.funnydude.sunsetarmory;

import io.redspace.ironsspellbooks.item.armor.UpgradeOrbType;
import io.redspace.ironsspellbooks.registries.UpgradeOrbTypeRegistry;
import net.minecraft.resources.ResourceKey;

public class ModUpgradeOrbs {
    public static ResourceKey<UpgradeOrbType> KINETIC_SPELL_POWER = ResourceKey.create(UpgradeOrbTypeRegistry.UPGRADE_ORB_REGISTRY_KEY, SunsetArmory.id("kinetic_power"));
}
