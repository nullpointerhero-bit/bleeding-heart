package com.starlitbrlliance.bleedingheart.registry;

import com.starlitbrlliance.bleedingheart.BleedingHeartMod;
import com.starlitbrlliance.bleedingheart.fluid.BloodFluidType;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModFluids {

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, BleedingHeartMod.MODID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, BleedingHeartMod.MODID);

    public static final DeferredHolder<FluidType, BloodFluidType> BLOOD_TYPE = FLUID_TYPES.register("blood",
            () -> new BloodFluidType(FluidType.Properties.create()
                    .descriptionId("block.bleedingheartmod.blood")
                    .canSwim(true)
                    .canDrown(true)
                    .canPushEntity(true)
                    .canHydrate(true)               // hydrates farmland, etc.
                    .canExtinguish(true)
                    .canConvertToSource(true)       // two adjacent sources make a new one
                    .supportsBoating(true)
                    .fallDistanceModifier(0F)
                    .motionScale(0.014D)
                    .density(1000)
                    .viscosity(1000)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> SOURCE_BLOOD = FLUIDS.register("blood",
            () -> new BaseFlowingFluid.Source(ModFluids.BLOOD_PROPERTIES));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_BLOOD = FLUIDS.register("flowing_blood",
            () -> new BaseFlowingFluid.Flowing(ModFluids.BLOOD_PROPERTIES));

    public static final BaseFlowingFluid.Properties BLOOD_PROPERTIES =
            new BaseFlowingFluid.Properties(BLOOD_TYPE, SOURCE_BLOOD, FLOWING_BLOOD)
                    .slopeFindDistance(4)
                    .levelDecreasePerBlock(1)
                    .tickRate(5)
                    .explosionResistance(100F)
                    .block(ModBlocks.BLOOD_BLOCK)
                    .bucket(ModItems.BLOOD_BUCKET);

    private ModFluids() {}
}
