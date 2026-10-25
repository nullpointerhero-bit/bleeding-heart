package com.starlitbrlliance.bleedingheart.registry;

import com.starlitbrlliance.bleedingheart.BleedingHeartMod;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BleedingHeartMod.MODID);

    /** The in-world block that represents the fluid. Copies water's properties. */
    public static final DeferredBlock<LiquidBlock> BLOOD_BLOCK = BLOCKS.register("blood",
            () -> new LiquidBlock(ModFluids.SOURCE_BLOOD.get(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
                            .mapColor(MapColor.COLOR_RED)
                            .noLootTable()));

    private ModBlocks() {}
}
