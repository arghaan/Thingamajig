package com.syrius.thingamajig;

import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(
        modid = "thingamajig",
        bus = Bus.MOD
)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.IntValue REPAIR_DELAY;
    private static final ModConfigSpec.IntValue REPAIR_MODE;
    static final ModConfigSpec SPEC;
    public static int repairDelay;
    public static int repairMode;

    public Config() {
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    static void onLoad(ModConfigEvent event) {
        repairDelay = REPAIR_DELAY.get();
        repairMode = REPAIR_MODE.get();
    }

    static {
        REPAIR_DELAY = BUILDER
                .comment(Component.translatable("item.thingamajig.thingamajig.config.delay").getString())
                .defineInRange("repairDelay", 1, 1, 1200);
        REPAIR_MODE = BUILDER.comment(Component.translatable("item.thingamajig.thingamajig.config.mode").getString())
                .defineInRange("repairModes", 0, 0, 3);
        SPEC = BUILDER.build();
    }
}
