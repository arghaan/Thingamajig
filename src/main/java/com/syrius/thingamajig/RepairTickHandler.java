package com.syrius.thingamajig;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.jetbrains.annotations.NotNull;

public class RepairTickHandler {
    static int minSlot = 0;
    static int maxSlot = 40;

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.@NotNull Pre event) {
        Entity entity = event.getEntity();
        if (entity instanceof ServerPlayer serverPlayer) {
            Inventory inv = serverPlayer.getInventory();

            for (int slot = 0; slot < inv.getContainerSize(); ++slot) {
                ItemStack stack = inv.getItem(slot);
                if (stack.getItem() == Thingamajig.THINGAMAJIG.get() && serverPlayer.tickCount % Config.repairDelay == 0) {
                    repair(serverPlayer, inv);
                }
            }
        }

    }

    private static void repair(@NotNull ServerPlayer serverPlayer, Inventory inv) {
        try (Level level = serverPlayer.level()) {
            if (!level.isClientSide) {
                switch (Config.repairMode) {
                    case 0:
                        minSlot = 0;
                        maxSlot = 40;
                        break;
                    case 1:
                        minSlot = 0;
                        maxSlot = 8;
                        break;
                    case 2:
                        minSlot = 9;
                        maxSlot = 35;
                        break;
                    case 3:
                        minSlot = 36;
                        maxSlot = 40;
                }

                for (int slot = minSlot; slot < maxSlot; ++slot) {
                    ItemStack target = inv.getItem(slot);
                    if (!target.isEmpty() && target.isDamaged() && target.getItem().isRepairable(target)) {
                        target.setDamageValue(target.getDamageValue() - 1);
                    }
                }

            }
        } catch (Throwable e) {
            throw new RuntimeException();
        }
    }
}
