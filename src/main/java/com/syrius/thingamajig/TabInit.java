//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.syrius.thingamajig;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TabInit {
    public static final DeferredRegister<CreativeModeTab> THINGAMAJIG_TAB;
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GROUP;

    public TabInit() {
    }

    @SuppressWarnings("unused")
    public static void register(IEventBus eventBus) {
        THINGAMAJIG_TAB.register(eventBus);
    }

    static {
        THINGAMAJIG_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "thingamajig");
        GROUP = THINGAMAJIG_TAB.register(
                "thingamajig_tab",
                () -> CreativeModeTab
                        .builder()
                        .title(Component.translatable("itemGroup.thingamajig"))
                        .icon(
                                () -> Thingamajig.THINGAMAJIG.get().getDefaultInstance()
                        )
                        .displayItems(
                                (parameters, output) -> output.accept(Thingamajig.THINGAMAJIG.get()))
                        .build()
        );
    }
}
