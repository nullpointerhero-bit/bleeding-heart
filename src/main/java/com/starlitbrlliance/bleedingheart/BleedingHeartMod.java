package com.starlitbrlliance.bleedingheart;

import com.starlitbrlliance.bleedingheart.registry.ModBlocks;
import com.starlitbrlliance.bleedingheart.registry.ModFluids;
import com.starlitbrlliance.bleedingheart.registry.ModItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(BleedingHeartMod.MODID)
public class BleedingHeartMod {
    public static final String MODID = "bleeding_heart";

    public BleedingHeartMod(IEventBus modBus) {
        ModFluids.FLUID_TYPES.register(modBus);
        ModFluids.FLUIDS.register(modBus);
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);

        modBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.BLOOD_BUCKET.get());
        }
    }
}
