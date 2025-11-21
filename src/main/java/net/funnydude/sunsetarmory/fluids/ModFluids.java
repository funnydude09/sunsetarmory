package net.funnydude.sunsetarmory.fluids;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.fluids.NoopFluid;
import io.redspace.ironsspellbooks.fluids.PotionFluidType;
import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModFluids {
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, SunsetArmory.MODID);
    private static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, SunsetArmory.MODID);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
        FLUID_TYPES.register(eventBus);
    }

    public static final DeferredHolder<FluidType, FluidType> FIRE_MIXTURE_TYPE = FLUID_TYPES.register("fire_mixture", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<FluidType, FluidType> ARCANE_MIXTURE_TYPE = FLUID_TYPES.register("arcane_mixture", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<FluidType, FluidType> NETHER_MIXTURE_TYPE = FLUID_TYPES.register("nether_mixture", () -> new FluidType(FluidType.Properties.create()));

    public static final DeferredHolder<Fluid, NoopFluid> FIRE_MIXTURE = registerNoop("fire_mixture", FIRE_MIXTURE_TYPE::value);
    public static final DeferredHolder<Fluid, NoopFluid> ARCANE_MIXTURE = registerNoop("arcane_mixture", ARCANE_MIXTURE_TYPE::value);
    public static final DeferredHolder<Fluid, NoopFluid> NETHER_MIXTURE = registerNoop("nether_mixture", NETHER_MIXTURE_TYPE::value);

    private static DeferredHolder<Fluid, NoopFluid> registerNoop(String name, Supplier<FluidType> fluidType) {
        DeferredHolder<Fluid, NoopFluid> holder = DeferredHolder.create(Registries.FLUID, IronsSpellbooks.id(name));
        BaseFlowingFluid.Properties properties = new BaseFlowingFluid.Properties(fluidType, holder::value, holder::value).bucket(() -> Items.AIR);
        FLUIDS.register(name, () -> new NoopFluid(properties));
        return holder;
    }
}