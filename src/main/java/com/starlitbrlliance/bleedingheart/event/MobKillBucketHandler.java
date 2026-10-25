package com.starlitbrlliance.bleedingheart.event;

import com.starlitbrlliance.bleedingheart.BleedingHeartMod;
import com.starlitbrlliance.bleedingheart.registry.ModItems;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

/**
 * Killing any mob while holding an empty bucket in the off hand converts one
 * empty bucket into a Bucket of Blood.
 */
@EventBusSubscriber(modid = BleedingHeartMod.MODID)
public final class MobKillBucketHandler {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity victim = event.getEntity();
        if (victim.level().isClientSide() || !(victim instanceof Mob)) {
            return;
        }

        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack offhand = player.getOffhandItem();
        if (!offhand.is(Items.BUCKET)) {
            return;
        }

        ItemStack blood = new ItemStack(ModItems.BLOOD_BUCKET.get());
        boolean creative = player.getAbilities().instabuild;

        if (creative) {
            if (player.getInventory().contains(blood)) {
                return;
            }
        } else {
            offhand.shrink(1);
        }

        if (!player.getInventory().add(blood)) {
            player.drop(blood, false);
        }

        player.level().playSound(null, player.blockPosition(),
                SoundEvents.BUCKET_FILL, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    private MobKillBucketHandler() {}
}
