package com.starlitbrlliance.bleedingheart.client;

import com.starlitbrlliance.bleedingheart.BleedingHeartMod;
import com.starlitbrlliance.bleedingheart.registry.ModFluids;
import com.mojang.blaze3d.systems.RenderSystem;

import com.starlitbrlliance.bleedingheart.registry.ModItems;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.joml.Vector3f;

/**
 * Client-only rendering for the fluid.
 */
@EventBusSubscriber(modid = BleedingHeartMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class BloodClient {

    private static final ResourceLocation STILL = ResourceLocation.withDefaultNamespace("block/water_still");
    private static final ResourceLocation FLOWING = ResourceLocation.withDefaultNamespace("block/water_flow");
    private static final ResourceLocation OVERLAY = ResourceLocation.withDefaultNamespace("block/water_overlay");
    private static final ResourceLocation SCREEN_OVERLAY = ResourceLocation.withDefaultNamespace("textures/misc/underwater.png");

    private static final int TINT = 0xFFB01218; // ARGB; alpha must be set or the fluid is invisible

    @SubscribeEvent
    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> tintIndex == 1 ? TINT : 0xFFFFFFFF, ModItems.BLOOD_BUCKET.get());
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_BLOOD.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_BLOOD.get(), RenderType.translucent());
        });
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return FLOWING;
            }

            @Override
            public ResourceLocation getOverlayTexture() {
                return OVERLAY;
            }

            @Override
            public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                return SCREEN_OVERLAY;
            }

            @Override
            public int getTintColor() {
                return TINT;
            }

            @Override
            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level,
                                           int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return new Vector3f(0.45F, 0.03F, 0.05F);
            }

        }, ModFluids.BLOOD_TYPE.get());
    }

    private BloodClient() {}
}
