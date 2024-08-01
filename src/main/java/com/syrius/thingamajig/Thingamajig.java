package com.syrius.thingamajig;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod("thingamajig")
public class Thingamajig {
    public static final String MODID = "thingamajig";
    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("thingamajig");
    public static final DeferredItem<Item> THINGAMAJIG;

    public Thingamajig(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modContainer.registerConfig(Type.COMMON, Config.SPEC);
        ITEMS.register(modEventBus);
        TabInit.THINGAMAJIG_TAB.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(RepairTickHandler.class);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    static {
        THINGAMAJIG = ITEMS.registerItem("thingamajig", ItemThingamajig::new, (new Item.Properties()).stacksTo(1));
    }

    @SuppressWarnings("unused")
    @EventBusSubscriber(
            modid = MODID,
            bus = Bus.MOD,
            value = {Dist.CLIENT}
    )
    public static class ClientModEvents {
        public ClientModEvents() {
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
