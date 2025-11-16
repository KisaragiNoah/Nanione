package com.kisaraginoah.nanione.module.occ;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class OCCClientEvents {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        while (OCCKeyMappings.COMMAND_TOGGLE.get().consumeClick()) {
            if (!OCCCommandStorage.hasCommand()) continue;
            mc.player.connection.sendCommand(OCCCommandStorage.getStoredCommand());
        }
    }
}
