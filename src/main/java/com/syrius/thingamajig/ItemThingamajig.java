package com.syrius.thingamajig;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemThingamajig extends Item {
    public ItemThingamajig(Properties properties) {
        super(properties);
    }

    public void appendHoverText(
            @NotNull ItemStack stack,
            @NotNull Item.@NotNull TooltipContext context,
            @NotNull List<Component> tooltip,
            @NotNull TooltipFlag flagIn
    ) {
        super.appendHoverText(stack, context, tooltip, flagIn);
        tooltip.add(Component.translatable("item.thingamajig.thingamajig.desc").withStyle(ChatFormatting.GREEN));
    }
}
